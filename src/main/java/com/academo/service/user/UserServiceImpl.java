package com.academo.service.user;

import com.academo.model.Profile;
import com.academo.model.User;
import com.academo.repository.ProfileRepository;
import com.academo.repository.UserRepository;
import com.academo.service.profile.ProfileServiceImpl;
import com.academo.util.exceptions.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileServiceImpl profileService;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
