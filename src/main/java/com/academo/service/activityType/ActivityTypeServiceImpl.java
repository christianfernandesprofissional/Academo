package com.academo.service.activityType;

import com.academo.model.ActivityType;
import com.academo.model.User;
import com.academo.repository.ActivityTypeRepository;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.NotAllowedInsertionException;
import com.academo.util.exceptions.activityType.ActivityTypeExistsException;
import com.academo.util.exceptions.activityType.ActivityTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTypeServiceImpl implements IActivityTypeService {

    @Autowired
    ActivityTypeRepository repository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public List<ActivityType> findAll(Integer  userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public ActivityType findByIdAndUserId(Integer userId, Integer ActivityTypeId) {
        return repository.findByIdAndUserId(userId, ActivityTypeId).orElseThrow(ActivityTypeNotFoundException::new);
    }

    @Override
    public ActivityType create(Integer userId, ActivityType activityType) {
        if(repository.existsByNameAndUserId(activityType.getName(), userId)) throw new ActivityTypeExistsException();
        activityType.setUser(userService.findById(userId));
        return repository.save(activityType);
    }

    @Override
    public ActivityType update(Integer userId, ActivityType activityType) {
        ActivityType inDb = repository.findByIdAndUserId(userId,activityType.getId()).orElseThrow(ActivityTypeNotFoundException::new);
        if (!inDb.getUser().getId().equals(userId)) throw new NotAllowedInsertionException();

        activityType.setUser(inDb.getUser());
        return repository.save(activityType);
    }

    @Override
    public void deleteActivityType(Integer userId, Integer activityId){
        ActivityType inDb = repository.findByIdAndUserId(userId,activityId).orElseThrow(ActivityTypeNotFoundException::new);
        if (!inDb.getUser().getId().equals(userId)) throw new NotAllowedInsertionException("Deleção inválida!");
        repository.deleteById(activityId);
    }

}
