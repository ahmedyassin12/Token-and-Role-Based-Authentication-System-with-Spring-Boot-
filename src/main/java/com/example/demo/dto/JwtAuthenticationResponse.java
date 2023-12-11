package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class JwtAuthenticationResponse {
    @Override
    public String toString() {
        return "JwtAuthenticationResponse{" +
                "token='" + token + '\'' +
                '}';
    }

    String token;
    // Default constructor
    public JwtAuthenticationResponse() {
    }

    // Constructor with token
    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    // Getters and setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Additional constructors or methods if needed
    public static JwtAuthenticationResponseBuilder builder() {
        return new JwtAuthenticationResponseBuilder();
    }

    public static class JwtAuthenticationResponseBuilder {
        private String token;

        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public JwtAuthenticationResponse build() {
                return new JwtAuthenticationResponse(token);
        }

    }


}