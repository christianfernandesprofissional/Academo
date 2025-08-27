package com.academo.service.activity;

import com.academo.model.Activity;
import com.academo.repository.ActivityRepository;
import com.academo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ActivityServiceImp implements IActivityService{
    @Autowired
    ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivities(Integer userId) {
        return activityRepository.findByUserId(userId);
    }

    @Override
    public Activity getActivityById(int id) {
        return activityRepository.findById(id).orElseThrow();
    }

    @Override
    public Activity insertActivity(Activity activity) {
       return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Integer id, Activity activity) {
        if(!activityRepository.existsById(id)){
            //throw new ActivityNotFoundException();
        }
        return null;
    }

    @Override
    public void deleteActivity(Integer id) {

    }

    @Override
    public Boolean existsActivity(Integer userId) {
        return activityRepository.existsById(userId);
    }
}