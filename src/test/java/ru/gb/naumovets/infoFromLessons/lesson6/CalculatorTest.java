package ru.gb.naumovets.infoFromLessons.lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @ParameterizedTest
    @MethodSource("testSumArgumentsProvider")
    void sum(int a, int b, int res) {
        Assertions.assertEquals(res,calculator.sum(a,b));
    }

    private static Stream<Arguments> testSumArgumentsProvider(){
        return Stream.of(
                Arguments.of(0,0,0),
                Arguments.of(1,1,2),
                Arguments.of(3,8,11),
                Arguments.of(-2,1,-1)
        );
    }
}