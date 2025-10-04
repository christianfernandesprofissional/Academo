package com.academo.controller;

import com.academo.model.User;
import com.academo.repository.UserRepository;
import com.academo.security.authuser.*;
import com.academo.security.service.TokenService;
import com.academo.service.profile.ProfileServiceImpl;
import com.academo.util.exceptions.user.UserNotFoundException;
import com.academo.util.mailservice.JavaMailApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProfileServiceImpl profileService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JavaMailApp mail;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserAuthDTO user) {
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        Authentication auth = authenticationManager.authenticate(userPass);

        var token = tokenService.generateLoginToken((AuthUser) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO register) {
        if(userRepository.findByName(register.name()) != null ||
                userRepository.findByEmail(register.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        User user = new  User(register.name(), encryptedPassword,register.email());
        User createdUser = userRepository.save(user);
        profileService.create(createdUser);
        enviarEmailDeAtivacao(createdUser.getEmail(), createdUser.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/activate")
    public ResponseEntity<User> activate(@RequestParam("value") String token) {
        Integer userId = Integer.parseInt(tokenService.validateActivationToken(token));
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setIsActive(true);
        userRepository.save(user);
        mail.enviarEmailBoasVindas(user.getEmail());
        return ResponseEntity.ok().build();
    }


    private void enviarEmailDeAtivacao(String email, Integer userId) {
        var token = tokenService.generateActivationToken(userId);
        mail.enviarEmailDeAtivacao(email, token);
    }

}
