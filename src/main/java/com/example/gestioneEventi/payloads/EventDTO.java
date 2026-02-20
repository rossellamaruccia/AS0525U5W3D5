package com.example.gestioneEventi.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventDTO(
        @NotBlank
        String title,
        @NotNull
        LocalDate date,
        @NotBlank
        String location,
        @NotNull
        int availability,
        @NotNull
        Long organizer_id) {
}
