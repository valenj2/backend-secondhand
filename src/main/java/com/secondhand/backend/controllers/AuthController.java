package com.secondhand.backend.controllers;

import com.secondhand.backend.config.JWTService;
import com.secondhand.backend.dtos.LoginRequest;
import com.secondhand.backend.services.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JWTService jwtService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return authService.authenticate(loginRequest);  // Aquí estás retornando un ResponseEntity
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        if (token != null) {
            jwtService.revokeToken(token);
        }
        return ResponseEntity.ok("Logout exitoso");
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}