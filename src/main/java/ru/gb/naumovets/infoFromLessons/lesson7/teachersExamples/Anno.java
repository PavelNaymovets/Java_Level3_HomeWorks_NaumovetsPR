package ru.gb.naumovets.infoFromLessons.lesson7.teachersExamples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
public @interface Anno {

    int value() default -1;

    String param() default "default-value";

    boolean booleanParam() default false;

    long longDefaultParam() default -1;

    String stringParam() default "";

//    Class<?> classParam();
//
//    Override overrideParam();

    int[] intArrayParam() default {};

}
