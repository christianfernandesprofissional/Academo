package com.academo.util.exceptions;

import com.academo.util.exceptions.activity.ActivityNotFoundException;
import com.academo.util.exceptions.group.GroupNotFoundException;
import com.academo.util.exceptions.subject.SubjectNotFoundException;
import com.academo.util.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    private ResponseEntity<String> subjectNotFoundHandler(SubjectNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matéria não encontrada!");
    }

    @ExceptionHandler(ActivityNotFoundException.class)
    private ResponseEntity<String> activityNotFoundHandler(ActivityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ativadade não encontrada!");
    }

    @ExceptionHandler(GroupNotFoundException.class)
    private ResponseEntity<String> groupNotFoundHandler(GroupNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gropo de matérias não encontrado!");
    }
}
