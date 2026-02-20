package com.example.gestioneEventi.services;

import com.example.gestioneEventi.entities.Event;
import com.example.gestioneEventi.entities.Role;
import com.example.gestioneEventi.entities.User;
import com.example.gestioneEventi.exceptions.NotFoundException;
import com.example.gestioneEventi.exceptions.UnauthorizedException;
import com.example.gestioneEventi.payloads.EventDTO;
import com.example.gestioneEventi.payloads.ParticipationDTO;
import com.example.gestioneEventi.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventService {
    private EventsRepository eventsRepository;
    private UserService userService;

    @Autowired
    public EventService(EventsRepository eventsRepository, UserService userService) {
        this.eventsRepository = eventsRepository;
        this.userService = userService;
    }

    public Event createEvent(EventDTO payload, long user_id) {
        User organizer = this.userService.findById(user_id);
        if (organizer.getRole() != Role.ORGANIZER) {
            throw new UnauthorizedException("You shall not pass.");
        } else
            return eventsRepository.save(new Event(payload.title(), payload.date(), payload.location(), payload.availability(), organizer));
    }

    public void getReservation(long userId, ParticipationDTO payload) {
        User user = userService.findById(userId);
        //questo ID arriverà dal JWT token
        if (user == null || user.getRole() != Role.BASIC) {
            throw new UnauthorizedException("You shall not book");
        }
        Event event = eventsRepository.findById(payload.event_id()).orElseThrow(() -> new NotFoundException(payload.event_id()));

        if (event.getAvailability() != 0) {
            throw new IllegalArgumentException("Posti disponibili insufficienti");
        }

        event.setAvailability(event.getAvailability() - 1);
        Set<User> participants = event.getParticipants();
        participants.add(user);
        eventsRepository.save(event);
        ;
    }
}
