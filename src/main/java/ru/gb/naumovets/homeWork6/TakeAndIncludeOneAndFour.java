package ru.gb.naumovets.homeWork6;

import java.util.Arrays;

public class TakeAndIncludeOneAndFour {

    public int[] takeNumbersAfterFour(int[] arr) {
        if (arr.length == 0) {
            throw new RuntimeException("Массив пустой");
        }
        int index = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                index = i;
                count++;
            }
        }
        int[] newArr;
        if (count == 0) {
            throw new RuntimeException("Массив не содержит цифры 4");
        } else {
            newArr = new int[arr.length - (index + 1)];
            for (int i = index + 1, k = 0; i < arr.length; i++, k++) {
                newArr[k] = arr[i];
            }
        }
        return newArr;
    }

    public boolean includeOneAndFour(int[] arr) {
        if (arr.length == 0) {
            throw new RuntimeException("Массив пустой.");
        }
        int countOne = 0;
        int countFour = 0;
        int anotherNumber = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                countOne++;
            } else if (arr[i] == 4) {
                countFour++;
            } else {
                anotherNumber++;
            }
        }
        return !(countOne == 0 || countFour == 0 || anotherNumber != 0);
    }

}
