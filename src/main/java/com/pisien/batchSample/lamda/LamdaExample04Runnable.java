package com.pisien.batchSample.lamda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Runnable 예제 04.
 *      - 매개변수 포함 람다식
 * */

public class LamdaExample04Runnable {
    private final Logger logger = LoggerFactory.getLogger("LamdaExample04Runnable 의 로그");

    public static void main(String[] args) {
        
        /**
         *  전통적인 익명구현객체를 만드는 방법
         * */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++) {
                    System.out.println("i = " + i);                    
                }      
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("thread = " + thread);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("=============================");

        /**
         *  (방법1)
         *  람다식으로 직접 작성하여 Thread 의 매개값으로 반환하는 코드
         *  단축키 : Alt + Enter
         * */
        runnable = () -> {
            for (int i=0; i<10; i++) {
                System.out.println("i = " + i);
            }
        };
        thread = new Thread(runnable);
        thread.start();

        /**
         *  (방법2)
         *  Thread 객체를 생성할 때, 바로 람다식을 작성하는 방식
         * */
        thread = new Thread( () -> {
            for (int i=0; i<10; i++) {
                System.out.println("i = " + i);
            }
        });
        thread.start();;

    }
}
