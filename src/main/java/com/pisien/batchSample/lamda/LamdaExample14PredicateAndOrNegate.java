package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.function.IntPredicate;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Predicate
 *       - And
 *       - Or
 *       - Negate
 *       - 추상 메소드 : test()
 * */

public class LamdaExample14PredicateAndOrNegate {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample14PredicateAndOrNegate 의 로그");
    public static ArrayList<Student> arrayList = new ArrayList<Student>();
    public static void main(String[] args) {

        //
        IntPredicate predicateA = x -> { return (x % 2) == 0; };   // 2의 배수 Boolean 반환
        IntPredicate predicateB = x -> { return (x % 3) == 0; };   // 3의 배수 Boolean 반환

        System.out.println("15는 2의 배수? = " + predicateA.test(15));
        System.out.println("15는 3의 배수? = " + predicateB.test(15));

        // AND
        IntPredicate predicateABAnd = predicateA.and(predicateB);
        System.out.println("6은 2와 3의 배수? = " + predicateABAnd.test(6));
        System.out.println("7은 2와 3의 배수? = " + predicateABAnd.test(7));
        System.out.println("8은 2와 3의 배수? = " + predicateABAnd.test(8));

        // OR
        IntPredicate predicateABOr = predicateA.or(predicateB);
        System.out.println("6은 2 또는 3의 배수? = " + predicateABOr.test(6));
        System.out.println("7은 2 또는 3의 배수? = " + predicateABOr.test(7));
        System.out.println("8은 2 또는 3의 배수? = " + predicateABOr.test(8));
        System.out.println("9은 2 또는 3의 배수? = " + predicateABOr.test(9));

        // NEGATE
        IntPredicate predicateABNegateOdd = predicateA.negate();
        System.out.println("2는 홀수? = " + predicateABNegateOdd.test(2));
        System.out.println("3는 홀수? = " + predicateABNegateOdd.test(3));
        IntPredicate predicateABNegateEven = predicateA.negate();
        System.out.println("2은 짝수? = " + predicateABNegateEven.test(2));
        System.out.println("3은 짝수? = " + predicateABNegateEven.test(3));

    }


}
