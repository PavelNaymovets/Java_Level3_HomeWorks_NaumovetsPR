package ru.gb.naumovets.homeWork6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TestTakeAndIncludeOneAndFour {

    TakeAndIncludeOneAndFour testObj;

    @BeforeEach
    void init(){
        testObj = new TakeAndIncludeOneAndFour();
    }

    @ParameterizedTest
    @MethodSource("dataForArrayTakeNumberAfterFour")
    void takeNumbersAfterFour(int[] arr, int[] result) {
        Assertions.assertArrayEquals(result, testObj.takeNumbersAfterFour(arr));
    }

    @ParameterizedTest
    @MethodSource("dataForArrayIncludeOneAndFour")
    void includeOneAndFour(int[] arr, boolean result) {
        Assertions.assertEquals(result, testObj.includeOneAndFour(arr));
    }

    public static Stream<Arguments> dataForArrayTakeNumberAfterFour(){
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] {1,2,3,4,5,6,7},new int[]{5,6,7}));
        out.add(Arguments.arguments(new int[] {1,2,3,4,5,4,7},new int[]{7}));
        out.add(Arguments.arguments(new int[] {1,2,3,4,5,6,7,4},new int[]{}));
        out.add(Arguments.arguments(new int[] {4,1,2,3,5,6,7},new int[]{1,2,3,5,6,7}));
        out.add(Arguments.arguments(new int[] {1,2,3,4,5,4,6,7},new int[]{6,7}));
        return out.stream();
    }

    public static Stream<Arguments> dataForArrayIncludeOneAndFour(){
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] {1,1,1,1,1,1,1},false));
        out.add(Arguments.arguments(new int[] {4,4,4,4,4,4,4},false));
        out.add(Arguments.arguments(new int[] {1,4,1,4,1,4,1,4},true));
        out.add(Arguments.arguments(new int[] {1,4,1,4,1,4,3},false));
        out.add(Arguments.arguments(new int[] {1,4},true));
        return out.stream();
    }

}