package ru.gb.naumovets.infoFromLessons.lesson7;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        Class<? extends String> stringClass = "Java".getClass();
        Class<Integer> integerClass = Integer.class;
        Class<Integer> intClass = int.class;
        Class<Void> voidClass = void.class;
        Class<char[]> charArrayClass = char[].class;
        Class<char[][]> charArrayClass_1 = char[][].class;

//        try {
//            Class jdbcClass = Class.forName("org.sqlite.jdbc");
//            System.out.println(jdbcClass);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println(stringClass);
        System.out.println(integerClass);
        System.out.println(intClass);
        System.out.println(voidClass);
        System.out.println(charArrayClass);
        System.out.println(charArrayClass_1);

        //Модификаторы доступа
        Class<String> strClass = String.class;
        int modifiers = strClass.getModifiers();
        if(Modifier.isPublic(modifiers)){
            System.out.println(strClass.getSimpleName() + " - public");
        }
        if(Modifier.isAbstract(modifiers)){
            System.out.println(strClass.getSimpleName() + " - abstract");
        }
        if(Modifier.isFinal(modifiers)){
            System.out.println(strClass.getSimpleName() + " - final");
        }

        //Доступ ко всем публичным полям объекта и его родителя
        Class<Cat> catClass = Cat.class;
        Field[] publicFields = catClass.getFields();
        for (Field field : publicFields) {
            System.out.println("Тип_поля Имя_поля : " + field.getType().getName() + " " + field.getName());
        }

        //Доступ  и изменение 1 публичного поля
        Cat cat = new Cat();
        Field name = cat.getClass().getField("name");
        name.set(cat, "мурзик");
        System.out.println(name.get(cat));
        Field age = cat.getClass().getField("age");
        age.set(cat, 10);
        System.out.println(age.get(cat));

        //Доступ и изменение 1 приватного поля. Иземнить final поля нельзя даже при помощи рефлексии
        Cat cat1 = new Cat(10);
        Field weight = cat.getClass().getDeclaredField("weight");
        weight.setAccessible(true);
//        System.out.println("get: " + weight.get(cat1));
        cat1.info();
        weight.set(cat1, 100);
        cat1.info();

        //Доступ к конструкторам класса
        Constructor[] constructors = Cat.class.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("---");
        try{
            System.out.println(Cat.class.getConstructor(String.class, int.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        //Доступ к методам
        Method[] methods = Cat.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getReturnType() + " ||| " + method.getName() + " ||| " + Arrays.toString(method.getParameterTypes()));
        }

        Cat cat2 = new Cat("Барсик");
        try {
            Method meow = cat2.getClass().getMethod("meow", int.class);
            meow.invoke(cat2, 5);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        //Доступ к конструктору
        Class someClass = Cat.class;
        Constructor catConstructor = Cat.class.getConstructor(String.class, String.class, int.class);
        try {
            Cat cat3 = (Cat)someClass.newInstance();//по умолчанию
            Cat cat4 = (Cat)catConstructor.newInstance("Мурзик", "Черный", 3);//с заданными параметрами
            System.out.println(cat3);
            System.out.println(cat4);
        } catch (InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
