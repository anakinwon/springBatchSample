package com.pisien.batchSample.lamda;

@FunctionalInterface //  인터페이스.
public interface MyInterfaceReturn {

    // void 선언 시 Return이 없음
    //void method(int x, int y);

    // int 타입으로 리턴함.
    int method(int x, int y);

}

