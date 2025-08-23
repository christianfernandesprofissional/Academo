package com.academo.service.user;

import com.academo.model.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();
    public User findById();
    public void create(User user);
    public void update(User user);
}