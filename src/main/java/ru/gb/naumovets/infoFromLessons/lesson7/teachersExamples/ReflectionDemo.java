package ru.gb.naumovets.infoFromLessons.lesson7.teachersExamples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {

    public static void main(String[] args) throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchFieldException {

        Class<Person> personClass = Person.class;

        Constructor<Person> personConstructor = personClass.getConstructor(String.class);
        Person john = personConstructor.newInstance("John"); // Person john = new Person("John", 20);
        System.out.println(john);

        Method setAgeMethod = personClass.getMethod("setAge", int.class);
        setAgeMethod.invoke(john, 20); // john.setAge(20);
        System.out.println(john);

        Method getCounterMethod = personClass.getMethod("getCounter");
        Object getCounterResult = getCounterMethod.invoke(null); // int counter = Person.getCounter();
        System.out.println("Person counter: " + getCounterResult);

        Method nameLengthMethod = personClass.getDeclaredMethod("nameLength");
        nameLengthMethod.setAccessible(true);
        Object nameLength = nameLengthMethod.invoke(john);
        System.out.println("Length of name: " + nameLength);

        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(john, "John 2"); // john.name = "John 2"
        System.out.println(john);

    }

}
