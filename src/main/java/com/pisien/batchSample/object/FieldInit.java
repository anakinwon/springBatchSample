package com.pisien.batchSample.object;

import java.util.Arrays;

/**
 *    <설계도, 붕어빵 틀>
 *
 *
 * */
public class FieldInit {

    // 기본형
    // 초기화를 하지 않아도 new 이용 시 자동 초기화 됨
    byte byteField;     // 1 Byte
    short shortFiled;   // 2 Byte
    int intFiled;       // 4 Byte
    long longFiled;     // 8 Byte

    boolean booleanFiled;  // 1 Byte
    char charFiled;     // 2 Byte

    float floatFiled;   // 4 Byte
    double doubleFiled; // 8 Byte

    // 참조형 변수
    int[] arrField;     // 4 Byte
    String strField;    // 4 Byte

    @Override
    public String toString() {
        return "FieldInit{" +
                "byteField=" + byteField +
                ", shortFiled=" + shortFiled +
                ", intFiled=" + intFiled +
                ", longFiled=" + longFiled +
                ", booleanFiled=" + booleanFiled +
                ", charFiled=" + charFiled +
                ", floatFiled=" + floatFiled +
                ", doubleFiled=" + doubleFiled +
                ", arrField=" + Arrays.toString(arrField) +
                ", strField='" + strField + '\'' +
                '}';
    }
}
