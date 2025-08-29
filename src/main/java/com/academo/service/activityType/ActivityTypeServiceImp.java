package com.academo.service.activityType;

import com.academo.model.ActivityType;
import com.academo.repository.ActivityTypeRepository;
import com.academo.util.exceptions.activityType.ActivityTypeExistsException;
import com.academo.util.exceptions.activityType.ActivityTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTypeServiceImp implements IActivityTypeService {

    @Autowired
    ActivityTypeRepository repository;

    @Override
    public List<ActivityType> findAll(Integer  userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public ActivityType findById(Integer id) {
        return repository.findById(id).orElseThrow(ActivityTypeNotFoundException::new);
    }

    @Override
    public ActivityType create(ActivityType activityType) {
        if(repository.existsByName(activityType.getName())) throw new ActivityTypeExistsException();
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
}
