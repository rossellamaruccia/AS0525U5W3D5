package com.example.gestioneEventi.services;

import com.example.gestioneEventi.entities.User;
import com.example.gestioneEventi.exceptions.BadRequestException;
import com.example.gestioneEventi.payloads.UserDTO;
import com.example.gestioneEventi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService {
    private UsersRepository usersRepository;
    private PasswordEncoder bcrypt;

    @Autowired
    public UserService(UsersRepository usersRepository, PasswordEncoder bcrypt) {
        this.usersRepository = usersRepository;
        this.bcrypt = bcrypt;
    }

    public void save(UserDTO payload) {
        if (this.usersRepository.findByFullname(payload.fullname()).isPresent())
            throw new BadRequestException("This user already exists");
        else this.usersRepository.save(new User(payload.fullname(), payload.role(), bcrypt.encode(payload.password())));
    }

    public User findById(long id) {
        Optional<User> optional = this.usersRepository.findById(id);
        if (optional.isPresent()) return optional.get();
        else throw NotFoundException(id);
    }
}
