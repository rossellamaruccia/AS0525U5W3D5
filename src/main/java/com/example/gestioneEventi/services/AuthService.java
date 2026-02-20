package com.example.gestioneEventi.services;

import com.example.gestioneEventi.entities.User;
import com.example.gestioneEventi.exceptions.NotFoundException;
import com.example.gestioneEventi.exceptions.UnauthorizedException;
import com.example.gestioneEventi.payloads.LoginDTO;
import com.example.gestioneEventi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final JWTTools jwtTools;
    private UserService userService;
    private PasswordEncoder bcrypt;

    @Autowired
    public AuthService(UserService userService, JWTTools jwtTools, PasswordEncoder bcrypt) {
        this.userService = userService;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO payload) {
        Optional<User> optional = this.userService.findByFullname(payload.fullname());
        if (optional.isPresent()) {
            User found = optional.get();
            if (bcrypt.matches(payload.password(), found.getPassword())) {
                String accessToken = jwtTools.generateToken(found);
                return accessToken;
            } else throw new UnauthorizedException("Wrong name or password");
        } else throw new NotFoundException("User not found");
    }
}
