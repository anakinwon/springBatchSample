package com.pisien.batchSample.object;


/**
 *  <참조형 매개변수 테스트>
 *    - Call Stack(후입선출, LIFO) 예제
 *    - Call By Value(값 참조) : readOnly, 속성
 *  (출력 예)
 *  1. Class : 100
 * 		2.firstMethod In : 1000
 * 			3.secondMethod In : 10000
 * 				4.thirdMethod In : 100000
 * 				4.thirdMethod Out: 100000
 * 			3.secondMethod Out: 10000
 * 		2.firstMethod Out: 1000
 * 	1. Class : 100
 * */
public class ClassObjectExam09PrimitiveParam {

    public static void main(String[] args) {
        Class01 num = new Class01();
        num.data = 100;
        System.out.println("\t1. Class : " + num.data);
        firstMethod(num.data);
        System.out.println("\t1. Class : " + num.data);
    }

    public static void firstMethod(int num) {
        num = 1000;
        System.out.println("\t\t2.firstMethod In : "+ num);
        secondMethod(num);
        System.out.println("\t\t2.firstMethod Out: "+ num);
    }
    public static void secondMethod(int num) {
        num = 10000;
        System.out.println("\t\t\t3.secondMethod In : "+ num);
        thirdMethod(num);
        System.out.println("\t\t\t3.secondMethod Out: "+ num);
    }
    public static void thirdMethod(int num) {
        num = 100000;
        System.out.println("\t\t\t\t4.thirdMethod In : "+ num);
        System.out.println("\t\t\t\t4.thirdMethod Out: "+ num);
    }
}

class Class01 {
    int data ;
}
