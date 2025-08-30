package com.academo.controller;

import com.academo.model.Activity;
import com.academo.model.ActivityType;
import com.academo.model.User;
import com.academo.service.activityType.ActivityTypeServiceImp;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.activity.ActivityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<ActivityType>> getActivities(@PathVariable Integer userId) {
        List<ActivityType> types  = activityTypeService.findAll(userId);
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityType> getActivity(@PathVariable Integer id) {
        activityTypeService.findById(id);
        return ResponseEntity.ok(activityTypeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ActivityType> createActivity(@RequestBody ActivityType activityType) {
        //if(activityTypeService.existsActivityByName(activityType.getName())) throw new ActivityExistsException();
       // User user = userServiceImp.findById(activityType.getId());
      //  activityType.setUser(user);
        ActivityType created = activityTypeService.create(activityType);
        URI location = URI.create("/activity-types/" + created.getId());
        return ResponseEntity.created(location).build();
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
