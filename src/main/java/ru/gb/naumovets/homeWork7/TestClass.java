package ru.gb.naumovets.homeWork7;

public class TestClass {

    //Методы с аннотациями
    @BeforeSuit
    public static void before(){
        System.out.println("BeforeSuit");
    }

    @Test(order = 10)
    public void testOrder_10_Method(){
        System.out.println("order = 10");
    }

    @Test
    public void testDefaultOrderMethod(){
        System.out.println("order = 1(default)");
    }

    @Test(order = 5)
    public void testOrder_5_Method(){
        System.out.println("order = 5");
    }

    @Test(order = 5)
    public void testOrder_5_Method_2(){
        System.out.println("order = 5");
    }

    @AfterSuit
    public void after(){
        System.out.println("AfterSuit");
    }
}
