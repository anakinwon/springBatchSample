package com.pisien.batchSample.object.entity;

public class Monitor {

    // 맴버 변수(필드)
    private String color;
    private int channel;
    private int volume;
    private boolean power;

    public String getColor() {        return color;    }
    public int getChannel() {        return channel;    }
    public boolean isPower() {        return power;    }
    public int getVolume() {        return volume;    }

    // 맴버 메소드
    // public 접근제어자는 누구나 접근할 수 있다.
    public void power() {
        this.power = !power;  // toggle 시키기.
        if(this.power) {
            System.out.println("모니터가 켜졌습니다.");
        }
        else {
            System.out.println("모니터가 꺼졌습니다.");
        }
    }

    public void channelUp() {   channel++;    }
    public void channelDown() { channel--;    }

    public void volumeUp() {    volume++;    }
    public void volumeDown() {  volume--;    }


    @Override
    public String toString() {
        return  "Monitor{" +
                "  color   ='" + color + '\'' +
                ", channel =" + channel +
                ", volume  =" + volume +
                ", power   =" + power +
                '}';
    }
}
