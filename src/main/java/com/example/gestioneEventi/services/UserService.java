package com.example.gestioneEventi.services;

import com.example.gestioneEventi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
