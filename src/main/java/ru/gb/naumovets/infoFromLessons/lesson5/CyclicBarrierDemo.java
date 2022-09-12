package ru.gb.naumovets.infoFromLessons.lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //CyclicBarrier  в отличии от CountDownLatch сам отсчитывает счетчик а заданной точке. В этот момент остальные потоки ждут
        final int Threads_count = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(Threads_count);
        for (int i = 0; i < Threads_count; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("Подготавливается " + index);
                    Thread.sleep(2000 + 500 * (int) (Math.random() * 10));
                    System.out.println("Готов " + index);
                    cyclicBarrier.await();
                    System.out.println("Поехал " + index);
                    Thread.sleep(2000 + 500 * (int) (Math.random() * 10));
                    System.out.println("Доехал " + index);
                    cyclicBarrier.await();
                    System.out.println("Гонка закончилась");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
