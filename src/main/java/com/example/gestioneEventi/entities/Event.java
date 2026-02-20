package com.example.gestioneEventi.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
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
}
