package com.academo.service.subject;

import com.academo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements ISubjectService{

    @Autowired
    private SubjectRepository subjectRepository;
}
