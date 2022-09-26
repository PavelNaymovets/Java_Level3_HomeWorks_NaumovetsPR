package ru.gb.naumovets.infoFromLessons.lesson6.teachersExamples;

public class Calculator {

    public Integer sum(Integer a, Integer b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }

        return a + b;
    }

}
