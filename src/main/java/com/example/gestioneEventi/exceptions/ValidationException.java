package com.example.gestioneEventi.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorList;

    public ValidationException(List<String> errorList) {
        super("Something went wrong :(");
        this.errorList = errorList;
    }
}
