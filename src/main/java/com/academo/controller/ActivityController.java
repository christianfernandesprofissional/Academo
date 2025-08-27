package com.academo.controller;

import com.academo.model.Activity;
import com.academo.service.activity.ActivityServiceImp;
import com.academo.util.exceptions.activity.ActivityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityServiceImp  activityService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Activity>> getActivities(@PathVariable Integer userId) {
        return ResponseEntity.ok(activityService.getActivities(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable Integer id) {
        Activity activity = activityService.getActivityById(id);
        if(activity == null) {
            throw new ActivityNotFoundException();
        }
        return ResponseEntity.ok(activity);
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        if(activityService.existsActivity(activity)) throw new ActivityExistsException();
        Activity created = activityService.insertActivity(activity);
        Location URI = URI.createURI("/activities/" + created.getId);
        return ResponseEntity.created().body(URI);
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable Integer id, @RequestBody Activity activity) {
        if(!activityService.existsActivity(id)) throw new ActivityNotFoundException();
        return activityService.updateActivity(id, activity);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable String id) {

    }
}
