package org.main;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static int AMOUNT_OF_SORTING_ITER = 10;
    public static int AMOUNT_OF_ELEMENTS = 100_000;
    public static int MAX_ELEMENT_VALUE = 1000_000;


    public static void main(String[] args) {
        double sumTimeByBubbleSort = 0d;
        double sumTimeBySelectionSort = 0d;
        double sumTimeByInsertionSort = 0d;
        System.out.println("\ttimeByBubbleSort ms" + "\t\t" + "timeBySelectionSort ms" + "\t\t" + "timeByInsertionSort ms");
        for (int i = 0; i < AMOUNT_OF_SORTING_ITER; i++) {
            int[] arr = createArray(AMOUNT_OF_ELEMENTS, MAX_ELEMENT_VALUE);
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);

            int timeByBubbleSort = toFindTimeOfSortByBubbleSort(arr);
            sumTimeByBubbleSort += timeByBubbleSort;
            int timeBySelectionSort = toFindTimeOfSortBySelectionSort(arr1);
            sumTimeBySelectionSort += timeBySelectionSort;
            int timeByInsertionSort = toFindTimeOfSortByInsertionSort(arr2);
            sumTimeByInsertionSort += timeByInsertionSort;
            System.out.println((i+1) + ".\t" + timeByBubbleSort + "\t\t\t\t\t\t" + timeBySelectionSort + "\t\t\t\t\t\t\t" + timeByInsertionSort);
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Среднее время:\n\t" + sumTimeByBubbleSort/AMOUNT_OF_SORTING_ITER + "\t\t\t\t\t"
                                            + sumTimeBySelectionSort/AMOUNT_OF_SORTING_ITER + "\t\t\t\t\t\t"
                                            + sumTimeByInsertionSort/AMOUNT_OF_SORTING_ITER);
    }

    public static int toFindTimeOfSortByBubbleSort(int[] arr){
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        return (int) (System.currentTimeMillis() - start);
    }

    public static int toFindTimeOfSortBySelectionSort(int[] arr){
        long start = System.currentTimeMillis();
        sortSelection(arr);
        return (int) (System.currentTimeMillis() - start);
    }

    public static int toFindTimeOfSortByInsertionSort(int[] arr){
        long start = System.currentTimeMillis();
        sortInsertion(arr);
        return (int) (System.currentTimeMillis() - start);
    }

    public static int[] createArray(int amountOfElements, int maxElement){
        int[] array = new int[amountOfElements];
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(maxElement);
        }
        return array;
    }

    public static int[] bubbleSort(int[] arr){

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}