package ru.gb.naumovets.infoFromLessons.lesson5;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        final int THREAD_COUNT = 6; //Задаю количество потоков
        System.out.println("Начинаем");
        final CountDownLatch cdl = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    //Выполняю задачу
                    Thread.sleep(500 + (int)(500 * Math.random()));
                    // как только задача выполнена, уменьшаем счетчик
                    cdl.countDown();
                    System.out.println("Поток #" + w + " - готов");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        //пока счетчик не приравняется нулю, будем стоять на этой строке
        cdl.await();
        //как только потоки выполнили свои задачи пишем сообщение
        System.out.println("Работа завершена");
    }

}
