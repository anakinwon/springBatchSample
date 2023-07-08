package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Function 예제 07.
 *       - 매개변수와 반환값이 모두 있다.
 *       - 매개변수를 동일하게 반환값으로 전달한다.
 *       - 추상 메소드 : apply(), applyAsInt(), applyAsDouble()
 * */

public class LamdaExample07Function {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample07Function 의 로그");

    private static List<Student> list = Arrays.asList(
            new Student("홍길동", "서울", 100, 80, 96),
            new Student("진도준", "원주", 95, 98, 86),
            new Student("한성주", "분당", 88, 86, 100),
            new Student("이일화", "강남", 97, 82, 86)
    );


    public static void main(String[] args) {
        
        /**
         *  Supplier<T> 함수적 인터페이스는 매개값이 없다. String 타입의 값을 리턴해 준다.(공급자)
         *  함수적 인터페이스의 추상메소드인 get()을 람다식 제공하여 호출한다.
         *  
         * */

        // 문자열 계산해서 호출하기
        Function<Student, String> function = t -> { return t.getName()+ ", " +t.getAddress(); };
        printString(function);

        // 숫자 계산해서 호출하기
        //ToIntFunction<Student> toIntfunctionEng = t -> { return t.getEnglishScore(); };
        ToIntFunction<Student> toIntfunctionEng = t -> t.getEnglishScore();
        intPrint(toIntfunctionEng, "영어");
        //ToIntFunction<Student> toIntfunctionMath = t -> { return t.getMathScore(); };
        ToIntFunction<Student> toIntfunctionMath = t -> t.getMathScore();
        intPrint(toIntfunctionMath, "수학");
        //ToIntFunction<Student> toIntfunctionKor = t -> { return t.getKoreanScore(); };
        ToIntFunction<Student> toIntfunctionKor = t -> t.getKoreanScore();
        intPrint(toIntfunctionKor, "국어");
    }
    public static void printString(Function<Student, String> function) {
        for (Student student : list) {
            String str = function.apply(student);
            System.out.println("이름 = " + str + "\t");
        }
        System.out.println("");
    }

    public static void intPrint(ToIntFunction<Student> function, String str) {
        for (Student student : list) {
            int score = function.applyAsInt(student);
            System.out.print(str +" = " + score +"\t");
        }
        System.out.println();
    }

}
