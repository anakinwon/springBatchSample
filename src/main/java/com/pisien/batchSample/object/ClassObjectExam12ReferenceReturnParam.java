package com.pisien.batchSample.object;

/**
 *  <참조변수 반환 테스트>
 *    - 참조변수 반환 테스트.
 *
 * */
public class ClassObjectExam12ReferenceReturnParam {

    public static void main(String[] args) {
        // 5개의 배열변수
        int[] origin = new int[] {10,20,30,40,50};
        int[] cloned = new int[5];
        System.out.println("copy 메소드 호출 전 origin = " + origin[0]+","+origin[1]+","+origin[2]+","+origin[3]+","+origin[4]);
        System.out.println("copy 메소드 호출 전 cloned = " + cloned[0]+","+cloned[1]+","+cloned[2]+","+cloned[3]+","+cloned[4]);

        cloned = copy(origin);
        System.out.println("copy 메소드 호출 후 cloned = " + cloned[0]+","+cloned[1]+","+cloned[2]+","+cloned[3]+","+cloned[4]);
    }

    public static int[] copy(int[] arr) {
        int[] temp = new int[5];

        System.arraycopy(arr,0,temp,0,arr.length);

        return temp;
    }

}

