package com.pisien.batchSample.lamda.entity;

public class Population {

    private String region;
    private int population;

    public Population(String region, int population) {
        this.region = region;
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public int getPopulation() {
        return population;
    }
}
