package com.academo.controller;

import com.academo.model.User;
import com.academo.repository.UserRepository;
import com.academo.security.authuser.RegisterDTO;
import com.academo.security.authuser.UserAuthDTO;
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

    @PostMapping("/login")
    public ResponseEntity<Authentication> login(@RequestBody UserAuthDTO user) {
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        Authentication auth = authenticationManager.authenticate(userPass);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO register) {
        if(userRepository.findByName(register.name()) != null ||
                userRepository.findByEmail(register.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        User user = new  User(register.name(), encryptedPassword,register.email(), register.isActive());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


}
