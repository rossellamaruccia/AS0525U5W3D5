package com.example.gestioneEventi.services;

import com.example.gestioneEventi.entities.Event;
import com.example.gestioneEventi.exceptions.BadRequestException;
import com.example.gestioneEventi.payloads.EventDTO;
import com.example.gestioneEventi.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EventService {
    private EventsRepository eventsRepository;

    @Autowired
    public EventService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public void save(EventDTO payload) {
        if (this.eventsRepository.findByTitle(payload.title()).isPresent())
            throw new BadRequestException("This event already exists");
        else
            this.eventsRepository.save(new Event(payload.title(), payload.date(), payload.location(), payload.availability(), payload.organizer()));
    }
}
