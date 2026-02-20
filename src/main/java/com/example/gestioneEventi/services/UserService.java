package com.example.gestioneEventi.services;

import com.example.gestioneEventi.entities.User;
import com.example.gestioneEventi.exceptions.BadRequestException;
import com.example.gestioneEventi.exceptions.NotFoundException;
import com.example.gestioneEventi.payloads.UserDTO;
import com.example.gestioneEventi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UsersRepository usersRepository;
    private PasswordEncoder bcrypt;

    @Autowired
    public UserService(UsersRepository usersRepository, PasswordEncoder bcrypt) {
        this.usersRepository = usersRepository;
        this.bcrypt = bcrypt;
    }

    public User save(UserDTO payload) {
        if (this.usersRepository.findByFullname(payload.fullname()).isPresent())
            throw new BadRequestException("This user already exists");
        else
            return this.usersRepository.save(new User(payload.fullname(), bcrypt.encode(payload.password())));
    }

    public User findById(long id) {
        Optional<User> optional = this.usersRepository.findById(id);
        if (optional.isPresent()) return optional.get();
        else throw new NotFoundException(id);
    }

    public Optional<User> findByFullname(String fullname) {
        return this.usersRepository.findByFullname(fullname);
    }
}
