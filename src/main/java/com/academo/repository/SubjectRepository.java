package com.academo.repository;

import com.academo.model.Group;
import com.academo.model.Subject;
import com.academo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    public List<Subject> findByUserId(Integer userId);
    public Optional<Subject> findByIdAndUserId(Integer subjectId, Integer userId);

}
