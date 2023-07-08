package com.pisien.batchSample.object;

import java.util.Scanner;

/**
 *  <제곱근 함수 테스트>
 *    - 제곱근, Power
 *
 * */
public class ClassObjectExam15Power {

    int iv;
    static int cv;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int x = 0;
        int n = 0;
        long result = 0L;

        System.out.print(" 제곱하고 싶은 수를 입력 : ");
        x = scanner.nextInt();
        System.out.print(" 몇 승을 더할까요? : ");
        n = scanner.nextInt();

        for (int i=1; i<=n; i++) {
            result += power(x, i);
        }

        System.out.printf(" %d의 %d승 까지의 합은 %d입니다!", x, n, result);
        scanner.close();
    }

    public static long power(int x, int n) {

        if(n==1) {
            return x;
        }
        else {
            return x * power(x, n-1);
        }
    }

}

