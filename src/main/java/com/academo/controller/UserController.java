package com.academo.controller;

import com.academo.model.User;
import com.academo.repository.UserRepository;
import com.academo.security.authuser.*;
import com.academo.security.service.TokenService;
import com.academo.service.profile.ProfileServiceImpl;
import com.academo.util.exceptions.user.ExistingUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserAuthDTO user) {
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        Authentication auth = authenticationManager.authenticate(userPass);

        var token = tokenService.generateToken((AuthUser) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO register) throws ExistingUserException {
        if(userRepository.findByName(register.name()) != null ||
                userRepository.findByEmail(register.email()) != null) throw new ExistingUserException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        User user = new  User(register.name(), encryptedPassword,register.email());
        User createdUser = userRepository.save(user);
        profileService.create(createdUser);
        return ResponseEntity.ok().build();
    }

}
