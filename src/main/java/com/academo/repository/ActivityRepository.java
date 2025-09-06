package com.academo.repository;

import com.academo.model.Activity;
import com.academo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findAllByUserId(Integer userId);
    Optional<Activity> findByIdAndUserId(Integer userId, Integer activityId);
    Boolean existsActivityByName(String activityName);
}
