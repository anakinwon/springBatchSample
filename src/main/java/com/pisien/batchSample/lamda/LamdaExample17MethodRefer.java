package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Calc;
import com.pisien.batchSample.lamda.entity.Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/**
 *  <표준 API의 함수적 인터페이스>
 *    메소드 참조
 *       - 정적 메소드 참조     : 바로 사용가능
 *       - 인스턴스 메소드 참조  : new 로 생성해야 사용 가능
 * */

public class LamdaExample17MethodRefer {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample17MethodRefer 의 로그");

    public static void main(String[] args) {

        // 매개변수와 반환값이 모두 동일해야 사용 가능
        // IntBinaryOperator 함수적 인터페이스는 2개의 int값 매개변수를 받아 연산하여
        // 하나의 int값으로 리턴하는 int applyAsInt(int, int) 추상메소드를 가지고 있다.
        IntBinaryOperator intBinaryOperator = null;

        // 람다식 구현
        intBinaryOperator = (x, y) -> { return Calc.sMethod(x,y); };
        System.out.println(" 람다식 결과1 = " + intBinaryOperator.applyAsInt(100, 200));
        

        intBinaryOperator = Calc::sMethod;
        System.out.println(" 정적 메소드 참조 결과1 = " + intBinaryOperator.applyAsInt(100, 200));
        intBinaryOperator = Calc::sMultiMethod;
        System.out.println(" 정적 메소드 참조 곱하기 결과1 = " + intBinaryOperator.applyAsInt(100, 200));

        System.out.println();
        // ===============================================================================================

        // 인스턴스 메소드 생성자
        Calc calc = new Calc();

        intBinaryOperator = (x, y) -> { return calc.iMethod(x, y); };
        System.out.println(" 인스턴스 메소드 람다식 결과1 = " + intBinaryOperator.applyAsInt(100, 200));
        intBinaryOperator = calc::iMethod;
        System.out.println(" 인스턴스 메소드 참조식 결과1 = " + intBinaryOperator.applyAsInt(100, 200));
        intBinaryOperator = calc::iMultiMethod;
        System.out.println(" 인스턴스 메소드 참조식 결과1 = " + intBinaryOperator.applyAsInt(100, 200));

    }
}
