package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.function.ToIntFunction;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Function 예제 08.
 *       - 매개변수와 반환값이 모두 있다.
 *       - 매개변수를 동일하게 반환값으로 전달한다.
 *       - 추상 메소드 : apply(), applyAsInt(), applyAsDouble()
 * */

public class LamdaExample08Function {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample08Function 의 로그");
    private static ArrayList<Student> arrayList = new ArrayList<Student>();

    public static void main(String[] args) {

        arrayList.add(new Student("홍길동", "서울", 100, 80, 96));
        arrayList.add(new Student("진도준", "원주", 95, 98, 86));
        arrayList.add(new Student("한성주", "분당", 88, 86, 100));
        arrayList.add(new Student("이일화", "강남", 97, 82, 86));

        // 실행블럭에서 어떤 메소드를 호출하느냐에 따라 점수가 달라짐을 확인할 수 있다.
        //ToIntFunction<Student> toIntFunction1 = t -> {return t.getKoreanScore(); };
        ToIntFunction<Student> toIntFunction1 = t -> t.getKoreanScore();
        // 매개값으로 applyAsInt() 추상메소드의 실체 람다식을정의한 toIntFunction1을 전달한다.
        average(toIntFunction1,"국어");

        //ToIntFunction<Student> toIntFunction2 = t -> {return t.getEnglishScore(); };
        ToIntFunction<Student> toIntFunction2 = t -> t.getEnglishScore();
        average(toIntFunction2,"영어");

        //ToIntFunction<Student> toIntFunction3 = t -> {return t.getMathScore(); };
        ToIntFunction<Student> toIntFunction3 = t -> t.getMathScore();
        average(toIntFunction3,"수학");
    }

    // average()에서 매개변수타입이  ToIntFunction<Student>를 구현한 객체가 넘어와야 함.
    public static void average (ToIntFunction<Student> function, String str) {

        int sum = 0;
        double avg = 0.0;

        for (Student student : arrayList) {
            int score = function.applyAsInt(student);
            sum += score;
        }
        avg = (double) sum / arrayList.size();
        System.out.println(str + "점수 평균 = " + avg);
    }


}
