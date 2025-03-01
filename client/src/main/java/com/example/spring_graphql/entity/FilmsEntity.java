package com.example.spring_graphql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "FILMS")
public class FilmsEntity {
    @Id
    private Integer id;
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FILMS_PRODUCERS",
            joinColumns = @JoinColumn(name = "FILM_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCER_ID"))
    private Set<ProducerEntity> producers;
}
