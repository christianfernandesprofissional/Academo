package com.academo.util.exceptions;

import com.academo.util.exceptions.activity.ActivityExistsException;
import com.academo.util.exceptions.activity.ActivityNotFoundException;
import com.academo.util.exceptions.activityType.ActivityTypeExistsException;
import com.academo.util.exceptions.activityType.ActivityTypeNotFoundException;
import com.academo.util.exceptions.group.GroupNotFoundException;
import com.academo.util.exceptions.profile.ProfileNotFoundException;
import com.academo.util.exceptions.subject.SubjectNotFoundException;
import com.academo.util.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //Activity
    @ExceptionHandler(ActivityNotFoundException.class)
    private ResponseEntity<String> activityNotFoundHandler(ActivityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade não encontrada!");
    }

    @ExceptionHandler(ActivityExistsException.class)
    private ResponseEntity<String> activityNotFoundHandler(ActivityExistsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Atividade já cadastrada!");
    }

    //Group
    @ExceptionHandler(GroupNotFoundException.class)
    private ResponseEntity<String> groupNotFoundHandler(GroupNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo de matérias não encontrado!");
    }

    //Profile
    @ExceptionHandler(ProfileNotFoundException.class)
    private ResponseEntity<String> profileNotFoundHandler(ProfileNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil não encontrado!");
    }

    //Subject
    @ExceptionHandler(SubjectNotFoundException.class)
    private ResponseEntity<String> subjectNotFoundHandler(SubjectNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matéria não encontrada!");
    }

    //ActivityType
    @ExceptionHandler(ActivityTypeNotFoundException.class)
    private ResponseEntity<String> typeActivityNotFoundException(ActivityTypeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Atividade não encontrado!");
    }

    @ExceptionHandler(ActivityTypeExistsException.class)
    private ResponseEntity<String> typeActivityNotFoundException(ActivityTypeExistsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de Atividade já existe!");
    }

    //User
    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }

    //Inserção ou deleção de objetos que não pertecem ao usuário
    @ExceptionHandler(NotAllowedInsertionException.class)
    private ResponseEntity<String> userNotFoundHandler(NotAllowedInsertionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
