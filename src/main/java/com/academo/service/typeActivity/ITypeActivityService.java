package com.academo.service.typeActivity;

import com.academo.model.ActivityType;

import java.util.List;

public interface ITypeActivityService {

    public List<ActivityType> findAll();
    public ActivityType findById(Integer id);
    public ActivityType create(ActivityType activityType);
    public ActivityType update(ActivityType activityType);

}
