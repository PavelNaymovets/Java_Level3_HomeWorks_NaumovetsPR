package ru.gb.naumovets.infoFromLessons.lesson6.teachersExample;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gb.naumovets.infoFromLessons.lesson6.Calculator;

import java.util.stream.Stream;

class TestCalculator {

    Calculator calculator;

    @BeforeAll
    static void runBeforeAll() {
        System.out.println("BEFORE ALL");
    }

    @AfterAll
    static void runAfterAll() {
        System.out.println("AFTER ALL");
    }

    @BeforeEach
    void runBeforeEach() {
        calculator = new Calculator();
        System.out.println("BEFORE EACH");
    }

    @AfterEach
    void runAfterEach() {
        calculator = new Calculator();
        System.out.println("AFTER EACH");
    }

    @ParameterizedTest
    @MethodSource("testSumArgumentsProvider")
    void testSum(int a, int b, int res) {
        Assertions.assertEquals(res, calculator.sum(a, b));
    }

    private static Stream<Arguments> testSumArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(5, 8, 13),
                Arguments.of(-2, -5, -7),
                Arguments.of(-5, 5, 0)
        );
    }

    @Test
    void testSumObject() {
        Integer result = calculator.sum(Integer.valueOf(5), Integer.valueOf(8));
        Assertions.assertEquals(13, result);
    }

    @Test
    void testSumNullFirstArguments() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.sum(null, 5));
    }

    @Test
    void testSumNullArguments() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.sum(null, null));
    }

}