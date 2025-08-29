package com.academo.controller;

import com.academo.controller.dtos.ActivityDTO;
import com.academo.model.Activity;
import com.academo.service.activity.ActivityServiceImp;
import com.academo.util.exceptions.activity.ActivityExistsException;
import com.academo.util.exceptions.activity.ActivityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.Location;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityServiceImp  activityService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<ActivityDTO>> getActivities(@PathVariable Integer userId) {
       List<ActivityDTO> activities =activityService.getActivities(userId)
               .stream()
               .map(a -> new ActivityDTO(
                       a.getId(),
                       a.getDate(),
                       a.getName(),
                       a.getDescription(),
                       a.getSubject() != null ? a.getSubject().getId() : null,
                       a.getActivityType
                               () != null ? a.getActivityType().getId() : null
               )).toList();
       return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable Integer id) {
        //Este método será mudado mais pra frente devido a falta de segurança já que
        //ele permite que o usuário mude o path e acesse qualquer Activity.
        //Será resolvido via autenticação JWT com Spring Security
        Activity activity = activityService.getActivityById(id);
        if(activity == null) {
            throw new ActivityNotFoundException();
        }
        return ResponseEntity.ok(activity);
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        if(activityService.existsActivityByName(activity.getName())) throw new ActivityExistsException();
        Activity created = activityService.insertActivity(activity);
        URI location = URI.create("/activities/" + created.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Integer id, @RequestBody Activity activity) {
        if(!activityService.existsActivityById(id)) throw new ActivityNotFoundException();
        Activity saved = activityService.updateActivity(id, activity);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteActivity(@PathVariable Integer id) {
        activityService.deleteActivity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
