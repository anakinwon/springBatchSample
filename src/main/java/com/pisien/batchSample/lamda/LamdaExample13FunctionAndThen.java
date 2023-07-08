package com.pisien.batchSample.lamda;

import com.pisien.batchSample.lamda.entity.Address;
import com.pisien.batchSample.lamda.entity.People;
import com.pisien.batchSample.lamda.entity.Population;
import com.pisien.batchSample.lamda.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *  <표준 API의 함수적 인터페이스>
 *    function andThen
 *    function compose
 *       - A.andThen(B)
 *       - C.compose(A)
 *       - 추상 메소드 : apply()
 * */

public class LamdaExample13FunctionAndThen {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample13FunctionAndThen 의 로그");
    public static ArrayList<Student> arrayList = new ArrayList<Student>();
    public static void main(String[] args) {

        // Function<T,R> 함수적인터페이스는 T를 매개값으로 주고 R로 매핑하여 반환한다.
        // 아래와 같이 apply() 추상매서드를 람다식으로 정의했다.
        // 결과는 andThen()을 통해서 People 을 전달하면, String을 얻는 결과가 나온다.
        // 여기서 Address sms functionA의 반환값으로, functionB의 매개값으로 사용된다.
        // 결과값이 country 또는 city 가 나오도록 중심축 역할을 한다.
        Function<People, Address> functionA = p -> {return p.getAddress();};
        Function<Address, String> functionB = p -> {return p.getCountry();};
        Function<Address, String> functionC = p -> {return p.getCity();};
        Function<People, String> functionAB = functionA.andThen(functionB);

        // A.andThen(B)
        String country = functionAB.apply( new People("아나킨", "anakin", new Address("코리아","성남")));
        System.out.println("국적 = " + country);

        // C.compose(A)
        Function<People, String> functionCA = functionC.compose(functionA);
        String city = functionCA.apply( new People("아나킨", "anakin", new Address("한국","서울")));
        System.out.println("도시 = " + city);


    }


}
