package com.academo.controller;

import com.academo.model.User;
import com.academo.repository.UserRepository;
import com.academo.security.authuser.*;
import com.academo.security.service.TokenService;
import com.academo.service.profile.ProfileServiceImpl;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserAuthDTO user) {
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        Authentication auth = authenticationManager.authenticate(userPass);

        var token = tokenService.generateLoginToken((AuthUser) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO register) {
        if(userRepository.findByName(register.name()) != null ||
                userRepository.findByEmail(register.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        User user = new  User(register.name(), encryptedPassword,register.email());
        User createdUser = userRepository.save(user);
        profileService.create(createdUser);
        var token = tokenService.generateActivationToken(createdUser.getId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestParam("value") String token) {

        return ResponseEntity.ok().build();
    }

}
