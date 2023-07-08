package com.pisien.batchSample.object;

/**
 *    <Static 맴버변수와 인스턴스 맴버 변수와의 차이 알아보기>
 *        - static 은 static 만 호출 가능하다.
 *        - 인스턴스는 모두 호출 가능하다.
 *
 * */
public class Member {
    static int cv = 200;    // static  맴버 변수
    int iv = 100;           // 인스턴스 맴버 변수

    public void instanceMethod() {
        //System.out.println("iv = " + iv);       // 된다.
        //System.out.println("cv = " + cv);       // 된다.
        System.out.println("iv = " + this.iv);    // 된다.
        System.out.println("cv = " + Member.cv);  // 된다.
    }

    public static void staticMethod() {
        System.out.println("cv = " + cv);         // 된다.
        //System.out.println("iv = " + iv);       // 안된다.
        System.out.println("cv = " + Member.cv);  // 된다.
        //System.out.println("iv = " + this.iv);  // 안된다.

    }

}
