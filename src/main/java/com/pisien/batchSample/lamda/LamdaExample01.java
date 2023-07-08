package com.pisien.batchSample.lamda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 람다식 예제 01.
 *
 * */


public class LamdaExample01 {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample01 의 로그");

    public static void main(String[] args) {

        /**
         *  MyInterface.method() 이용 : Body 가 없는 추상메소드임.
         *  람다식을 이용해서 익명구현객체를 생성하고 있는 것이다.
         *  매개변수가 없을 땐, ()는 생략 불가
         *  {}는 추상메소드인 method()의 구현부가 들어가야 하며,
         *  여기서 2줄이기 때문에 {}를 생략할 수 없다.
         *
         * */
        MyInterface myInterface1 = new MyInterface() {  //  메소드명에서 "Alt + Enter" 하면 람다로 변환가능
            @Override
            public void method() {
                String str = "1. 일반 메소드로 만든 구현체";
                System.out.println(str);
            }
        };

        MyInterface myInterface2 = () -> {
            String str = "\t2. 람다식으로 만든 익명구현체입니다.";
            System.out.println(str);
        };

        myInterface1.method();
        myInterface2.method();
        myInterface2 = () -> {System.out.println("\t\t3. 람다식으로 축약으로 만든 익명구현체입니다.");};
        myInterface2.method();

        System.out.println("\t");
        
        // 실행문이 한 줄 일때는 중괄호 생략
        myInterface2 = () -> System.out.println("\t\t\t3. 람다식으로 중괄호 생략");
        myInterface2.method();

    }
}
