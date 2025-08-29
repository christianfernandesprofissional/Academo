package com.academo.service.group;

import com.academo.model.Group;
import com.academo.repository.GroupRepository;
import com.academo.repository.UserRepository;
import com.academo.util.exceptions.group.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    // Lista todos os grupos pelo usuário
    @Override
    public List<Group> getGroups(Integer id){
        return groupRepository.findByUserId(id);
    }

    // Função para acessar um grupo específico
    @Override
    public Group getGroupById(Integer groupId){
        return groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

    @Override
    // Cria novo grupo
    public Group insertGroup(Group group){
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Integer id, Group group) {
        // Fazer a implementação do encontrar
        group.setId(id);
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }


}
