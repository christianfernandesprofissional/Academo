package com.academo.controller;

import com.academo.controller.dtos.ActivityTypeDTO;
import com.academo.model.Activity;
import com.academo.model.ActivityType;
import com.academo.model.User;
import com.academo.security.authuser.AuthUser;
import com.academo.service.activityType.ActivityTypeServiceImp;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.activity.ActivityExistsException;
import com.academo.util.exceptions.activityType.ActivityTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/activity-types")
public class ActivityTypeController {

    @Autowired
    ActivityTypeServiceImp activityTypeService;
    @Autowired
    UserServiceImpl userServiceImp;

    @GetMapping("/all")
    public ResponseEntity<List<ActivityTypeDTO>> getActivities(Authentication authentication) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        List<ActivityTypeDTO> types  = activityTypeService.findAll(userId)
                .stream()
                .map(t -> new ActivityTypeDTO(
                        t.getId(),
                        t.getName(),
                        t.getDescription()
                )).toList();
        return ResponseEntity.ok(types);
    }

    @GetMapping()
    public ResponseEntity<ActivityTypeDTO> getActivity(Authentication authentication, @RequestParam Integer id) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        ActivityType activityType = activityTypeService.findByIdAndUserId(id, userId).orElseThrow(ActivityTypeNotFoundException::new);
        ActivityTypeDTO activityTypeDTO = new ActivityTypeDTO(activityType.getId(), activityType.getName(), activityType.getDescription());
        return ResponseEntity.ok(activityTypeDTO);
    }

    @PostMapping
    public ResponseEntity<ActivityType> createActivity(Authentication authentication, @RequestBody ActivityTypeDTO activityTypeDTO) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        ActivityType created = activityTypeService.create(userId, new ActivityType(activityTypeDTO.name(), activityTypeDTO.description()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Integer id, @RequestBody Activity activity) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteActivity(@PathVariable Integer id) {
    return null;
    }
}
