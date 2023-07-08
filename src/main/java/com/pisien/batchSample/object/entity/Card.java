package com.pisien.batchSample.object.entity;

public class Card {

    /**
     *  정적 변수(static 변수)  - 클래스 영역에 생성됨
     *   공유영역에서 전역변수로 공유됨.
     *   정적변수는 이탤릭체로 보인다.
     *   인스턴스 생성 없이 사용 가능
     * */
    public static int width  = 100;
    public static int height =  80;

    /**
     *  인스턴스 변수(Instance 변수)   - Heap 메모리 영역에 생성됨
     *   인스턴스 생성을 반드시 해야 사용 가능
     * */
    private String color;
    private String company;

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Card{" +
                " 카드색상 = '" + this.color + '\'' +
                ", 카드사 = '" + this.company + '\'' +
                ", 카드너비 = '" + Card.width + '\'' +    // Static 변수는 Class명.static변수명 으로 부터 접근함
                ", 카드높이 = '" + Card.height + '\'' +   // Static 변수는 Class명.static변수명 으로 부터 접근함
                '}';
    }
}
