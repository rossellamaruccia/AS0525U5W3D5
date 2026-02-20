package com.example.gestioneEventi.repositories;

import com.example.gestioneEventi.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Long> {
}
