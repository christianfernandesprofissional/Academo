package com.academo.repository;

import com.academo.model.ActivityType;
import com.academo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Integer> {
    Boolean existsByName(String name);
    List<ActivityType> findAllByUserId(Integer id);
    public Optional<ActivityType> findByIdAndUserId(Integer id, Integer userId);
}
