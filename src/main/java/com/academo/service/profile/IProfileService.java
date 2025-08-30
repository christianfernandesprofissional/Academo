package com.academo.service.profile;

import com.academo.model.Profile;
import com.academo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProfileService  {

    public Profile create(User user);
    public Profile update(Profile profile);
}
