package com.academo.controller;

import com.academo.controller.dtos.group.GroupDTO;
import com.academo.controller.dtos.group.GroupPostDTO;
import com.academo.controller.dtos.subject.SubjectDTO;
import com.academo.model.Group;
import com.academo.model.Subject;
import com.academo.security.authuser.AuthUser;
import com.academo.service.group.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
                        g.getDescription(),
                        //A lista de Subject do grupo é transformada em uma lista de SubjectDTO
                        g.getSubjects().stream()
                                .map(s -> new SubjectDTO(s.getId(), s.getName(), s.getDescription())).toList()
                )).toList();

        return ResponseEntity.ok(groups);
    }

    // Função para acessar um grupo específico
    @GetMapping
    public ResponseEntity<GroupDTO> findById(Authentication authentication, @RequestParam Integer groupId){
        //usando @RequestParam a requisição é feita pela url ficando localhost:8080/groups?groupId=1
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Group group = groupService.getGroupByIdAndUserId(userId,groupId);
        List<SubjectDTO> subjects = group.getSubjects().stream()
                .map(s -> new SubjectDTO(
                        s.getId(),
                        s.getName(),
                        s.getDescription()
                )).toList();
        GroupDTO groupDTO = new GroupDTO(group.getId(), group.getName(), group.getDescription(),subjects);
        return ResponseEntity.ok(groupDTO);
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

    @DeleteMapping
    public ResponseEntity<Group> deleteGroup(Authentication authentication, @RequestParam Integer groupId){
        //usando @RequestParam a requisição é feita pela url ficando localhost:8080/groups?groupId=1
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        groupService.deleteGroup(userId,groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/addSubject")
    public ResponseEntity<GroupDTO> addSubjectToGroup(Authentication authentication, @RequestParam Integer groupId, @RequestParam Integer subjectId) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Group group = groupService.addSubjectToGroup(userId, groupId, subjectId);
        List<SubjectDTO> subjects = group.getSubjects().stream()
                .map(s -> new SubjectDTO(
                        s.getId(),
                        s.getName(),
                        s.getDescription()
                )).toList();
        GroupDTO groupDTO = new GroupDTO(group.getId(), group.getName(), group.getDescription(),subjects);
        return ResponseEntity.ok(groupDTO);
    }

    @DeleteMapping("/removeSubject")
    public ResponseEntity<GroupDTO> removeSubjectFromGroup(Authentication authentication, @RequestParam Integer groupId, @RequestParam Integer subjectId){
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Group group = groupService.deleteSubjectFromGroup(userId, groupId, subjectId);
        List<SubjectDTO> subjects = group.getSubjects().stream()
                .map(s -> new SubjectDTO(
                        s.getId(),
                        s.getName(),
                        s.getDescription()
                )).toList();
        GroupDTO groupDTO = new GroupDTO(group.getId(), group.getName(), group.getDescription(),subjects);
        return ResponseEntity.ok(groupDTO);
    }
}
