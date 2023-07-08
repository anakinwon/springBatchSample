package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Predicate
 *       - isEqual
 *       -  null    null     --> true
 *       -  null    notnull  --> false
 *       -  notnull null     --> false
 *       - 추상 메소드 : test()
 * */

public class LamdaExample15PredicateIsEqual {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample15PredicateIsEqual 의 로그");
    public static ArrayList<Student> arrayList = new ArrayList<Student>();
    public static void main(String[] args) {

        // 둘 다 null 일 때 true 로 반환
        Predicate<String> predicate = null;
        boolean result = false;

        predicate = Predicate.isEqual(null);
        result = predicate.test(null);
        System.out.println(" 두 객체가 null 일 때??? = " + result);

        // 하나 이상 null 일 때 false 로 반환
        predicate = Predicate.isEqual(null);
        result = predicate.test("아나킨");
        System.out.println(" 하나만 null 일 때??? = " + result);

    }


}
