package com.academo.service.profile;

import com.academo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileServiceImpl {

    @Autowired
    private ProfileRepository profileRepository;
}
