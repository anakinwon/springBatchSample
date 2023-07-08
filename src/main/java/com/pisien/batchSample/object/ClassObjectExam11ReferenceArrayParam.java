package com.pisien.batchSample.object;

/**
 *  <참조형 매개변수 테스트>
 *    - 배열은 주소를 가지고 있다.
 *    - 배열 값이 바뀌면 참조하고 있는 배열의 값도 바뀐다.
 *
 * */
public class ClassObjectExam11ReferenceArrayParam {

    public static void main(String[] args) {
        // 5개의 배열변수
        int[] arr = new int[] {10,20,30,40,50};
        System.out.println("change메소드 호출 전 = " + arr[0]);
        change(arr);
        System.out.println("change메소드 호출 후 = " + arr[0]);

        // 아래와 같이 주소를 넘겨도 된다.
        change(new int[] {10,20,30});
        System.out.println("change메소드 호출 후 = " + arr[0]);

    }

    public static void change(int[] arr) {
        arr[0] = 999;
        System.out.println("메소드 내 값 = " + arr[0]);
    }

}

