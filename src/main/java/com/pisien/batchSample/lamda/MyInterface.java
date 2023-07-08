package com.pisien.batchSample.lamda;

@FunctionalInterface //  인터페이스.
public interface MyInterface {
    void method();
    //void method1(); // 추상메소드가 2개 이상이면 오류가 발생됨.
}
