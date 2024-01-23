package org.main;

import java.lang.*;
import java.util.Random;

public class Constants {
    public static final int INTEGER_1 = 4;
    public static final int INTEGER_2 = 6;
    public static final int INTEGER_3 = 3;
    public static final int INTEGER_4 = 1;
    public static final int INTEGER_5 = 5;
    public static final int INTEGER_6 = 2;
    public static final int INTEGER_7 = 7;
    public static final int INTEGER_8 = 8;
    public static final int INTEGER_9 = 11;
    public static final int INTEGER_10 = 10;
    public static final int INTEGER_11 = 9;

    public static final Integer[] arrayIntegers = new Integer[]{
            INTEGER_1,
            INTEGER_2,
            INTEGER_3,
            INTEGER_4,
            INTEGER_5,
            INTEGER_6,
            INTEGER_7,
            INTEGER_8,
            INTEGER_9,
            INTEGER_10,
            INTEGER_11
    };

    public static final Integer[] arrayStringsWithEqualsStrings = new Integer[]{
            INTEGER_1,
            INTEGER_2,
            INTEGER_7,
            INTEGER_3,
            INTEGER_4,
            INTEGER_5,
            INTEGER_1,
            INTEGER_6,
            INTEGER_7,
            INTEGER_8,
            INTEGER_2,
            INTEGER_9,
            INTEGER_10
    };

    public static Integer[] createArray(int amountOfElements, int maxElement){
        Integer[] array = new Integer[amountOfElements];
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(maxElement);
        }
        return array;
    }
}
