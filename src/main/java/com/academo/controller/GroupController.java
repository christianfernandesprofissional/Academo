package com.academo.controller;

import com.academo.DTO.Group.GroupDTO;
import com.academo.service.group.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    // Injeção de dependência da service
    @Autowired
    GroupServiceImpl groupService;

    // Função de listar todos os grupos
    @GetMapping(value = "/all/{userId}")
    public ResponseEntity<List<GroupDTO>> getGroups(@PathVariable Integer userId){
        List<GroupDTO> groups = groupService.getGroups(userId);
        return ResponseEntity.ok(groups);
    }

    // Função para acessar um grupo específico
    @GetMapping(value = "/{groupId}")
    public ResponseEntity<GroupDTO> findById(@PathVariable Integer groupId){
        GroupDTO groupDTO = groupService.getGroupById(groupId);
        return ResponseEntity.ok(groupDTO);
    }
}
