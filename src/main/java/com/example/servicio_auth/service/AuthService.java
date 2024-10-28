package com.example.servicio_auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.servicio_auth.config.JWTAuthtenticationConfig;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    public Map<String, Object> validateToken(String token) {
        try {
            // Remover el prefijo "Bearer " si está presente
            if (token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            // Validar el token
            Claims claims = Jwts.parserBuilder() // Cambiar a parserBuilder()
                    .setSigningKey(jwtAuthtenticationConfig.getSigningKey()) // Configurar la clave secreta
                    .build() // Construir el parser
                    .parseClaimsJws(token) // Parsear y verificar el token JWT
                    .getBody();

            // Extraer información del usuario
            String username = claims.getSubject();

            // Manejar la conversión de authorities
            List<?> rawAuthorities = claims.get("authorities", List.class);
            List<String> authorities = rawAuthorities.stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .collect(Collectors.toList());

            // Retornar la información del usuario
            return Map.of("username", username, "authorities", authorities);
        } catch (Exception e) {
            // Manejo de excepciones
            return null; // Devuelve null para indicar un token inválido
        }
    }
}