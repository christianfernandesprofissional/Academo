package com.academo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity-types")
public class TypeActivityController {

    @GetMapping
    public ResponseEntity getAllActivityTypes() {

    }
}
