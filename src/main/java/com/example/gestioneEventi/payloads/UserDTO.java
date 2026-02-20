package com.example.gestioneEventi.payloads;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank(message = "full name is required")
        String fullname,
        @NotBlank
        //TODO: aggiungere validazione regex x pw
        String password) {
}
