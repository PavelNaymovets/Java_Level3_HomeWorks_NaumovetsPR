package ru.gb.naumovets.homeWork1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main<E> {

    public static void main(String[] args) {
        // 1 задание
        List<Double> arrDouble = Arrays.asList(1.0, 2.0, 3.0);
        List<String> arrString = Arrays.asList("A", "B", "C");

        System.out.println("Массив до применения метода swap()");
        System.out.println(arrDouble);
        System.out.println(arrString);

        try {
            swap(arrDouble, 0, 1);
            swap(arrString, 0, 1);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Массив после применения метода swap()");
        System.out.println(arrDouble);
        System.out.println(arrString);

        // 2 задание
        Integer[] arrInteger = new Integer[]{1, 2, 3, 4, 5};
        ArrayList<Integer> newArr = new Main<Integer>().convertToArrayLyst(arrInteger);
        System.out.println(newArr);

        // 3 задание

        Box<Orange> orangeBox = new Box<>(
                new Orange(),
                new Orange(),
                new Orange(),
                new Orange(),
                new Orange()
        );

        Box<Orange> orangeBox_2 = new Box<>(
                new Orange(),
                new Orange(),
                new Orange(),
                new Orange(),
                new Orange()
        );

        Box<Apple> appleBox = new Box<>(
                new Apple(),
                new Apple(),
                new Apple(),
                new Apple(),
                new Apple()
        );

        Box<GoldenApple> goldenAppleBox = new Box<>(
                new GoldenApple(),
                new GoldenApple(),
                new GoldenApple(),
                new GoldenApple(),
                new GoldenApple()
        );

        System.out.println("Весл коробки orangeBox до пересыпания " + orangeBox.getWeight());
        System.out.println("Весл коробки orangeBox_2 до пересыпания " + orangeBox_2.getWeight());
        orangeBox.moveTo(orangeBox_2);
        System.out.println("Весл коробки orangeBox после пересыпания " + orangeBox.getWeight());
        System.out.println("Весл коробки orangeBox_2 после пересыпания " + orangeBox_2.getWeight());

        System.out.println("Весл коробки goldenAppleBox до пересыпания " + goldenAppleBox.getWeight());
        System.out.println("Весл коробки appleBox до пересыпания " + appleBox.getWeight());
        goldenAppleBox.moveTo(appleBox);
        System.out.println("Весл коробки goldenAppleBox после пересыпания " + goldenAppleBox.getWeight());
        System.out.println("Весл коробки appleBox после пересыпания " + appleBox.getWeight());

        System.out.println();
        System.out.println("Сравниваю вес коробок. Можно сравнивать коробки с разными фруктами." + "\n" + "true - массы коробок равны" + "\n" + "false - массы коробок не равны" + "\n");
        System.out.println("Масса appleBox = " + appleBox.getWeight());
        System.out.println("Масса orangeBox = " + orangeBox.getWeight());
        System.out.println("Масса goldenAppleBox = " + goldenAppleBox.getWeight());
        System.out.println("Сравниваю appleBox и orangeBox " + appleBox.compare(orangeBox));
        System.out.println("Сравниваю appleBox и appleBox " + appleBox.compare(appleBox));
        System.out.println("Сравниваю appleBox и goldenAppleBox " + appleBox.compare(goldenAppleBox));
        System.out.println("Сравниваю orangeBox и goldenAppleBox " + orangeBox.compare(goldenAppleBox));


    }

    // 1 задание
    private static <T> void swap(List<T> arr, int firstIndex, int secondIndex) throws IllegalAccessException {
        if (firstIndex > arr.size() - 1 || secondIndex > arr.size() - 1) {
            throw new IllegalArgumentException("Индекс за пределами массива");
        } else if (firstIndex < 0 || secondIndex < 0) {
            throw new IllegalArgumentException("Индекс меньше нуля");
        } else if (arr == null) {
            throw new IllegalArgumentException("Массив не передан");
        }
        T buf = arr.get(firstIndex);
        arr.set(firstIndex, arr.get(secondIndex));
        arr.set(secondIndex, buf);
    }


    // 2 задание
    private ArrayList<E> convertToArrayLyst(E[] arr) {
        ArrayList<E> newArr = new ArrayList<>(Arrays.asList(arr));
        return newArr;
    }

}
