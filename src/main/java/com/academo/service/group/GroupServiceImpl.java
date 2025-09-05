package com.academo.service.group;

import com.academo.model.Group;
import com.academo.model.User;
import com.academo.repository.GroupRepository;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.group.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserServiceImpl userService;

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
        // Fazer a implementação do encontrar
        User user = userService.findById(userId);
        group.setUser(user);
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }


}
