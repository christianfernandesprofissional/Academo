package com.academo.repository;

import com.academo.model.Group;
import com.academo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    public List<Subject> findByUserId(Integer userId);

}
