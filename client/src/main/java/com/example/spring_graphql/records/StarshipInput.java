package com.example.spring_graphql.records;

import lombok.Data;

@Data
public class StarshipInput {
    public Integer id;
    String name;
    Integer maxSpeed;
    Float length;
}
