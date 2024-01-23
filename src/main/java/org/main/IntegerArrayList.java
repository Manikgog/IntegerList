package org.main;

import java.util.Arrays;

public class IntegerArrayList implements IntegerList {
    private Integer[] array;
    private int size;
    private int capacity;
    public IntegerArrayList() {
        size = 0;
        capacity = 10;
        array = new Integer[capacity];
    }

    private Integer[] grow() {
        capacity = ((capacity * 3) / 2) + 1;
        Integer[] newArray = new Integer[capacity];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    @Override
    public Integer add(Integer item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        if (size == capacity) {
            array = grow();
            size++;
            array[size - 1] = item;
            return item;
        }
        size++;
        array[size - 1] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if(size == capacity){
            array = grow();
        }
        size++;
        for (int i = size - 1; i > index; i--) {
            Integer tmp = array[i];
            array[i] = array[i - 1];
            array[i - 1] = tmp;
        }
        array[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        int index = indexOf(item);
        if (index != -1) {
            return remove(index);
        }
        throw new IllegalArgumentException();
    }
    private void shrinkToSize() {
        capacity = size;
        array = Arrays.copyOf(array, size);
    }
    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Integer item = array[index];
        array[index] = null;
        if (index < size - 1) {
            for (int j = index + 1, i = index; j < size; j++, i++) {
                array[i] = array[j];
                array[j] = null;
            }
        }
        size--;
        if (size > 0) {
            if (capacity / size > 2) {
                shrinkToSize();
            }
        }

        return item;
    }

    @Override
    public boolean contains(Integer item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        Integer[] arr = Arrays.copyOf(array, size);
        quickSort(arr, 0, arr.length - 1);
        return isContain(arr, item);
    }

    private boolean isContain(Integer[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    public void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    @Override
    public int indexOf(Integer item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if(item == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if(otherList == null){
            throw new NullParameterException("Переданный параметр равен null.");
        }
        return Arrays.equals(Arrays.copyOf(array, size), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        capacity = 10;
        array = new Integer[capacity];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, size);
    }
}
