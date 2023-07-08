package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Function 예제 09.
 *       - 매개변수와 반환값이 모두 있다.
 *       - 매개변수를 동일하게 반환값으로 전달한다.
 *       - 추상 메소드 : apply(), applyAsInt(), applyAsDouble()
 * */

public class LamdaExample09Function {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample09Function 의 로그");
    private static ArrayList<Population> population = new ArrayList<Population>();

    public static void main(String[] args) {

        // Population 객체에 값 추가하기
        population.add(new Population("서울 강남구", 250000));
        population.add(new Population("서울 서초구", 230000));
        population.add(new Population("서울 종로구", 210000));
        population.add(new Population("서울 송파구", 240000));

        //Function<Population, String> function = t -> {return t.getRegion();};
        Function<Population, String> function = t -> t.getRegion();
        regionPring(function);

        //ToDoubleFunction<Population> function2 = t -> {return t.getPopulation();};
        ToDoubleFunction<Population> function2 = t -> t.getPopulation();
        avgPrint(function2);

    }

    // 지역을 출력하는 메소드 생성
    public static void regionPring(Function<Population, String> function) {
        for (Population population1 : population) {
            String region = function.apply(population1);
            System.out.print("지역 = " + region + "\t");
        }
        System.out.println();
    }

    // 각 지역에 인구수와 평균 인구수 출력
    public static void avgPrint(ToDoubleFunction<Population> toDoubleFunction) {
        double sum = 0.0;

        for (Population population1 : population) {
            double value = toDoubleFunction.applyAsDouble(population1);
            System.out.print("인구수 = " + value + "\t");
            sum += value;
        }
        System.out.println();
        System.out.println("서울인구 평균 = " + (sum/population.size()));
    }




}
