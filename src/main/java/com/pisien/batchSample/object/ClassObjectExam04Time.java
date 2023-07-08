package com.pisien.batchSample.object;

import com.pisien.batchSample.object.entity.Time;

public class ClassObjectExam04Time {

    public static void main(String[] args) {

        Time time = new Time();
        // 시간 설정
        time.setHour(24);
        // 분 설정
        time.setMinute(60);
        // 초 설정
        time.setSecond(60);

        System.out.println(" What Time is it now ? " + time.toString());

    }

}

