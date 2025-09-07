package com.academo.service.activityType;

import com.academo.model.ActivityType;

import java.util.List;

public interface IActivityTypeService {

    public List<ActivityType> findAll(Integer userId);
    public ActivityType findByIdAndUserId(Integer userId, Integer ActivityTypeId);
    public ActivityType create(Integer userId, ActivityType activityType);
    public ActivityType update(Integer userId, ActivityType activityType);
    public void deleteActivityType(Integer userId, Integer activityId);

}
