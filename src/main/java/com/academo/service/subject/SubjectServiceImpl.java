package com.academo.service.subject;

import com.academo.model.Subject;
import com.academo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> findAll() {
        return List.of();
    }

    @Override
    public Subject findById(Integer id) {
        return null;
    }

    @Override
    public Subject create(Subject subject) {
        return null;
    }

    @Override
    public Subject update(Subject subject) {
        return null;
    }
}
