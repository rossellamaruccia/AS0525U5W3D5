package com.example.gestioneEventi.payloads;

import com.example.gestioneEventi.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotBlank(message = "full name is required")
        String fullname,
        @NotNull
        Role role,
        @NotBlank
        //TODO: aggiungere validazione regex x pw
        String password) {
}
