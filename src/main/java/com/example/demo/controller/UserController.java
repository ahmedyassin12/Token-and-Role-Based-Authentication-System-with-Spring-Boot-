package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
private UserService userservice ;

    @Autowired
    public UserController(UserService userService) {
        this.userservice = userService;
    }

    @PostConstruct
    public void initRolesandUser(){

        userservice.initRolesandUser();



    }


    @PostMapping({"/createNewUser"})
    public User createNewUser( @RequestBody User  user){

        return userservice.createNewUser(user) ;



    }



    @GetMapping({"/forAdmin"})
    public String forAdmin(){


        return "this url is only accessible for admin" ;


    }
    @GetMapping({"/forUser"})
    public String forUser(){


        return "this url is only accessible for User" ;

    }




}
