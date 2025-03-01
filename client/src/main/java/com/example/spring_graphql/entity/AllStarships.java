package com.example.spring_graphql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "STARSHIP")
public class AllStarships {
    @Id
    private Integer id;
    private String name;
    private Integer maxSpeed;
    private Float length;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "STARSHIP_FILMS",
            joinColumns = @JoinColumn(name = "starship_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private Set<FilmsEntity> films;
}
