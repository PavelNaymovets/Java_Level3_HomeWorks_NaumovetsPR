package ru.gb.naumovets.infoFromLessons.lesson7;

import java.lang.reflect.Method;

public class MainDemo {
    public static void main(String[] args) {
        //Доступ к аннотации
        Cat cat = new Cat();
        try {
            Method method = cat.getClass().getMethod("info", null);
            AdvancedAnnotation annotation = method.getAnnotation(AdvancedAnnotation.class);
            if(annotation != null){
                System.out.println(annotation);
            }
            System.out.println("value: " + annotation.value());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
