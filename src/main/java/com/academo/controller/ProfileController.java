package com.academo.controller;


import com.academo.service.profile.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/profile")
public class ProfileController {

    @Autowired
    private IProfileService service;
}
