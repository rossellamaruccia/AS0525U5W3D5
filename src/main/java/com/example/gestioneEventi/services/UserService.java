package com.example.gestioneEventi.services;

import com.example.gestioneEventi.entities.User;
import com.example.gestioneEventi.exceptions.BadRequestException;
import com.example.gestioneEventi.payloads.UserDTO;
import com.example.gestioneEventi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void save(UserDTO payload) {
        if (this.usersRepository.findByFullname(payload.fullname()).isPresent())
            throw new BadRequestException("This user already exists");
        else this.usersRepository.save(new User(payload.fullname(), payload.role(), payload.password()));
    }
}
