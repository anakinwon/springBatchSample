package com.pisien.batchSample.object.implement;

/**
 *  <사칙연산 구현체>
 *     1. 인스턴스 메소드 내에서는 static 메소드 사용 가능.
 *     2. static 메소드 내에서는 인스턴스 메소드 사용 불가.
 *
 * */
public class Calculator {

    // 인스턴스 메소드 (new 인스턴스를 생성해야 사용가능)
    public int add(int x, int y) {
        // 인스턴스 메소드 내에서는 static 메소드 사용 가능...
        long multiply = Calculator.multiply(10, 20);
        System.out.println("inner multiply = " + multiply);
        return  x + y;
    }

    // 인스턴스 메소드 (new 인스턴스를 생성해야 사용가능)
    public int subtract(int x, int y) {
        return  x - y;
    }

    // 정적 메소드 (인스턴스 생성 없이 직접 접근 가능)
    public static long multiply(long x, long y) {
        // static 메소드 내에서는 인스턴스 메소드 사용 불가
        //this.add(10,20);
        return  x * y;
    }

    // 정적 메소드 (인스턴스 생성 없이 직접 접근 가능)
    public static double divide(double x, double y) {
        return  x / y;
    }

}
