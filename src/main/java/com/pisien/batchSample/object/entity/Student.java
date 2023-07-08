package com.pisien.batchSample.object.entity;

public class Student {

    // 맴버 변수 (field)
    public String name;
    public int age;

    // Object클래스의 toString() 오버라이딩(재정의)
    @Override
    public String toString() {

        // thiw : (Student)클래스 내에서 지역변수로 숨겨져 있는 존재
        //        클래스의 주소를 가지고 있다.
        return "Student{" +
                "name='" + this.name + '\'' +
                ", age=" +this. age +
                '}';
    }
}
