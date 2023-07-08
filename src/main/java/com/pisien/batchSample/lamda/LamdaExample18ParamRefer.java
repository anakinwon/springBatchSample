package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Calc;
import com.pisien.batchSample.lamda.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.config.PointcutEntry;
import org.springframework.data.relational.core.sql.In;

import javax.annotation.security.PermitAll;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

/**
 *  <표준 API의 함수적 인터페이스>
 *    매개변수의 메소드 참조
 *       - 정적 메소드 참조     : 바로 사용가능
 *       - 인스턴스 메소드 참조  : new 로 생성해야 사용 가능
 * */

public class LamdaExample18ParamRefer {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample18ParamRefer 의 로그");

    public static void main(String[] args) {

        // 람다식으로 생성자를 호출하여 인스턴스를 만드는 방식.
        Function<String, People> function = (x) -> { return new People(x); };
        People people = function.apply("아나킨");
        System.out.println("Actor = " + people);

        // 생성자 참조 방식.
        function = People::new;
        people = function.apply("파드메");
        System.out.println("Actress = " + people);

        System.out.println("===============================");
        // 람다식으로 생성자를 호출하여 인스턴스를 만드는 방식.
        BiFunction<String, Integer, People> function1 = (x, y) -> { return new People(x, y); };
        people = function1.apply("아나킨", 25);
        System.out.println("주인공 = " + people);

        // 생성자 참조 방식.
        function1 = People::new;
        people = function1.apply("파드메", 28);
        System.out.println("여주인공 = " + people);


    }
}
