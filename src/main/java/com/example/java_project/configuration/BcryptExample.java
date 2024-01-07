package com.example.java_project.configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptExample {
    public static void main(String[] args) {
        String password = "admin";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        System.out.println("Hashed Password: " + hashedPassword);
    }
}
