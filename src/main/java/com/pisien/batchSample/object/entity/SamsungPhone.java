package com.pisien.batchSample.object.entity;

import lombok.Getter;
import lombok.Setter;

/** 
 *  롬복 사용 테스트
 * 
 * */

@Getter @Setter //@ToString
public class SamsungPhone {

    /**
     *   1. 정적변수 (static변수)
     *      - 모두 공요하는 전역변수. 프로그램 시작 시 생성, 종료 시 소멸된다.
     *      - 클래스변수 = 정적변수 = static 변수, 메모리 최상단에 선언되며,
     *      - "클래스명.변수명"으로 어디서든 누구든지 접근가능하다.
     * */
    public static String country;

    /**
     *   2. 인스턴스 변수
     *      - 인스턴스 변수 멤버 변수(필드)
     *      - new 연산자로 생성한 인스턴스는 무저건 독립적인 저장공간을 지님
     *      - 서로 다른 값을 가짐.
     * */
    private String company;
    private String model;
    private String color;
    private int release;

    public void setCompany(String company) {
        /**
         *  1. 지역변수
         *     - 지역 변수는 반드시 값초기화 필요
         *     - 지역 변수로는 static 변수를 사용하지 못한다.
         *
         * */
        int i=0;
        if(!company.equals("삼성")) {
            System.out.println(" 회사 선택 오류! ");
            return;
        }
        this.company = company;
    }

    public void setModel(String model) {
        if(!(model.equals("갤럭시7") || model.equals("갤럭시8") || model.equals("갤럭시9") || model.equals("갤럭시10"))) {
            System.out.println(" 모델 선택 오류! ");
            return;
        }

        this.model = model;
    }

    @Override
    public String toString() {
        return "SamsungPhone{" +
                "company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", country='" + country + '\'' +
                ", release=" + release +
                '}';
    }
}
