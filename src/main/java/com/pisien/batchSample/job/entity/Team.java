package com.pisien.batchSample.job.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Team {
    private Long id;
    private String name;
    private int age;
}
