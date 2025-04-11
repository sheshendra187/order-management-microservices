package dev.ms.user_admin_service.repository;

import dev.ms.user_admin_service.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
    List<AdminUser> findByFullNameContainingIgnoreCase(String keyword);
}
