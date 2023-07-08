package com.pisien.batchSample.object;

import com.pisien.batchSample.object.entity.Card;

public class ClassObjectExam06VariableMethod {

    public static void main(String[] args) {
        /**
         *  Static 변수는 전역변수이므로,
         *  공유 변수는 이탤릭체로 보인다.
         *  공용으로 쓰는 변수이므로, 가급적 바꾸지 마라
         * */
        //Card.height =  85;
        //Card.width  = 105;

        Card card = new Card();
        card.setColor("노랑");
        card.setCompany("KB카드");

        System.out.println(" 내가 만든 카드정보 = " + card.toString());
    }
}

