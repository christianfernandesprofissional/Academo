package com.academo.repository;

import com.academo.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Integer> {
    Boolean existsByName(String name);
    List<ActivityType> findAllByUserId(Integer id);
}
