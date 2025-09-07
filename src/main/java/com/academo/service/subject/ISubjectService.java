package com.academo.service.subject;

import com.academo.model.Subject;

import java.util.List;

public interface ISubjectService {

    public List<Subject> findAll(Integer userId);
    public Subject getSubjectByIdAndUserId(Integer subjectId, Integer userId);
    public Subject create(Subject subject, Integer userId);
    public Subject updateSubject(Integer userId, Subject subject);
    public void deleteSubject(Integer userId, Integer subject);
}
