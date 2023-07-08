package com.pisien.batchSample.job.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Customer {

    private String name;
    private int age;
    private String year;

}
