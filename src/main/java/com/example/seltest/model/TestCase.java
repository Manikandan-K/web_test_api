package com.example.seltest.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TestCase {
    private Long id;
    private List<Step> steps;
    private String url;
    private String name;
}
