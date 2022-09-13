package ru.gb.naumovets.homeWork7;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//Класс имеет метод .start(Class<?> testClass){} который запускает методы согласно аннотациям и заданному порядку. С начала
//@beforeSuit затем @Test с указанным ордером и затем @AfterSuit.
public class TestExecutor {

    public static void start(Class<?> testClass){
        try{
            startUnsafe(testClass);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void startUnsafe(Class<?> testClass) throws Exception{
        Method[] methods = testClass.getDeclaredMethods(); //собираю методы из тестового класса

        Method beforeSuitMethod = null;
        List<Method> testMethods = new ArrayList<>();
        Method afterSuitMethod = null;

        for (Method method : methods) { //прохожусь по методам. Собираю их в переменные согласно указанным аннотациям
            if(method.isAnnotationPresent(BeforeSuit.class)){
                if(beforeSuitMethod != null){
                    throw new RuntimeException("Аннотации @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре");
                }
                beforeSuitMethod = method;
            }

            if(method.isAnnotationPresent(AfterSuit.class)){
                if(afterSuitMethod != null){
                    throw new RuntimeException("Аннотации @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре");
                }
                afterSuitMethod = method;
            }

            if(method.isAnnotationPresent(Test.class)){
                testMethods.add(method);
            }
        }


        testMethods.sort((o1, o2) -> { //соратирую методы с аннотацией @Test, согласно ордеру
            Test o1Annotation = o1.getAnnotation(Test.class);
            Test o2Annotation = o2.getAnnotation(Test.class);

            return Integer.compare(o2Annotation.order(), o1Annotation.order());
        });

        Constructor<?> classT = testClass.getDeclaredConstructor();
        TestClass classTest = (TestClass)classT.newInstance();//создаю экземпляр класса testClass для дальнейшего вызова нестатических методов

        if(beforeSuitMethod != null){ //вызов метода с аннотацией @BeforeSuit
            beforeSuitMethod.invoke(classTest);
        }

        for (Method testMethod : testMethods) { //вызов метода с аннотацией @Test
            testMethod.invoke(classTest);
        }

        if(afterSuitMethod != null){ //вызов метода с аннотацией @AfterSuit
            afterSuitMethod.invoke(classTest);
        }

    }

}
