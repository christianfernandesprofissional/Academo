package com.academo.repository;

import com.academo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    public List<Group> findByUserId(Integer userId);
    public Optional<Group> findByIdAndUserId(Integer id, Integer userId);
}
