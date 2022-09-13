package ru.gb.naumovets.infoFromLessons.lesson7.teachersExamples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnnotationDemo {

    /*
     * Annotation members:
     * A primitive type
     * String
     * Class or an invocation of Class (§4.5)
     * An enum type
     * An annotation type
     * An array type whose component type is one of the preceding types (§10.1)
     *
     * https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.6.1
     */

    public static void main(String[] args) throws NoSuchMethodException {
        Class<Person> personClass = Person.class;

        if (personClass.isAnnotationPresent(Anno.class)) {
            Anno annotation = personClass.getAnnotation(Anno.class);
            System.out.println("value: " + annotation.value());
            System.out.println("param: " + annotation.param());
            System.out.println("intArrayParam: " + Arrays.toString(annotation.intArrayParam()));
        }

        Method getNameMethod = personClass.getMethod("getName");
        Anno methodAnnotation = getNameMethod.getAnnotation(Anno.class);
        if (methodAnnotation != null) {
            System.out.println(Arrays.toString(methodAnnotation.intArrayParam()));
        }

        Constructor<Person> constructor = personClass.getConstructor(String.class);
        Parameter[] parameters = constructor.getParameters();
        for (Parameter parameter : parameters) {
            Anno annotation = parameter.getAnnotation(Anno.class);
            if (annotation != null) {
                System.out.println(annotation.param());
            }
        }

    }

}
