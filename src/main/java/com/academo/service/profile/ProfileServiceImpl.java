package com.academo.service.profile;

import com.academo.model.Profile;
import com.academo.model.User;
import com.academo.repository.ProfileRepository;
import com.academo.util.exceptions.profile.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile findById(Integer id) {
        return profileRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    public Profile create(User user) {
        Profile profile = new Profile();
        profile.setId(user.getId());
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Integer userId, Profile profile) {
        profile.setId(userId);
        return profileRepository.save(profile);
    }
}
