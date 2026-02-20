package com.example.gestioneEventi.repositories;

import com.example.gestioneEventi.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventsRepository extends JpaRepository<Event, Long> {
    Optional<Object> findByTitle(String title);
}
