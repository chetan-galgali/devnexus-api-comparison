package com.insight.transform.clientproject.value;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@EqualsAndHashCode
public class Producer {
    private Integer id;
    private String name;
}
