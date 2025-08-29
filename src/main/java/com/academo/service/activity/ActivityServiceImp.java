package com.academo.service.activity;

import com.academo.model.Activity;
import com.academo.repository.ActivityRepository;
import com.academo.repository.UserRepository;
import com.academo.util.exceptions.activity.ActivityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ActivityServiceImp implements IActivityService{
    @Autowired
    ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivities(Integer userId) {
        return activityRepository.findAllByUserId(userId);
    }

    @Override
    public Activity getActivityById(Integer id) {
        return activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
    }

    @Override
    public Activity insertActivity(Activity activity) {
       return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Integer id, Activity activity) {
        if(!activityRepository.existsById(id)){
            throw new ActivityNotFoundException();
        }
        activity.setId(id);
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }

    @Override
    public Boolean existsActivityByName(String activityName) {
        return activityRepository.existsActivityByName(activityName);
    }

    @Override
    public Boolean existsActivityById(Integer id) {
        return activityRepository.existsById(id);
    }

}