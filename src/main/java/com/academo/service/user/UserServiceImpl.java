package com.academo.service.user;

import com.academo.model.User;
import com.academo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById() {
        return null;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }
}
