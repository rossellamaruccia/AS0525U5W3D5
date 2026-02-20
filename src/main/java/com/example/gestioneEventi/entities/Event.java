package com.example.gestioneEventi.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String title;
    private String description;
    private LocalDate date;
    @Setter(AccessLevel.NONE)
    private String location;
    private int availability;
    @ManyToOne
    private User organizer;
    @ManyToMany(mappedBy = "events")
    private Set<User> users;

    public Event(String title, LocalDate date, String location, int availability, User organizer) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.availability = availability;
        this.organizer = organizer;
    }
}
