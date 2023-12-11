package com.example.demo.controller;

import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//handle web requests.
@RestController

@CrossOrigin
public class JwtController {
    public JwtService getJwtService() {
        return jwtService;
    }

    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
   private JwtService jwtService ;


    @PostMapping( "/authenticate")
    //The createJwtToken method is annotated with @PostMapping("/authenticate"),
    // which means it's mapped to HTTP POST requests to the "/authenticate" endpoint.
public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest)throws Exception{


       return  jwtService.createJwtToken(jwtRequest ) ;







}



}
