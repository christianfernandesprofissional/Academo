package com.academo.service.activityType;

import com.academo.model.ActivityType;
import com.academo.model.Group;
import com.academo.repository.ActivityTypeRepository;
import com.academo.repository.UserRepository;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.activityType.ActivityTypeExistsException;
import com.academo.util.exceptions.activityType.ActivityTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityTypeServiceImp implements IActivityTypeService {

    @Autowired
    ActivityTypeRepository repository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public List<ActivityType> findAll(Integer  userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public ActivityType findById(Integer id) {
        return repository.findById(id).orElseThrow(ActivityTypeNotFoundException::new);
    }

    @Override
    public ActivityType create(Integer userId, ActivityType activityType) {
        if(repository.existsByName(activityType.getName())) throw new ActivityTypeExistsException();
        activityType.setUser(userService.findById(userId));
        return repository.save(activityType);
    }

    @Override
    public ActivityType update(ActivityType activityType) {
        if(!repository.existsById(activityType.getId())) throw new ActivityTypeNotFoundException();
        return repository.save(activityType);
    }

    @Override
    public void deleteActivityType(Integer activityId){
        repository.deleteById(activityId);
    }

    public Optional<ActivityType> findByIdAndUserId(Integer id, Integer userId){
        return repository.findByIdAndUserId(id, userId);
    }
}
