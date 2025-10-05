package com.academo.service.group;

import com.academo.model.Group;
import com.academo.model.Subject;
import com.academo.model.User;
import com.academo.repository.GroupRepository;
import com.academo.service.subject.SubjectServiceImpl;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.NotAllowedInsertionException;
import com.academo.util.exceptions.group.GroupNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    SubjectServiceImpl subjectService;

    // Lista todos os grupos pelo usuário
    @Override
    public List<Group> getGroups(Integer id){
        return groupRepository.findByUserId(id);
    }

    // Função para acessar um grupo específico
    @Override
    public Group getGroupByIdAndUserId(Integer userId, Integer groupId){
        //return groupRepository.findByIdAndUserId(userId,groupId).orElseThrow(GroupNotFoundException::new);
        return groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

    @Override
    // Cria novo grupo
    public Group insertGroup(Integer userId, Group group){
        User user =  userService.findById(userId);
        group.setUser(user);
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Integer userId, Group group) {
        Group groupDb = groupRepository.findById(group.getId()).orElseThrow(GroupNotFoundException::new);
        if (!groupDb.getUser().getId().equals(userId)) throw new NotAllowedInsertionException();
        group.setUser(groupDb.getUser());
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Integer userId, Integer groupId) {
        Group groupDb = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        if (!groupDb.getUser().getId().equals(userId)) throw new NotAllowedInsertionException("Deleção inválida!");
        groupRepository.deleteById(groupId);
    }

    @Override
    @Transactional
    public Group addSubjectToGroup(Integer userId, Integer groupId, Integer subjectId) {
        Group group = verifyGroup(userId, groupId);
        Subject subject = verifySubject(userId, subjectId);

        group.getSubjects().add(subject);
        return groupRepository.save(group);
    }

    @Override
    @Transactional
    public Group deleteSubjectFromGroup(Integer userId, Integer groupId, Integer subjectId) {
        Group group = verifyGroup(userId, groupId);
        Subject subject = verifySubject(userId, subjectId);

        group.getSubjects().remove(subject);
        return groupRepository.save(group);
    }

    @Override
    public Group associateSubjects(Integer userId, Integer groupId, List<Integer> subjectsIds) {
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        List<Subject> subjects = new ArrayList<>();
        for(Integer id : subjectsIds) {
            Subject subject = subjectService.findById(id);
            subjects.add(subject);
        }
        group.setSubjects(subjects.stream().filter(Objects::nonNull).toList());
        return group;
    }

    /**
     * Verifica se o grupo pertence ao mesmo usuário,
     *
     * @param userId id do usuário
     * @param groupId id do grupo
     * @return Group
     */
    private Group verifyGroup(Integer userId, Integer groupId){
        Group group = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
        if(!group.getUser().getId().equals(userId)) throw new NotAllowedInsertionException();
        return group;
    }

    /**
     * Verifica se o Subject pertence ao mesmo usuário,
     *
     * @param userId id do usuário
     * @param subjectId id do subject
     * @return Subject
     */
    private Subject verifySubject(Integer userId, Integer subjectId){
        Subject subject = subjectService.getSubjectByIdAndUserId(subjectId, userId);
        if(!subject.getUser().getId().equals(userId)) throw new NotAllowedInsertionException();
        return subject;
    }

}
