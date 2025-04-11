package dev.ms.user_admin_service.controller;

import dev.ms.user_admin_service.dto.OrderResponse;
import dev.ms.user_admin_service.feign.OrderClient;
import dev.ms.user_admin_service.model.AdminUser;
import dev.ms.user_admin_service.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminUserRepository userRepository;
    private final OrderClient orderClient;

    // 1. Fetch all users
    @GetMapping("/users")
    public List<AdminUser> getAllUsers() {
        return userRepository.findAll();
    }

    // 2. Fetch user by username
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userRepository.findById(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Search users by keyword in full name
    @GetMapping("/user/search/{keyword}")
    public List<AdminUser> searchUsers(@PathVariable String keyword) {
        return userRepository.findByFullNameContainingIgnoreCase(keyword);
    }

    // 4. Register new admin (public endpoint – handled in AuthController)

    // 5. Get all admins
    @GetMapping("/alladmins")
    public List<AdminUser> getAllAdmins() {
        return userRepository.findAll().stream()
                .filter(u -> "ADMIN".equalsIgnoreCase(u.getRole()))
                .collect(Collectors.toList());
    }

    // 6. Get order by orderId using Feign Client
    @GetMapping("/orders/{orderid}")
    public OrderResponse getOrderById(@PathVariable String orderid) {
        return orderClient.getOrderById(orderid);
    }
}

