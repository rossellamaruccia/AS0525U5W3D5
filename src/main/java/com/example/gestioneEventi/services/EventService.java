package com.example.gestioneEventi.services;

import com.example.gestioneEventi.entities.Event;
import com.example.gestioneEventi.entities.Role;
import com.example.gestioneEventi.entities.User;
import com.example.gestioneEventi.exceptions.UnauthorizedException;
import com.example.gestioneEventi.payloads.EventDTO;
import com.example.gestioneEventi.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private EventsRepository eventsRepository;
    private UserService userService;

    @Autowired
    public EventService(EventsRepository eventsRepository, UserService userService) {
        this.eventsRepository = eventsRepository;
        this.userService = userService;
    }

    public Event createEvent(EventDTO payload) {
        User organizer = this.userService.findById(payload.organizer_id());
        if (organizer.getRole() != Role.ORGANIZER) {
            throw new UnauthorizedException("You shall not pass.");
        } else
            return eventsRepository.save(new Event(payload.title(), payload.date(), payload.location(), payload.availability(), organizer));
    }
}
