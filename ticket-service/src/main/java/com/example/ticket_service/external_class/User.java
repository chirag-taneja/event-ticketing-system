package com.example.ticket_service.external_class;

import jakarta.persistence.Column;

public class User {
    private Long id;


    private String username;


    private String password;


    private String email;


    private Role role;
}

enum Role {
    USER,ADMIN
}