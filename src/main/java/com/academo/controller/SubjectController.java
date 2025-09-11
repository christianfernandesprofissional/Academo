package com.academo.controller;

import com.academo.controller.dtos.subject.SubjectDTO;
import com.academo.controller.dtos.subject.SubjectPostDTO;
import com.academo.model.Activity;
import com.academo.model.Subject;
import com.academo.security.authuser.AuthUser;
import com.academo.service.subject.ISubjectService;
import com.academo.service.subject.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    ISubjectService serviceI;

    @Autowired
    SubjectServiceImpl service;

    // A recuperação do Id do User por meio do PathVariable é temporária
    // Será implementado um Middleware para recuperação deste ID
    @PostMapping
    public ResponseEntity<Subject> create(Authentication authentication, @RequestBody SubjectPostDTO subjectDTO) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Subject createdSubject = service.create(new Subject(subjectDTO.name(), subjectDTO.description()),userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDTO>> getSubjects(Authentication authentication) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        List<SubjectDTO> subjects = service.findAll(userId)
                .stream()
                .map(g -> new SubjectDTO(
                        g.getId(),
                        g.getName(),
                        g.getDescription())).toList();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping
    public ResponseEntity<SubjectDTO> getSubject(Authentication authentication, @RequestParam Integer subjectId) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Subject subject = service.getSubjectByIdAndUserId(userId, subjectId);
        SubjectDTO subjectDTO = new SubjectDTO(subject.getId(), subject.getName(), subject.getDescription());
        return ResponseEntity.ok(subjectDTO);
    }

    @PutMapping
    public ResponseEntity<SubjectDTO> updateSubject(Authentication authentication, @RequestBody SubjectDTO subjectDTO) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Subject subject = new Subject(subjectDTO);
        service.updateSubject(userId,subject);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Activity> deleteActivity(Authentication authentication, @RequestParam Integer subjectId) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        service.deleteSubject(userId, subjectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
