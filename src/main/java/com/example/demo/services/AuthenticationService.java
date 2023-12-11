package com.example.demo.services;

import com.example.demo.Util.JwtUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.entity.JwtRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
public class AuthenticationService {
    @Override
    public String toString() {
        return "AuthenticationService{" +
                "userRepository=" + userRepository +
                ", userService=" + userService +
                ", passwordEncoder=" + passwordEncoder +
                ", jwtUtil=" + jwtUtil +
                ", authenticationManager=" + authenticationManager +
                '}';
    }

    public UserDao getUserRepository() {
        return userRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public JwtUtil getJwtUtil() {
        return jwtUtil;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    private final UserDao userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserDao userRepository, UserService userService, PasswordEncoder passwordEncoder,
                                 JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse signup(JwtRequest request) {
        var user = User
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getUserPassword()))
                //.role(Role.ROLE_USER)
                .build();

        user = userService.createNewUser((com.example.demo.entity.User) user);
        var jwt = jwtUtil.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signin(JwtRequest request) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getUserPassword()));
        var user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtUtil.generateToken(user);

        return JwtAuthenticationResponse.builder().token(jwt).build();

    }




}
