package com.academo.controller;

import com.academo.model.Subject;
import com.academo.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    ISubjectService service;

    // A recuperação do Id do User por meio do PathVariable é temporária
    // Será implementado um Middleware para recuperação deste ID
    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody Subject subject) {
        Integer userId = subject.getUser().getId();
        Subject createdSubject = service.create(subject, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable Integer userId) {
        List<Subject> subjects = service.findAll();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Integer subjectId) {
        Subject subject = service.findById(subjectId);
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        subject.setId(id);
        Subject updatedSubject = service.update(subject);
        return ResponseEntity.ok(updatedSubject);
    }



}
