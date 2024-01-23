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

    private Integer[] addCapacity() {
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
            array = addCapacity();
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
            array = addCapacity();
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
        Integer[] newArray = new Integer[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
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
                Integer tmp = array[i];
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
        sortInsertion(arr);
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

    private void sortInsertion(Integer[] arr) {
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
        return Arrays.equals(array, otherList.toArray());
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
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
