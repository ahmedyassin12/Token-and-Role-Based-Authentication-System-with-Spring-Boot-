package com.example.demo.controller;

import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.entity.JwtRequest;
import com.example.demo.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")

public class AuthenticationController {
    @Override
    public String toString() {
        return "AuthenticationController{" +
                "authenticationService=" + authenticationService +
                '}';
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody JwtRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody JwtRequest request) {
        return authenticationService.signin(request);
    }
}
