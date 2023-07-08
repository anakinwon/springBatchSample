package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 *  <표준 API의 함수적 인터페이스>
 *    Predicate 예제 11.
 *       - 매개값을 조사해서 true 또는 false 값을 반환한다.
 *       - 추상 메소드 : test()
 * */

public class LamdaExample11Predicate {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample11Predicate 의 로그");
    public static ArrayList<Student> arrayList = new ArrayList<Student>();
    public static void main(String[] args) {

        // 객체 값 추가
        arrayList.add(new Student("홍길동", 98, "남자"));
        arrayList.add(new Student("진도준", 87, "남자"));
        arrayList.add(new Student("한성주", 96, "여자"));
        arrayList.add(new Student("이일화", 94, "여자"));

        // Predicate<T> 함수적 인터페이스는  test()의 추상메소드의 실체를 람다식으로 구현
        // 남자 혹은 여자에 따라서 구분하여 값을 계산함
        //Predicate<Student> predicateMale   = t -> { return t.getSex().equals("남자"); };
        Predicate<Student> predicateMale   = t -> t.getSex().equals("남자");

        //Predicate<Student> predicateFemale = t -> { return t.getSex().equals("여자"); };
        Predicate<Student> predicateFemale = t -> t.getSex().equals("여자");

        double avgMale = avg(predicateMale);
        System.out.println("남자평균 = " + avgMale);

        double avgFemale = avg(predicateFemale);
        System.out.println("여자평균 = " + avgFemale);

    }

    // Predicate<T> 함수적 인터페이스는 test()의 추상메소드를 가지고 있다.
    // 아울러, 매개변수로  Predicate<Student>로 받고, test() 추상메소드를 사용한다.
    public static double avg(Predicate<Student> predicate) {
        int cnt = 0;
        int sum = 0;

        for (Student student : arrayList) {
            if (predicate.test(student)) {
                cnt++;
                sum += student.getScore();
            }
        }
        return (double) sum/cnt;
    }





}
