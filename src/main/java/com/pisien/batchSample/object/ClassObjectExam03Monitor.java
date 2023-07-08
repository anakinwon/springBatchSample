package com.pisien.batchSample.object;

import com.pisien.batchSample.object.entity.Monitor;

public class ClassObjectExam03Monitor {

    public static void main(String[] args) {

        // 붕어빵 찍기.
        // 설계도를 가지고 제품을 생산한다.
        // fi는 리모콘 같은 개념  (fi. 찍으면, 맴버변수들 보여준다.)
        Monitor monitor = new Monitor();

        // 모니터 켜기
        monitor.power();
        // 모니터 끄기
        monitor.power();

        // 채널 올리기
        monitor.channelUp();
        monitor.channelUp();
        monitor.channelUp();
        System.out.println("channel = " + monitor.getChannel());

        // 채널 내리기
        monitor.channelDown();
        monitor.channelDown();
        monitor.channelDown();
        System.out.println("channel = " + monitor.getChannel());

        // 볼륨 올리기
        monitor.volumeUp();
        monitor.volumeUp();
        monitor.volumeUp();
        monitor.volumeUp();
        monitor.volumeUp();
        monitor.volumeUp();
        System.out.println("volume = " + monitor.getVolume());

        monitor.toString();

    }

}

