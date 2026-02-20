package com.example.gestioneEventi.payloads;

import com.example.gestioneEventi.entities.Role;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank(message = "full name is required")
        String fullname,
        @NotBlank(message = "role must be specified")
        Role role,
        @NotBlank
        //TODO: aggiungere validazione regex x pw
        String password) {
}
