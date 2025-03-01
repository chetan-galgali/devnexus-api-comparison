package com.example.spring_graphql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "FILMS")
public class Films {
    @Id
    private Integer id;
    private String title;
}
