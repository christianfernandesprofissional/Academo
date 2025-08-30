package com.academo.security.service;

import com.academo.model.User;
import com.academo.repository.UserRepository;
import com.academo.security.authuser.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = username.contains("@") ? userRepository.findByEmail(username) : userRepository.findByName(username);
        return new AuthUser(user);
    }
}
