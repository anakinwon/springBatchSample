package com.pisien.batchSample.object.entity;

public class Time {
    /**
     *  <접근제어자 (Access Modifier)>
     *    - ** private   : 같은 클래스 내에서만 접근 가능
     *    -    protected : 같은 패키지, 자식 클래스에서만 접근 가능
     *    -    default   : 같은 패키지에서만 접근 가능
     *    - ** public    : 어디서든 누구나 접근 가능
     *
     * */
    private int hour;
    private int minute;
    private int second;

//    public Time() {}
//
//    public Time(int hour, int minute, int second) {
//        this.hour = hour;
//        this.minute = minute;
//        this.second = second;
//    }

    public int getHour() {   return hour;   }
    public int getMinute() { return minute; }
    public int getSecond() { return second; }

    /**
     * Setter 에서는 입력 Validation 처리가 가능하다.
     * */
    public void setHour(int hour) {
        if (hour<0 || hour>23) {
            System.out.println(" 시간 입력 오류 ");
            return;
        }
        this.hour = hour;
    }
    public void setMinute(int minute) {
        if (minute<0 || minute>59) {
            System.out.println(" 분 입력 오류 ");
            return;
        }
        this.minute = minute;
    }
    public void setSecond(int second) {
        if (second<0 || second>59) {
            System.out.println(" 초 입력 오류 ");
            return;
        }
        this.second = second;
    }

    @Override
    public String toString() {
        return "Time{" +
                "  hour   =" + hour +
                ", minute =" + minute +
                ", second =" + second +
                '}';
    }
}
