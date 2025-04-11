package dev.ms.user_admin_service.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
