package com.academo.service.activityType;

import com.academo.model.ActivityType;

import java.util.List;

public interface IActivityTypeService {

    public List<ActivityType> findAll(Integer userId);
    public ActivityType findById(Integer id);
    public ActivityType create(Integer userId, ActivityType activityType);
    public ActivityType update(ActivityType activityType);
    public void deleteActivityType(Integer activityId);

}
