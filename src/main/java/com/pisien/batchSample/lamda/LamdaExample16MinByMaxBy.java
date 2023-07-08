package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Fruit;
import com.pisien.batchSample.lamda.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.FacesRequestAttributes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 *  <표준 API의 함수적 인터페이스>
 *    MinBy MaxBy
 *       - MinBy 둘 중 작은 값 반환
 *       - MaxBy 둘 중 큰 값 반환
 *       - 추상 메소드 : compare()
 * */

public class LamdaExample16MinByMaxBy {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample16MinByMaxBy 의 로그");

    public static void main(String[] args) {

        BinaryOperator<Fruit> binaryOperator = null;
        Fruit fruit = null;

        // BinaryOperator 의 minBy 정적 메소드는 작은쪽 객체를 반환한다.
        // 기준이 되는 필드를 선택할 수 있다.
        binaryOperator = BinaryOperator.minBy( (f1, f2) -> {
            return Integer.compare(f1.getPrice(), f2.getPrice());
        });
        
        fruit = binaryOperator.apply(new Fruit("황도", 25000), new Fruit("백도",35000));
        System.out.println(" 더 저렴한 과일명 = " + fruit.getName());

        // BinaryOperator 의 MaxBy 정적 메소드는 큰쪽 객체를 반환한다.
        binaryOperator = BinaryOperator.maxBy( (f1, f2) -> {
            return Integer.compare(f1.getPrice(), f2.getPrice());
        });

        fruit = binaryOperator.apply(new Fruit("황도", 25000), new Fruit("백도",35000));
        System.out.println(" 더 비싼 과일명 = " + fruit.getName());
    }
}
