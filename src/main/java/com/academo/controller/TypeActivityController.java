package com.academo.controller;

import com.academo.model.ActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity-types")
public class TypeActivityController {

    @Autowired
    ActivityTypeService activityTypeService;

    @GetMapping
    public ResponseEntity<List<ActivityType>> getAllActivityTypes() {

    }
}
