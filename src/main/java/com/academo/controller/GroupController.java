package com.academo.controller;

import com.academo.controller.dtos.GroupDTO;
import com.academo.model.Group;
import com.academo.service.group.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(value = "/all/{userId}")
    public ResponseEntity<List<GroupDTO>> getGroups(@PathVariable Integer userId){
        List<GroupDTO> groups = groupService.getGroups(userId)
                .stream()
                .map(g -> new GroupDTO(
                        g.getId(),
                        g.getName(),
                        g.getDescription())).toList();

        return ResponseEntity.ok(groups);
    }

    // Função para acessar um grupo específico
    @GetMapping(value = "/{groupId}")
    public ResponseEntity<Group> findById(@PathVariable Integer groupId){
        Group group = groupService.getGroupById(groupId);
        return ResponseEntity.ok(group);
    }

    // Função para criar novo grupo
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group){
        Group created = groupService.insertGroup(group);
        URI location = URI.create("/groups/" + created.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{groupId}")
    public ResponseEntity<Group> updateGroup(@PathVariable Integer groupId, @RequestBody Group group){
        Group saved = groupService.updateGroup(groupId, group);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{groupId}")
    public ResponseEntity deleteGroup(@PathVariable Integer groupId){
        groupService.deleteGroup(groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
