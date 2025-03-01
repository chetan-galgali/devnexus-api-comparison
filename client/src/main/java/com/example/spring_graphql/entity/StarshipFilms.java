package com.example.spring_graphql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "STARSHIP")
public class StarshipFilms {
    @Id
    @Column(name = "ID")
    private Integer id;
    @ManyToMany
    @JoinTable(name = "STARSHIP_FILMS",
            joinColumns = @JoinColumn(name = "starship_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private Set<Films> films;
}
