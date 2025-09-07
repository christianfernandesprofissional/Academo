package com.academo.service.activity;

import com.academo.model.Activity;
import com.academo.model.ActivityType;
import com.academo.model.Subject;
import com.academo.model.User;
import com.academo.repository.ActivityRepository;
import com.academo.service.activityType.ActivityTypeServiceImpl;
import com.academo.service.subject.SubjectServiceImpl;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.NotAllowedInsertionException;
import com.academo.util.exceptions.activity.ActivityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ActivityServiceImp implements IActivityService{
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    SubjectServiceImpl subjectService;

    @Autowired
    ActivityTypeServiceImpl activityTypeService;

    @Override
    public List<Activity> getActivities(Integer userId) {
        return activityRepository.findAllByUserId(userId);
    }

    @Override
    public Activity getActivityByActivityIdAndUserId(Integer userId, Integer activityId) {
        return activityRepository.findByIdAndUserId(userId,activityId).orElseThrow(ActivityNotFoundException::new);
    }

    @Override
    public Activity insertActivity(Activity activity, Integer userId, Integer activityTypeId, Integer subjectId) {
        return activityRepository.save(fillActivity(activity,userId,activityTypeId,subjectId));
    }

    @Override
    public Activity updateActivity(Activity activity, Integer userId, Integer activityTypeId, Integer subjectId) {
        Activity inDb = activityRepository.findById(activity.getId()).orElseThrow(ActivityNotFoundException::new);
        if(!inDb.getUser().getId().equals(userId)) throw new NotAllowedInsertionException();
        return activityRepository.save(fillActivity(activity,userId,activityTypeId,subjectId));
    }

    @Override
    public void deleteActivity(Integer userId,Integer activityId) {
        Activity inDb = activityRepository.findById(activityId).orElseThrow(ActivityNotFoundException::new);
        if(!inDb.getUser().getId().equals(userId)) throw new NotAllowedInsertionException("Deleção inválida!");
        activityRepository.deleteById(activityId);
    }

    @Override
    public Boolean existsActivityByName(String activityName) {
        return activityRepository.existsActivityByName(activityName);
    }

    @Override
    public Boolean existsActivityById(Integer id) {
        return activityRepository.existsById(id);
    }

    /**
     * Preenche a classe Activity buscando
     * todas as dependências nos seus respectivos
     * services.
     * @author Christian
     * @return Activity
     */
    private Activity fillActivity(Activity activity, Integer userId, Integer activityTypeId, Integer subjectId){
        User user = userService.findById(userId);
        ActivityType activityType = activityTypeService.findByIdAndUserId(userId,activityTypeId);
        Subject subject = subjectService.getSubjectByIdAndUserId(userId, subjectId);
        activity.setActivityType(activityType);
        activity.setSubject(subject);
        activity.setUser(user);
        return activity;
    }

}