package com.pisien.batchSample.object;

public class ClassObjectExam08Member {

    public static void main(String[] args) {

        System.out.println("===========================================");
        /**
         *  아래는 Static 맴버변수로, 인스턴스 생성없이 접근 가능하다.
         * */
        Member.staticMethod();
        Member.cv = 500;
        Member.staticMethod();

        System.out.println("===========================================");
        /**
         *  아래는 Static 맴버변수로, 인스턴스 생성없이 접근 가능하다.
         * */

        Member member = new Member();
        member.instanceMethod();
        member.iv = 999;
        member.instanceMethod();

        ClassObjectExam08Member.study();
    }

    public static void study() {
        System.out.println("java 공부를 열심히 합시다!!!");
    }



}

