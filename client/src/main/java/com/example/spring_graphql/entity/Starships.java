package com.example.spring_graphql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "STARSHIP")
public class Starships {
    @Id
    private Integer id;
    private String name;
    private Integer maxSpeed;
    private Float length;
}
