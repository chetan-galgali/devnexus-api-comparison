package com.insight.transform.clientproject.value;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@ToString
@Builder
@EqualsAndHashCode
public class Films {
    private Integer id;
    private String title;
    private List<Producer> producers;
}
