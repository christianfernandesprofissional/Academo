package com.academo.service.activity;

import com.academo.repository.GroupRepository;
import com.academo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class ActivityServiceImp implements IActivityService{
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
}