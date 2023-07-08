package com.pisien.batchSample.lamda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 람다식 예제 02.
 *    - 매개변수 포함 람다식
 * */


public class LamdaExample02 {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample02 의 로그");

    public static void main(String[] args) {

        // 1. 기본 타입 메소드
        MyInterfaceParam myInterfaceParam = new MyInterfaceParam() {
            @Override
            public void method(int x) {
                int result = x * 10;
                System.out.println("1. 기본 타입 메소드 = " + result);
            }
        };
        myInterfaceParam.method(500);


        // 2.매개변수 x값을 가지고 람다식을 구현한 매서드의 값
        MyInterfaceParam myInterfaceParam2 = null;

        myInterfaceParam2 = (int x) -> {
            int result = x * 10;
            System.out.println("\t2.매개변수 x값을 가지고 람다식을 구현한 매서드의 값 = " + result);
        };
        myInterfaceParam2.method(400);

        // 3. 매개타입 없애고, 변수선언부 없앰
        myInterfaceParam2 = x -> {
            //int result = x * 10;
            System.out.println("\t\t3. 매개타입 없애고, 변수선언부 없앰 = " + (x * 10) );
        };
        myInterfaceParam2.method(300);

        // 4. 가장 축약 한 형태
        myInterfaceParam2 = x -> System.out.println("\t\t\t4. 가장 축약 한 형태 = " + (x * 10) );
        myInterfaceParam2.method(200);

    }
}
