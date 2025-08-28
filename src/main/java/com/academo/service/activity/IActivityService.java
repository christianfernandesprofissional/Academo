package com.academo.service.activity;

import com.academo.model.Activity;

import java.util.List;

public interface IActivityService {

    public List<Activity> findAll();
    public Activity findById(Integer id);
    public Activity create(Activity activity);
    public Activity update(Activity activity);
}
