package com.edusystem.eduplatform.secure.notes.DTO;


import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
