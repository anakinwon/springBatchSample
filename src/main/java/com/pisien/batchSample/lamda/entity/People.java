package com.pisien.batchSample.lamda.entity;

public class People {

    private String name;
    private int age;

    private String id;
    private Address address;


    public People() {
        System.out.println("기본생성자 호출");
    }
    public People(String name) {
        System.out.println("People(String name) 생성자 호출");
        this.name = name;
    }
    public People(String name, int age) {
        System.out.println("People(String name, int age) 생성자 호출");
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public People(String name, String id, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }
}
