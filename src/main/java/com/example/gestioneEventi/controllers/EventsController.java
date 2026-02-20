package com.example.gestioneEventi.controllers;

import com.example.gestioneEventi.entities.Event;
import com.example.gestioneEventi.entities.User;
import com.example.gestioneEventi.exceptions.NotFoundException;
import com.example.gestioneEventi.exceptions.ValidationException;
import com.example.gestioneEventi.payloads.EventDTO;
import com.example.gestioneEventi.services.EventService;
import com.example.gestioneEventi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {
    private UserService userService;
    private EventService eventService;

    @Autowired
    public EventsController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody @Validated EventDTO payload, BindingResult validationResult) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            Long user_id = ((User) principal).getId();
            if (validationResult.hasErrors()) {
                List<String> errorsList = validationResult.getFieldErrors()
                        .stream()
                        .map(fieldError -> fieldError.getDefaultMessage())
                        .toList();
                throw new ValidationException(errorsList);
            } else
                return this.eventService.createEvent(payload, user_id);
        } else throw new NotFoundException("User not found");
    }
}
