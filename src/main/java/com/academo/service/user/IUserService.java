package com.academo.service.user;

import com.academo.model.User;

import java.util.List;

public interface IUserService {

    public User findById(Integer id);
    public User update(User user);
}