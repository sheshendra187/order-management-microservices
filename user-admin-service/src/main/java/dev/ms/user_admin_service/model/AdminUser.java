package dev.ms.user_admin_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {

    @Id
    private String username;

    private String password;

    private String role; // ADMIN or USER

    private String fullName;

    private String phoneNumber;
}
