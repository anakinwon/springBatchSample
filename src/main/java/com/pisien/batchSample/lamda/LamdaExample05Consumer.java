package com.pisien.batchSample.lamda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.*;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Consumer 예제 05.
 *       - 매개변수 포함 람다식
 *       - 추상 메소드 : accept()
 * */

public class LamdaExample05Consumer {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample05Consumer 의 로그");

    public static void main(String[] args) {
        
        /**
         *  String 타입을 매개변수로 가지는 Consumer 제너릭 함수적 인터페이스를 구현했다.
         *  t는 무조건 String타입이고, 매개변수가 된다.
         *  Consumer 인터페이스류들은 리턴값이 존재하지 않는다.
         *  (t) -> { System.out.println("consumer Interface = " + t);} --> 바로 accept()를 람다식으로 구현함.
         * */
        Consumer<String> consumer = (t) -> {
            System.out.println("consumer Interface = " + t);
        };
        consumer.accept("소비자 인터페이스는 자바 1.8에서 추가되었다.");

        /**
         *  BiConsumer <T, T>
         *      - 두개의 매개변수를 갖는다.
         *      - 반환값은 없다
         * */
        BiConsumer<String, String> biConsumer = (t,u) -> {
            System.out.println("BiConsumer Interface : t = " + t + ", u = " + u);
        };
        biConsumer.accept("자바", "C++");

        /**
         *  DoubleConsumer <T, T>
         *      - Double 형 매개변수를 갖는다.
         *      - 반환값은 없다
         * */
        DoubleConsumer doubleConsumer = d -> {
            System.out.println("DoubleConsumer Interface : double = " + d);
        };
        doubleConsumer.accept(3.14);

        /**
         *  DoubleConsumer <T, T>
         *      - Sting + int 형 매개변수를 갖는다.
         *      - 반환값은 없다
         * */
        ObjIntConsumer  objIntConsumer = (t,i) -> {
            System.out.println("ObjIntConsumer Interface : " + t + " " + i);
        };
        objIntConsumer.accept("Java version is", 8);

        /**
         *  DoubleConsumer <T, T>
         *      - Sting + int 형 매개변수를 갖는다.
         *      - 반환값은 없다
         * */
        ObjDoubleConsumer objDoubleConsumer = (t, d) -> {
            System.out.println("ObjDoubleConsumer Interface : " + t + " " + d);
        };
        objDoubleConsumer.accept("Java version is", 1.8);
    }
}
