package com.academo.service.activityType;

import com.academo.model.ActivityType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTypeServiceImp implements IActivityTypeService {
    @Override
    public List<ActivityType> findAll() {
        return List.of();
    }

    @Override
    public ActivityType findById(Integer id) {
        return null;
    }

    @Override
    public ActivityType create(ActivityType activityType) {
        return null;
    }

    @Override
    public ActivityType update(ActivityType activityType) {
        return null;
    }
}
