package com.academo.controller;


import com.academo.controller.dtos.profile.ProfilePutDTO;
import com.academo.model.Profile;
import com.academo.security.authuser.AuthUser;
import com.academo.service.profile.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl service;

    @GetMapping
    public ResponseEntity<Profile> getProfile(Authentication authentication) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Profile profile = service.findById(userId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(Authentication authentication, @RequestBody ProfilePutDTO profileDto) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Profile profile = new  Profile(profileDto);
        Profile savedProfile = service.update(userId, profile);
        return ResponseEntity.ok(savedProfile);
    }
}
