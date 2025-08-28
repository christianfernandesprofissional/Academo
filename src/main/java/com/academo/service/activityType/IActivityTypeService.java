package com.academo.service.activityType;

import com.academo.model.ActivityType;

import java.util.List;

public interface IActivityTypeService {

    public List<ActivityType> findAll();
    public ActivityType findById(Integer id);
    public ActivityType create(ActivityType activityType);
    public ActivityType update(ActivityType activityType);

}
