package com.academo.service.user;

import com.academo.model.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();
    public User findById(Integer id);
    public User create(User user);
    public User update(User user);
}