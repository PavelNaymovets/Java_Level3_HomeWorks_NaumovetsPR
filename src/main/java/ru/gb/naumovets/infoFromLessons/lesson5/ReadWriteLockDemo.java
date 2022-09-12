package ru.gb.naumovets.infoFromLessons.lesson5;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    //ReadWriteLock позволяет множеству потоков одновременно читать данные, или только одному потоку их записывать
    public static void main(String[] args) {
        final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        for (int i = 0; i < 3; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    readWriteLock.readLock().lock();
                    System.out.println("Начало чтения - " + index);
                    shortSleep(1000);
                    System.out.println("Завершение чтения - " + index);
                } finally {
                    readWriteLock.readLock().unlock();
                }
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    readWriteLock.writeLock().lock();
                    System.out.println("Начало записи - " + index);
                    shortSleep(1000);
                    System.out.println("Завершение записи - " + index);
                } finally {
                    readWriteLock.writeLock().unlock();
                }
            }).start();
        }
    }

    public static void shortSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
