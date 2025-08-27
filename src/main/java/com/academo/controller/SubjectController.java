package com.academo.controller;

import com.academo.model.Subject;
import com.academo.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SubjectController {

    @Autowired
    ISubjectService service;

    // A recuperação do Id do User por meio do PathVariable é temporária
    // Será implementado um Middleware para recuperação deste ID
    @PostMapping("/subjects")
    public ResponseEntity<Subject> create(@RequestBody Subject subject) {
        Integer userId = subject.getUser().getId();
        Subject createdSubject = service.create(subject, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

}
