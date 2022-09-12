package ru.gb.naumovets.infoFromLessons.lesson4;

public class Volatile {
    private static volatile int myInt = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    // main memory myInt = 2

    // cache1: myInt = 0                  cache2: myInt = 0
    // cache1: myInt = 0                  cache2: myInt = 1
    // cache1: myInt = 0                  cache2: myInt = 2

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int localVar = myInt;
            while (myInt < 5) {
                if (localVar != myInt) {
                    localVar = myInt;
                    System.out.println(localVar + " t1");
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int localVar = myInt; // 0
            while (myInt < 5) {
                myInt = ++localVar; // 1
                System.out.println(localVar + " t2");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
