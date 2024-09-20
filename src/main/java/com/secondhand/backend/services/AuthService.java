package com.secondhand.backend.services;

import com.secondhand.backend.config.JWTService;
import com.secondhand.backend.dtos.AuthResponse;
import com.secondhand.backend.dtos.LoginRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService; // Servicio para generar el JWT

    public ResponseEntity<AuthResponse> authenticate(LoginRequest loginRequest) {
        System.out.println("dentro de autservice: "+loginRequest);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getContrasena()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(token,"Autenticacion exitosa", true,userDetails));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null,"Error: Credenciales inválidas: "+ e, false, null));
        }
    }
}