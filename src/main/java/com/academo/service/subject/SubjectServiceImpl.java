package com.academo.service.subject;

import com.academo.model.Subject;
import com.academo.model.User;
import com.academo.repository.SubjectRepository;
import com.academo.repository.UserRepository;
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
        return List.of();
    }

    @Override
    public Subject findById(Integer id) {
        return null;
    }

    @Override
    public Subject create(Subject subject, Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            // Terminar implementação
            return null;
        }
        Subject createdSubject = subjectRepository.save(subject);
        createdSubject.setUser(user);

        return createdSubject;
    }


    @Override
    public Subject update(Subject subject) {
        return null;
    }
}
