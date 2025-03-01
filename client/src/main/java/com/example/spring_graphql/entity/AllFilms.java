package com.example.spring_graphql.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "FILMS")
public class AllFilms {
    @Id
    private Integer id;
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FILMS_PRODUCERS",
            joinColumns = @JoinColumn(name = "FILM_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCER_ID"))
    private Set<ProducerEntity> producers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "STARSHIP_FILMS",
            joinColumns = @JoinColumn(name = "FILM_ID"),
            inverseJoinColumns = @JoinColumn(name = "STARSHIP_ID"))
    private Set<Starships> starships;
}
