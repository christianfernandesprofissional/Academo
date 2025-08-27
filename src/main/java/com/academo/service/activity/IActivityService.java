package com.academo.service.activity;

import com.academo.model.Activity;

import java.util.List;

public interface IActivityService {

    List<Activity> getActivities();
    Activity getActivityById(int id);
    Activity insertActivity(Activity activity);
    Activity updateActivity(Integer id, Activity activity);
    void deleteActivity(Integer id);
}
