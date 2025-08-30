package com.academo.service.profile;

import com.academo.model.Profile;
import com.academo.model.User;
import com.academo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile findById(Integer id) {
        return null;
    }

    @Override
    public Profile create(User user) {
        return null;
    }

    @Override
    public Profile update(Profile profile) {
        return null;
    }
}
