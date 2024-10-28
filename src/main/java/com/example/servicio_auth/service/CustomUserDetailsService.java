package com.example.servicio_auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.servicio_auth.model.LoginRequest;
import com.example.servicio_auth.model.UserResponse;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate = new RestTemplate();

    public UserResponse authenticateUser(String username, String password) {
        String url = "http://localhost:8080/usuario/signin";

        
        
        LoginRequest loginRequest = new LoginRequest(username, password);

        UserResponse userResponse = restTemplate.postForObject(url, loginRequest, UserResponse.class);

        return userResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
