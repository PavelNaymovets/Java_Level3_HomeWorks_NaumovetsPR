package ru.gb.naumovets.homeWork5;

import ru.gb.naumovets.infoFromLessons.lesson5.CountDownLatchDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    private final static CountDownLatch countDownLatch = new CountDownLatch(CARS_COUNT);
    private final static Semaphore smp = new Semaphore(CARS_COUNT/2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(smp), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), countDownLatch);
        }
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < cars.length; i++) {
            Thread thread = new Thread(cars[i]);
            thread.start();
            threads.add(thread);
        }
        Car.countDownLatch.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (Thread thread : threads) {
            //Жду завершения всех потоков
            thread.join();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

}
