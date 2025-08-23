package com.academo.service.subject;

import com.academo.model.Subject;

import java.util.List;

public interface ISubjectService {

    public List<Subject> findAll();
    public Subject findById(Integer id);
    public Subject create(Subject subject);
    public Subject update(Subject subject);

}
