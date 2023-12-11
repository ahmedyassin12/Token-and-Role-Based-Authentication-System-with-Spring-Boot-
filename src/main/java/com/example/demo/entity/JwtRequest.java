package com.example.demo.entity;

/**
 * Represents a request for JWT authentication.
 */

public class JwtRequest {

    private String username ;
    private String userPassword ;

    public JwtRequest(String username, String userPassword) {
        if (username == null || userPassword == null || username.isEmpty() || userPassword.isEmpty()) {
            throw new IllegalArgumentException("Username and user password must be provided.");
        }

        this.username = username;
        this.userPassword = userPassword;
    }

    public JwtRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }



}
