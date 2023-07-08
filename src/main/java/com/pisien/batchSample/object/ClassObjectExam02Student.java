package com.pisien.batchSample.object;

import com.pisien.batchSample.object.entity.Student;

public class ClassObjectExam02Student {

    public static void main(String[] args) {

        // 붕어빵 찍기.
        // 설계도를 가지고 제품을 생산한다.
        // fi는 리모콘 같은 개념  (fi. 찍으면, 맴버변수들 보여준다.)
        Student student1 = new Student();
        Student student2 = new Student();

        student1.age = 42;
        student1.name = "아나킨";

        // 동일한 주소를 매핑한다.
        student2 = student1;


        // student2.age 나이를 수정하면,
        // student1.age 의 나이도 수정된다.
        student2.age = 32;
        System.out.println("student1.age = " + student1.age);
        System.out.println("student1.name = " + student1.name);

        System.out.println("student2.age = " + student2.age);
        System.out.println("student2.name = " + student2.name);

    }

}

