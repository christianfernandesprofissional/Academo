package com.academo.service.group;

import com.academo.DTO.Group.GroupDTO;
import com.academo.model.Group;
import com.academo.model.User;
import com.academo.repository.GroupRepository;
import com.academo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    // Lista todos os grupos (VOLTAR AQUI PARA TERMINAR A IMPLEMENTAÇÃO)
    public List<GroupDTO> findByUserId(Integer id){
        return groupRepository.findByUserId(id).stream().map(GroupDTO::new).toList();
    }

    // Cria novo grupo
    public Group create(GroupDTO groupDTO, Integer userId){
        User user = userRepository.findById(userId).orElse(null);

        if (user == null){
            // Implmentar Exception
            return null;
        }

        //Group createdGroup = groupRepository.save(Group(groupDTO));
        return null;
    }
}
