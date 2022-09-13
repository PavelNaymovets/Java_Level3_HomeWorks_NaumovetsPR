package ru.gb.naumovets.homeWork5.TeachersSolution;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.function.Consumer;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    private final CyclicBarrier prepareCdl;
    private final Consumer<Car> finishCallback;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier prepareCdl, Consumer<Car> finishCallback) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;

        this.prepareCdl = prepareCdl;
        this.finishCallback = finishCallback;

    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            prepareCdl.await(); // Ждем остальных
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        finishCallback.accept(this); // Вызываем callback об окончании.
    }
}