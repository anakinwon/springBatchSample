package com.pisien.batchSample.object;

import com.pisien.batchSample.object.entity.Card;
import com.pisien.batchSample.object.implement.Calculator;

public class ClassObjectExam07Calculator {

    public static void main(String[] args) {
        /**
         *  Static 변수는 전역변수이므로,
         *  공유 변수는 이탤릭체로 보인다.
         *  공용으로 쓰는 변수이므로, 가급적 바꾸지 마라
         * */

        Calculator calc = new Calculator();

        int add =
                calc.add(10, 20);
        int subtract =
                calc.subtract(30, 10);
        long multiply =
                Calculator.multiply(20, 50);
        double divide =
                Calculator.divide(100, 5);

        System.out.println("add      = " + add);
        System.out.println("subtract = " + subtract);
        System.out.println("multiply = " + multiply);
        System.out.println("divide   = " + divide);

    }
}

