package org.main;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class IntegerArrayListTest {
    private final IntegerList integerList = new IntegerArrayList();
    @Test
    public void positiveAdd_Test(){
        int[] arr = Main.createArray(10_000, 1000_000);
        for (int i = 0; i < arr.length; i++) {
            integerList.add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            Assertions.assertEquals(arr[i], integerList.get(i));
        }
        Assertions.assertEquals(arr.length, integerList.size());
    }

    @Test
    public void positiveSet_Test(){
        int[] arr = Main.createArray(10_000, 1000_000);
        for (int i = 0; i < arr.length; i++) {
            integerList.add(arr[i]);
        }

        int[] arrToSet = Main.createArray(10_000, 100_000);
        for(int i = 0; i < arrToSet.length; i+=100){
            integerList.set(i, arrToSet[i]);
            Assertions.assertEquals(integerList.get(i), arrToSet[i]);
        }
    }

    @Test
    public void negativeSet_Test(){
        int[] arr = Main.createArray(10, 1000_000);
        for (int i = 0; i < arr.length; i++) {
            integerList.add(arr[i]);
        }

        Assertions.assertThrows(NullParameterException.class, ()->integerList.set(0, null));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->integerList.set(-1, 2));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->integerList.set(integerList.size(), 2));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->integerList.set(integerList.size() + 1, 2));
    }

    @Test
    public void positiveRemoveByItem_Test(){
        for (int i = 0; i < 1000; i++) {
            integerList.add(i + 1);
        }

        integerList.remove(Integer.valueOf(100));
        Assertions.assertFalse(integerList.contains(100));
        Assertions.assertEquals(999, integerList.size());
        integerList.remove(Integer.valueOf(200));
        Assertions.assertFalse(integerList.contains(200));
        Assertions.assertEquals(998, integerList.size());
        integerList.remove(Integer.valueOf(300));
        Assertions.assertFalse(integerList.contains(300));
        Assertions.assertEquals(997, integerList.size());
    }

    @Test
    public void negativeRemoveByItem_Test(){
        for (int i = 0; i < 1000; i++) {
            integerList.add(i + 1);
        }

        Assertions.assertThrows(IllegalArgumentException.class, ()->integerList.remove(Integer.valueOf(1001)));
        Assertions.assertThrows(IllegalArgumentException.class, ()->integerList.remove(Integer.valueOf(1002)));
        Assertions.assertThrows(IllegalArgumentException.class, ()->integerList.remove(Integer.valueOf(-1)));

        Assertions.assertThrows(NullParameterException.class, ()->integerList.remove(null));
    }

    @Test
    public void positiveRemoveByIndex_Test(){
        for (int i = 0; i < 1000; i++) {
            integerList.add(i + 1);
        }

        Assertions.assertEquals(1000, integerList.remove(999));
        Assertions.assertEquals(999, integerList.size());

        Assertions.assertEquals(1, integerList.remove(0));
        Assertions.assertEquals(998, integerList.size());

        Assertions.assertEquals(2, integerList.remove(0));
        Assertions.assertEquals(997, integerList.size());

    }

    @Test
    public void negativeRemoveByIndex_Test(){
        for (int i = 0; i < 1000; i++) {
            integerList.add(i + 1);
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->integerList.remove(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->integerList.remove(1000));
    }

    @Test
    public void positiveContains_Test(){
        int[] arr = Main.createArray(10_000, 100_000);
        for (int i = 0; i < arr.length; i++) {
            if(i == 100){
                integerList.add(100_000_000);
            }else if(i == 1000){
                integerList.add(100_000_001);
            }else if(i == 5000){
                integerList.add(100_000_002);
            }else {
                integerList.add(arr[i]);
            }
        }

        Assertions.assertTrue(integerList.contains(100_000_000));
        Assertions.assertTrue(integerList.contains(100_000_001));
        Assertions.assertTrue(integerList.contains(100_000_002));

        Assertions.assertFalse(integerList.contains(100_000_003));
        Assertions.assertFalse(integerList.contains(100_000_004));
        Assertions.assertFalse(integerList.contains(100_000_005));
    }

    @Test
    public void negativeContains_Test(){
        Assertions.assertThrows(NullParameterException.class, ()->integerList.contains(null));
    }

    @Test
    public void positiveIndexOf_Test(){
        for (int i = 0; i < 1000; i++) {
            integerList.add(i + 1);
        }

        Assertions.assertEquals(999, integerList.indexOf(1000));
        Assertions.assertEquals(499, integerList.indexOf(500));
        Assertions.assertEquals(99, integerList.indexOf(100));

        Assertions.assertEquals(-1, integerList.indexOf(10001));
        Assertions.assertEquals(-1, integerList.indexOf(10002));
        Assertions.assertEquals(-1, integerList.indexOf(10003));
    }

    @Test
    public void negativeIndexOf_Test(){
        for (int i = 0; i < 1000; i++) {
            integerList.add(i + 1);
        }

        Assertions.assertThrows(NullParameterException.class, ()->integerList.indexOf(null));
    }

    @Test
    public void positiveLastIndexOf_Test(){
        for (int i = 0; i < 1000; i++) {
            if(i == 100 || i == 200){
                integerList.add(-20);
            }else if(i == 300 || i == 400){
                integerList.add(-40);
            }else if(i == 500 || i == 600){
                integerList.add(-60);
            }else {
                integerList.add(i + 1);
            }
        }

        Assertions.assertEquals(200, integerList.lastIndexOf(-20));
        Assertions.assertEquals(400, integerList.lastIndexOf(-40));
        Assertions.assertEquals(600, integerList.lastIndexOf(-60));

        Assertions.assertEquals(-1, integerList.lastIndexOf(-80));
        Assertions.assertEquals(-1, integerList.lastIndexOf(-90));
        Assertions.assertEquals(-1, integerList.lastIndexOf(-100));
    }

    @Test
    public void negativeLastIndexOf_Test(){
        Assertions.assertThrows(NullParameterException.class, ()->integerList.lastIndexOf(null));
    }

    @Test
    public void positiveGet_Test(){
        for (int i = 0; i < 1000; i++) {
            integerList.add(i + 1);
        }

        Assertions.assertEquals(1, integerList.get(0));
        Assertions.assertEquals(100, integerList.get(99));
        Assertions.assertEquals(300, integerList.get(299));
        Assertions.assertEquals(700, integerList.get(699));
        Assertions.assertEquals(1000, integerList.get(999));
    }

    @Test
    public void negativeGet_Test(){
        for (int i = 0; i < 1000; i++) {
            integerList.add(i + 1);
        }

        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->integerList.get(-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->integerList.get(1000));
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()->integerList.get(2000));
    }

    @Test
    public void positiveEquals_Test(){
        IntegerList integerList1 = new IntegerArrayList();
        IntegerList integerList2 = new IntegerArrayList();

        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            int n = rnd.nextInt(1000);
            integerList.add(n);
            integerList1.add(n);
        }

        for (int i = 0; i < 10; i++) {
            integerList2.add(rnd.nextInt(1000));
        }

        Assertions.assertTrue(integerList.equals(integerList1));
        Assertions.assertFalse(integerList.equals(integerList2));

    }

    @Test
    public void sizeTest(){
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }
        Assertions.assertEquals(100, integerList.size());
        integerList.remove(0);
        Assertions.assertEquals(99, integerList.size());
        integerList.remove(0);
        integerList.remove(0);
        Assertions.assertEquals(97, integerList.size());
    }

    @Test
    public void isEmptyTest(){
        Assertions.assertTrue(integerList.isEmpty());

        integerList.add(1);
        Assertions.assertFalse(integerList.isEmpty());

    }

    @Test
    public void clearTest(){
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }
        integerList.clear();
        Assertions.assertTrue(integerList.isEmpty());
    }

    @Test
    public void toArrayTest(){
        IntegerList testList = new IntegerArrayList();

        Assertions.assertTrue(Arrays.equals(testList.toArray(), integerList.toArray()));
        Integer[] arr = new Integer[100];
        Integer[] arr1 = new Integer[100];

        for (int i = 0; i < 100; i++) {
            integerList.add(i);
            arr[i] = i;
            arr1[i] = i + 2;
        }

        Assertions.assertTrue(Arrays.equals(arr, integerList.toArray()));
        Assertions.assertFalse(Arrays.equals(arr1, integerList.toArray()));
    }

    @Test
    public void equals_Test(){
        IntegerList integerList1 = new IntegerArrayList();
        IntegerList integerList2 = new IntegerArrayList();

        for (int i = 0; i < 100; i++) {
            integerList.add(i);
            integerList1.add(i);
            if(i != 100/2){
                integerList2.add(i);
            }
        }

        Assertions.assertTrue(integerList.equals(integerList1));
        Assertions.assertFalse(integerList.equals(integerList2));
    }


}
