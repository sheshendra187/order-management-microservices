package dev.ms.user_admin_service.controller;

import dev.ms.user_admin_service.dto.AuthRequest;
import dev.ms.user_admin_service.dto.AuthResponse;
import dev.ms.user_admin_service.dto.RegisterRequest;
import dev.ms.user_admin_service.model.AdminUser;
import dev.ms.user_admin_service.repository.AdminUserRepository;
import dev.ms.user_admin_service.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        AdminUser user = adminUserRepository.findById(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(new AuthResponse(token, user.getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (adminUserRepository.existsById(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        AdminUser user = new AdminUser(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole(),
                request.getFullName(),
                request.getPhoneNumber()
        );

        adminUserRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}

