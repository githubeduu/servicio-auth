package com.example.servicio_auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.servicio_auth.exceptions.UserNotFoundException;
import com.example.servicio_auth.model.LoginRequest;
import com.example.servicio_auth.model.UserResponse;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate = new RestTemplate();

    public UserResponse authenticateUser(String username, String password) {
        String url = "http://localhost:8081/usuario/signin";
        LoginRequest loginRequest = new LoginRequest(username, password);

        try {
            
            return restTemplate.postForObject(url, loginRequest, UserResponse.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED || e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("Usuario no encontrado o contraseña incorrecta");
            } else {

                throw e;
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

