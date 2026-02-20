package com.example.gestioneEventi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("User with the id: " + id + "got lost. You got into a sideQuest");
    }
}
