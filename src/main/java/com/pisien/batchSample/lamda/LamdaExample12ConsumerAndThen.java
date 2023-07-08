package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.People;
import com.pisien.batchSample.lamda.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Consumer andThen 예제 12.
 *       - A.andThen(B)
 *       - 추상 메소드 : apply()
 * */

public class LamdaExample12ConsumerAndThen {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample12ConsumerAndThen 의 로그");
    public static ArrayList<Student> arrayList = new ArrayList<Student>();
    public static void main(String[] args) {

        // Consumer<T> 함수적인터페이스는 매개값 P를 받아와서 단순히 소비하는 역할만 한다.
        // 아래와 같이 accept() 추상매서드를 람다식으로 정의했다.
        Consumer<People> consumerA = p -> {
            System.out.println("ConsumerA = " + p.getName());
        };
        Consumer<People> consumerB = p -> {
            System.out.println("ConsumerB = " + p.getId());
        };

        // 위의 정의된 2개의 함수적인 인터페이스 consumerA 와 consumerB 를 가지고 코딩하면.
        // consumerA가 먼저 실행되고, consumerB가 뒤에 실행되어 출력된다.
        Consumer<People> consumerAB = consumerA.andThen(consumerB);
        consumerAB.accept(new People("아나킨","anakin",null));

    }


}
