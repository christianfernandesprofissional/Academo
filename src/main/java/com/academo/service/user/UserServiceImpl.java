package com.academo.service.user;

import com.academo.model.Profile;
import com.academo.model.User;
import com.academo.repository.ProfileRepository;
import com.academo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(User user) {
        User createdUser = userRepository.save(user);
        if (user != null) {
            Profile profile = new Profile();

        }
        return null;
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }
}
