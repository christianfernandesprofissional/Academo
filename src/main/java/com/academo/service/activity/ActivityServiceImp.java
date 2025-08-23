package com.academo.service.activity;

import com.academo.model.Activity;
import com.academo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ActivityServiceImp implements IActivityService{

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public List<Activity> findAll() {
        return List.of();
    }

    @Override
    public Activity findById(Integer id) {
        return null;
    }

    @Override
    public Activity create(Activity activity) {
        return null;
    }

    @Override
    public Activity update(Activity activity) {
        return null;
    }
}