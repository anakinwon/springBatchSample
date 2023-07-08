package com.pisien.batchSample.object;

import com.pisien.batchSample.object.entity.SamsungPhone;

public class ClassObjectExam01 {

    public static void main(String[] args) {

        // 붕어빵 찍기.
        // 설계도를 가지고 제품을 생산한다.
        // fi는 리모콘 같은 개념  (fi. 찍으면, 맴버변수들 보여준다.)
        FieldInit fi = new FieldInit();
        byte byteField = fi.byteField;
        System.out.println("byteField = " + fi.byteField);
        System.out.println("booleanFiled = " + fi.booleanFiled);
        System.out.println("floatFiled = " + fi.floatFiled);
        System.out.println("arrField = " + fi.arrField);

        System.out.println("fi = " + fi);
        System.out.println("fi.toString() = " + fi.toString());

    }

}
