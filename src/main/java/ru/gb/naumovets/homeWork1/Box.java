package ru.gb.naumovets.homeWork1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Box<T extends Fruit> {
    private ArrayList<T> boxFruit;

    public Box(T... fruits) {
        this.boxFruit = Arrays.stream(fruits).collect(Collectors.toCollection(ArrayList::new));
    }

    public double getWeight() {
        if (boxFruit == null || boxFruit.size() == 0) {
            return 0.0;
        }
        double result = 0.0;
        for (T fruit : boxFruit) {
            result += fruit.getWeight();
        }
        return result;
    }

    public void moveTo(Box<? super T> anotherBox){
        anotherBox.addAllFruits(boxFruit);
        boxFruit.clear();
    }

    private void addAllFruits(ArrayList<? extends T> anotherList){
        boxFruit.addAll(anotherList);
    }

    public boolean compare(Box<? extends Fruit> anotherBox){
        double compare = this.getWeight() - anotherBox.getWeight();
        return compare == 0;
    }

}
