package com.academo.util.exceptions;

import com.academo.model.User;
import com.academo.security.service.TokenService;
import com.academo.service.user.IUserService;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.FileTransfer.*;
import com.academo.util.exceptions.activity.ActivityExistsException;
import com.academo.util.exceptions.activity.ActivityNotFoundException;
import com.academo.util.exceptions.activityType.ActivityTypeExistsException;
import com.academo.util.exceptions.activityType.ActivityTypeNotFoundException;
import com.academo.util.exceptions.group.GroupNotFoundException;
import com.academo.util.exceptions.profile.ProfileNotFoundException;
import com.academo.util.exceptions.subject.SubjectNotFoundException;
import com.academo.util.exceptions.user.ExistingUserException;
import com.academo.util.exceptions.user.UserIsNotActiveException;
import com.academo.util.exceptions.user.UserNotFoundException;
import com.academo.util.exceptions.user.WrongDataException;
import com.academo.util.mailservice.JavaMailApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private JavaMailApp mail;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServiceImpl userService;

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

    @ExceptionHandler(ExistingUserException.class)
    private ResponseEntity<String> existingUserHandler(ExistingUserException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário já está cadastrado no sistema!");
    }

    @ExceptionHandler(WrongDataException.class)
    private ResponseEntity<String> wrongDataHandler(WrongDataException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário/Email ou Senha Inválidos!");
    }


    //Inserção ou deleção de objetos que não pertecem ao usuário
    @ExceptionHandler(NotAllowedInsertionException.class)
    private ResponseEntity<String> userNotFoundHandler(NotAllowedInsertionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(UserIsNotActiveException.class)
    private ResponseEntity<String> userIsNotActiveHandler(UserIsNotActiveException exception) {
        User user = exception.getUser();
        if (!user.getTokenExpiresAt().isAfter(LocalDateTime.now())) {
            LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30).plusSeconds(20).atOffset(ZoneOffset.of("-03:00")).toLocalDateTime();
            user.setTokenExpiresAt(expiresAt);
            userService.update(user);
            var token = tokenService.generateActivationToken(user.getId());
            mail.enviarEmailDeAtivacao(user.getEmail(), token);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O usuário ainda não foi ativado. Confira seu email para ativar");
    }

    //Files
    @ExceptionHandler(FileSizeException.class)
    private ResponseEntity<String> fileSizeHandler(FileSizeException exception) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(exception.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    private ResponseEntity<String> fileSizeHandler(FileNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(MimeTypeException.class)
    private ResponseEntity<String> mimeTypeHandler(MimeTypeException exception) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(exception.getMessage());
    }

    @ExceptionHandler(UserStorageIsFullException.class)
    private ResponseEntity<String> userStorageIsFullHandler(UserStorageIsFullException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(FileStorageException.class)
    private ResponseEntity<String> userStorageIsFullHandler(FileStorageException exception) {
        // Se tiver erro de autenticação do google, apague o diretório tokens, e reinicie a aplicação
        //isso vai fazer a aplicação gerar o link de login do google novamente
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

}