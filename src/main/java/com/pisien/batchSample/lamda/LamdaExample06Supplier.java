package com.pisien.batchSample.lamda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.*;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Supplier 예제 06.
 *       - 매개변수 없는 람다식
 *       - String Type 으로 Get()으로 반환값을 갖는다.
 *       - 추상 메소드 : get(), getInt(), getString()
 * */

public class LamdaExample06Supplier {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample06Supplier 의 로그");

    public static void main(String[] args) {
        
        /**
         *  Supplier<T> 함수적 인터페이스는 매개값이 없다. String 타입의 값을 리턴해 준다.(공급자)
         *  함수적 인터페이스의 추상메소드인 get()을 람다식 제공하여 호출한다.
         *  
         * */
        
        Supplier<String> supplier = () -> {
            String str = "자바공부를 합시다.";
            
            return  str;
        };
        String str = supplier.get();
        System.out.println("str = " + str);

        /**
         *  IntSupplier 함수적 인터페이스 역시 매개값이 없고, 반드시 int 타입(고정) 을 반환해야 한다.
         *  함수적 인터페이스의 추상메소드인 getAsInt() 를 람다식으로 구현해서 호출한다.
         * */
        IntSupplier intSupplier = () -> {
            int rand = (int)(Math.random()*45) + 1;
            return  rand;
        };
        int rand = intSupplier.getAsInt();
        System.out.println("rand = " + rand);
        
    }
}
