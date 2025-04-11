package dev.ms.user_admin_service.controller;

import dev.ms.user_admin_service.dto.OrderRequest;
import dev.ms.user_admin_service.feign.OrderClient;
import dev.ms.user_admin_service.model.AdminUser;
import dev.ms.user_admin_service.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AdminUserRepository userRepository;
    private final OrderClient orderClient;

    // 7. Search user by keyword (only return full names)
    @GetMapping("/search/{keyword}")
    public List<String> searchUsers(@PathVariable String keyword) {
        return userRepository.findByFullNameContainingIgnoreCase(keyword)
                .stream()
                .map(AdminUser::getFullName)
                .toList();
    }

    // 8. Get logged-in user's info (not others')
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username, Authentication auth) {
        if (!auth.getName().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        return userRepository.findById(username)
                .map(user -> Map.of(
                        "username", user.getUsername(),
                        "fullName", user.getFullName(),
                        "phoneNumber", user.getPhoneNumber()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 9. Register new user (handled in AuthController)

    // 10. Get orders of logged-in user
    @GetMapping("/{username}/orders")
    public ResponseEntity<?> getUserOrders(@PathVariable String username, Authentication auth) {
        if (!auth.getName().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        return ResponseEntity.ok(orderClient.getOrdersByUsername(username));
    }

    // 10b. Place a new order
    @PostMapping("/{username}/orders")
    public ResponseEntity<?> placeOrder(
            @PathVariable String username,
            @RequestBody OrderRequest orderRequest,
            Authentication auth
    ) {
        if (!auth.getName().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        return orderClient.placeOrder(username, orderRequest);
    }
}

