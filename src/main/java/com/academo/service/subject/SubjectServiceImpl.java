package com.academo.service.subject;

import com.academo.model.Subject;
import com.academo.model.User;
import com.academo.repository.SubjectRepository;
import com.academo.repository.UserRepository;
import com.academo.util.exceptions.subject.SubjectNotFoundException;
import com.academo.util.exceptions.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = subjectRepository.findAll();
        if(subjects == null) throw new SubjectNotFoundException();
        return subjects;
    }

    @Override
    public Subject findById(Integer id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        if(subject == null) throw new SubjectNotFoundException();
        return subject;
    }

    @Override
    public Subject create(Subject subject, Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new UserNotFoundException();
        }
        Subject createdSubject = subjectRepository.save(subject);
        createdSubject.setUser(user);
        return createdSubject;
    }


    @Override
    public Subject update(Subject subject) {
        if (!subjectRepository.existsById(subject.getId())) throw new SubjectNotFoundException();
        Subject updatedSubject = subjectRepository.save(subject);
        return updatedSubject;
    }
}
