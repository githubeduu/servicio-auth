package com.example.servicio_auth.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicio_auth.config.JWTAuthtenticationConfig;
import com.example.servicio_auth.model.LoginRequest;
import com.example.servicio_auth.model.LoginResponse;
import com.example.servicio_auth.model.UserResponse;
import com.example.servicio_auth.service.AuthService;
import com.example.servicio_auth.service.CustomUserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Llamada al servicio de usuario para autenticar
        UserResponse userResponse = userDetailsService.authenticateUser(username, password);

        // Si la respuesta es nula, el usuario no fue encontrado
        if (userResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login");
        }

        // Generar el token JWT
        String token = jwtAuthtenticationConfig.getJWTToken(username);

        // Crear la respuesta
        LoginResponse loginResponse = new LoginResponse(userResponse, token);
        
        return ResponseEntity.ok(loginResponse); // Devolver la respuesta completa
    }


    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        Map<String, Object> userInfo = authService.validateToken(token);

        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }

        return ResponseEntity.ok(userInfo);
    }


}

