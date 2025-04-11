package dev.ms.user_admin_service.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String role; // "USER" or "ADMIN"
    private String fullName;
    private String phoneNumber;
}
