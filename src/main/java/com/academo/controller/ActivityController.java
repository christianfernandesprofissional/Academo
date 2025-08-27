package com.academo.controller;

import com.academo.model.Activity;
import com.academo.service.activity.ActivityServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityServiceImp  activityService;

    @GetMapping("/all/{userId}")
    public List<Activity> getActivities(@PathVariable Integer userId) {
        return activityService;
    }

    @GetMapping("/{id}")
    public Activity getActivity(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        return null;
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable String id, @RequestBody Activity activity) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable String id) {

    }
}
