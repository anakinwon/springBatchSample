package com.pisien.batchSample.lamda.entity;

// 메소드 참고가 가능하려면???
// 매개변수와 반환값이 모두 동일해야 사용 가능
public class Calc {

    // 정적 메소드 : 메모리에 직접 할당하기 때문에 직접 호출 가능
    // 사용법 : Calc.sMethod
    public static int sMethod(int x, int y) {
        return x + y;
    }
    public static int sMultiMethod(int x, int y) {
        return x * y;
    }

    // 인스턴스 메소드 : 힙메모리를 사용하므로 new 로 생성해서 호출해야 사용 가능함.
    // 사용법 : new Calc.iMethod
    public int iMethod(int x, int y) {
        return x + y;
    }
    public int iMultiMethod(int x, int y) {
        return x + y;
    }
}
