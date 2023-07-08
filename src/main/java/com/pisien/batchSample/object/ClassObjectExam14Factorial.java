package com.pisien.batchSample.object;

/**
 *  <재귀함수 테스트>
 *    - 재귀함수, Factorial
 *
 * */
public class ClassObjectExam14Factorial {

    public static void main(String[] args) {

        long result = factorial(5L);
        System.out.println("5펙토리얼의 결과값 = " + result);
    }

    public static long factorial(long n) {
        long result = 0L;

        if (n==1) {
            return 1;
        }
        else {
            // 인수를 감소 시키면서 자기를 다시 호출한다.
            result = n * factorial(n-1);
            System.out.println("result = " + result);
        }
        return result;
    }

}

