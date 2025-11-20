package com.academo.service.activity;

import com.academo.model.Activity;

import java.util.List;

public interface IActivityService {
    List<Activity> getActivities(Integer userId);
    Activity getActivityByActivityIdAndUserId(Integer userId,Integer activityId);
    Activity insertActivity(Activity activity, Integer userId, Integer activityTypeId, Integer subjectId);
    Activity updateActivity(Activity activity, Integer userId, Integer activityTypeId, Integer subjectId);
    void deleteActivity(Integer userId,Integer activityId);
    Boolean existsActivityByName(String activityName);
    Boolean existsActivityById(Integer id);
    List<Activity> getBySubjectId(Integer subjectId);
}
