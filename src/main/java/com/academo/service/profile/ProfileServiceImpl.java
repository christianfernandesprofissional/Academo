package com.academo.service.profile;

import com.academo.model.Profile;
import com.academo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> findAll() {
        return List.of();
    }

    @Override
    public Profile findById(Integer id) {
        return null;
    }

    @Override
    public Profile create(Profile profile) {
        return null;
    }

    @Override
    public Profile update(Profile profile) {
        return null;
    }
}
