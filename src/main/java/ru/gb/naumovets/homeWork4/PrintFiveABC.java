package ru.gb.naumovets.homeWork4;

public class PrintFiveABC {

    private final Object mon = new Object();//Монитор - контролирует доступ к объекту
    private String literal = "A";//Литерал с которого начнется печать

    public static void main(String[] args) {
        PrintFiveABC printFiveABC = new PrintFiveABC();
        new Thread(() -> {
            printFiveABC.printA();
        }).start();
        new Thread(() -> {
            printFiveABC.printB();
        }).start();
        new Thread(() -> {
            printFiveABC.printC();
        }).start();
    }

    public void printA(){
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (literal !="A") {
                    try {
                        mon.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("A");
                literal = "B";
                mon.notifyAll(); //Если применить метод notify(), то после печати C, потоки с методами printB и printC - войдут в бесконечное ожидание.
            }
        }
    }

    public void printB(){
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (literal !="B") {
                    try {
                        mon.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("B");
                literal = "C";
                mon.notifyAll();
            }
        }
    }

    public void printC(){
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (literal !="C") {
                    try {
                        mon.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("C");
                literal = "A";
                mon.notifyAll();
            }
        }
    }
}
