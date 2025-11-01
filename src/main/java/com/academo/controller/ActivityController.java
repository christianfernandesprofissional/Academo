package com.academo.controller;

import com.academo.controller.dtos.activity.ActivityDTO;
import com.academo.controller.dtos.activity.ActivityPostDTO;
import com.academo.controller.dtos.activity.ActivityPutDTO;
import com.academo.controller.dtos.notification.NotificationDTO;
import com.academo.model.Activity;
import com.academo.security.authuser.AuthUser;
import com.academo.service.activity.ActivityServiceImp;
import com.academo.util.exceptions.activity.ActivityExistsException;
import com.academo.util.exceptions.activity.ActivityNotFoundException;
import com.academo.util.notification.SendNotifications;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityServiceImp  activityService;

    @Autowired
    private SendNotifications sendNotifications;

    @GetMapping("/all")
    public ResponseEntity<List<ActivityDTO>> getActivities(Authentication authentication) {
       Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
       List<ActivityDTO> activities =activityService.getActivities(userId)
               .stream()
               .map(a -> new ActivityDTO(
                       a.getId(),
                       a.getNotificationDate(),
                       a.getActivityDate(),
                       a.getName(),
                       a.getDescription(),
                       a.getSubject() != null ? a.getSubject().getName() : null,
                       a.getActivityType() != null ? a.getActivityType().getName() : null
               )).toList();
       return ResponseEntity.ok(activities);
    }

    @GetMapping
    public ResponseEntity<Activity> getActivity(Authentication authentication, @RequestParam Integer activityId) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Activity activity = activityService.getActivityByActivityIdAndUserId(userId,activityId);
        return ResponseEntity.ok(activity);
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(Authentication authentication, @RequestBody ActivityPostDTO activityPostDto) {
        if(activityService.existsActivityByName(activityPostDto.name())) throw new ActivityExistsException();
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Activity activity = new Activity(activityPostDto);
        Activity created = activityService.insertActivity(activity, userId, activityPostDto.activityTypeId(), activityPostDto.subjectId());
        URI location = URI.create("/activities?activityId=" + created.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Activity> updateActivity(Authentication authentication, @RequestBody ActivityPutDTO activityPutDto) {
        if(!activityService.existsActivityById(activityPutDto.id())) throw new ActivityNotFoundException();
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Activity activity = new Activity(activityPutDto);
        activityService.updateActivity(activity, userId, activityPutDto.ActivityTypeId(), activityPutDto.subjectId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Activity> deleteActivity(Authentication authentication, @RequestParam Integer activityId) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        activityService.deleteActivity(userId,activityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/teste")
    public void teste(Authentication authentication) throws MessagingException {
        sendNotifications.sendEmails(activityService.teste());
    }
}
