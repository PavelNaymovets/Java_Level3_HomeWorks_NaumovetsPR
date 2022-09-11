package ru.gb.naumovets.infoFromLessons.lesson4;

public class RaceCondition {

    //Монитор - контролирует доступ к объекту
    public static class Counter {
        private int value;

        public int getValue() {
            return value;
        }

        public synchronized void incrementValue() {
            value++;
        }

        public synchronized void decrementValue() {
            value--;
        }
    }

    //Синхронизация метода через POJO (Plain Old Java Object - обычные джава объекты)
    public static class SynchDoubleCounter {
        private long first;
        private long second;
        private Object monFirst = new Object();
        private Object monSecond = new Object();

        public long getFirst() {
            return first;
        }

        public long getSecond() {
            return second;
        }

        public void incrementFirst() {
            synchronized (monFirst) {
                first++;
            }
        }

        public void incrementSecond() {
            synchronized (monSecond) {
                second++;
            }
        }

        public void decrementFirst() {
            synchronized (monFirst) {
                first--;
            }
        }

        public void decrementSecond() {
            synchronized (monSecond) {
                second--;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.incrementValue();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.decrementValue();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(counter.getValue());
    }
}

