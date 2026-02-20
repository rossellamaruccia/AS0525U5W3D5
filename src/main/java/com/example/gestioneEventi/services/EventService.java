package com.example.gestioneEventi.services;

import com.example.gestioneEventi.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EventService {
    private EventsRepository eventsRepository;

    @Autowired
    public EventService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
}
