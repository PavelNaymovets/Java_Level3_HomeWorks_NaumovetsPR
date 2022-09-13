package ru.gb.naumovets.homeWork5;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static final Object mon = new Object();
    private static boolean winner = true;
    private static int CARS_COUNT;
    public static CountDownLatch countDownLatch;
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        countDownLatch = cdl;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        synchronized (mon){
            if(winner){
                System.out.println("Победитель " + this.getName());
                winner = false;
            }
        }
    }

}
