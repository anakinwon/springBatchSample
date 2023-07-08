package com.pisien.batchSample.lamda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 람다식 예제 03.
 *    - 매개변수 포함 람다식
 * */


public class LamdaExample03 {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample03 의 로그");

    public static void main(String[] args) {

        // 1. 기본 타입 메소드
        MyInterfaceReturn myInterfaceReturn = new MyInterfaceReturn() {
            @Override
            public int method(int x, int y) {
                int result = x * y;
                System.out.println("1. x * y = " + result);
                return result;
            }
        };
        myInterfaceReturn.method(500, 10);

        // 2.매개변수 x값을 가지고 람다식을 구현한 매서드의 값
        MyInterfaceReturn myInterfaceReturn2 = null;

        myInterfaceReturn2 = (x, y) -> {
            int result = x * y;
            System.out.println("\t2. x * y = " + result);
            return result;
        };
        myInterfaceReturn2.method(400, 10);

        // 3. 매개타입 없애고, 변수선언부 없앰
        myInterfaceReturn2 = (x,y) -> { return  (x * y); };
        int result = myInterfaceReturn2.method(300, 10);
        System.out.println("\t\t3. x * y = " + result);

        // 4. 가장 축약 한 형태
        myInterfaceReturn2 = (x,y) -> (x * y);
        result = myInterfaceReturn2.method(200, 10);
        System.out.println("\t\t\t4. x * y = " + result);

    }
}
