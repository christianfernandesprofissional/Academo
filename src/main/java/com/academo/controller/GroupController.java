package com.academo.controller;

import com.academo.controller.dtos.GroupDTO;
import com.academo.controller.dtos.GroupPostDTO;
import com.academo.model.Group;
import com.academo.security.authuser.AuthUser;
import com.academo.service.group.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    // Injeção de dependência da service
    @Autowired
    GroupServiceImpl groupService;

    // Função de listar todos os grupos
    @GetMapping("/all")
    public ResponseEntity<List<GroupDTO>> getGroups(Authentication authentication){
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        List<GroupDTO> groups = groupService.getGroups(userId)
                .stream()
                .map(g -> new GroupDTO(
                        g.getId(),
                        g.getName(),
                        g.getDescription())).toList();

        return ResponseEntity.ok(groups);
    }

    // Função para acessar um grupo específico
    @GetMapping
    public ResponseEntity<Group> findById(Authentication authentication, @RequestBody Integer groupId){
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Group group = groupService.getGroupByIdAndUserId(userId,groupId);
        return ResponseEntity.ok(group);
    }

    // Função para criar novo grupo
    @PostMapping
    public ResponseEntity<Group> createGroup(Authentication authentication, @RequestBody GroupPostDTO groupDTO){
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Group created = groupService.insertGroup(userId, new Group(groupDTO.name(), groupDTO.description()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Group> updateGroup(Authentication authentication, @RequestBody GroupDTO groupDTO){
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Group group = new Group(groupDTO.name(), groupDTO.description());
        group.setId(groupDTO.id());
        Group saved = groupService.updateGroup(userId,group);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{groupId}")
    public ResponseEntity deleteGroup(@PathVariable Integer groupId){
        groupService.deleteGroup(groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
