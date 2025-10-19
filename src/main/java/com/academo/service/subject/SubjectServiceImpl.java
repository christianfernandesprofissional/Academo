package com.academo.service.subject;

import com.academo.model.Activity;
import com.academo.model.Group;
import com.academo.model.Subject;
import com.academo.model.User;
import com.academo.repository.GroupRepository;
import com.academo.repository.SubjectRepository;
import com.academo.repository.UserRepository;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.NotAllowedInsertionException;
import com.academo.util.exceptions.activity.ActivityNotFoundException;
import com.academo.util.exceptions.group.GroupNotFoundException;
import com.academo.util.exceptions.subject.SubjectNotFoundException;
import com.academo.util.exceptions.user.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<Subject> findAll(Integer userId) {
        return subjectRepository.findByUserId(userId);
    }

    @Override
    public Subject findById(Integer subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    @Override
    public List<Subject> findByGroup(Integer groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        return group.getSubjects();
    }

    @Override
    public Subject create(Subject subject, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        subject.setUser(user);
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectByIdAndUserId(Integer subjectId, Integer userId) {
        return subjectRepository.findByIdAndUserId(subjectId, userId).orElseThrow(SubjectNotFoundException::new);
    }

    @Override
    public Subject updateSubject(Integer userId, Subject subject) {
        Subject inDb = subjectRepository.findById(subject.getId()).orElseThrow(SubjectNotFoundException::new);
        if(!inDb.getUser().getId().equals(userId)) throw new NotAllowedInsertionException();
        User user = userService.findById(userId);
        subject.setUser(user);
        return subjectRepository.save(subject);
    }

    @Override
    @Transactional
    public void deleteSubject(Integer userId, Integer subjectId){
        Subject inDb = subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        if(!inDb.getUser().getId().equals(userId)) throw new NotAllowedInsertionException("Deleção inválida!");

        for(Group g : inDb.getGroups()) {
            g.getSubjects().remove(inDb);
            groupRepository.save(g);
        }
        subjectRepository.deleteById(subjectId);
    }

}