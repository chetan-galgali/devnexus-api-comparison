package com.insight.transform.clientproject.value;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@ToString
@Builder
@EqualsAndHashCode
public class Starships {
    private Integer id;
    private String name;
    private Integer maxSpeed;
    private Float length;
    private List<Films> films;
}
