package com.pisien.batchSample.lamda.entity;

public class Student {
    private String name;
    private String address;
    private int englishScore;
    private int mathScore;
    private int koreanScore;
    private int score;
    private String sex;

    public Student(String name, int score, String sex) {
        this.name = name;
        this.score = score;
        this.sex = sex;
    }

    public int getScore() {
        return score;
    }

    public String getSex() {
        return sex;
    }

    public Student(String name, String address, int englishScore, int mathScore, int koreanScore) {
        this.name = name;
        this.address = address;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
        this.koreanScore = koreanScore;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public int getEnglishScore() {
        return englishScore;
    }
    public int getMathScore() {
        return mathScore;
    }
    public int getKoreanScore() {
        return koreanScore;
    }

}
