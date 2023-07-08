package com.pisien.batchSample.lamda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.IntBinaryOperator;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Operator 예제 10.
 *       - 함수와 유사하게 매개변수와 반환값이 있지만,
 *       - 매개값을 이용하여 연산을 수행한 후 동일한 타입으로 반환한다.
 *       - 추상 메소드 : apply(), applyAsInt(), applyAsDouble()
 * */

public class LamdaExample10Operator {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample09Function 의 로그");

    private static int[] scores = {98, 97, 96, 85, 89, 76, 85, 96, 78, 83};

    public static void main(String[] args) {

        // Operator 를 활용하여 최대값 구하기.
        IntBinaryOperator operatorMax = (a, b) -> {
//            if (a>=b)
//                return a;
//            else
//                return b;
            return Math.max(a,b);
        };
        int maxValue = maxOrMin(operatorMax);
        System.out.println("최대값 = " + maxValue);

        // Operator 를 활용하여 최소값 구하기.
        // operator = (a, b) -> { return Math.min(a,b);  };
        IntBinaryOperator operatorMin = (a, b) -> Math.min(a,b);
        int minValue = maxOrMin(operatorMin);
        System.out.println("최소값 = " + minValue);

    }

    // 최대 최소값 구하는 인터페이스 함수 생성
    public static int maxOrMin(IntBinaryOperator operator) {
        int result = scores[0];
        for (int score : scores) {
            result = operator.applyAsInt(result, score);
        }
        return result;
    }


}
