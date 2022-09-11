package ru.gb.naumovets.infoFromLessons.lesson4;

public class ThreadCreation {

    public static void main(String[] args) {
        //Первый способ запуска потока. Интерфейс ранабл
        Thread thr1 = new Thread(new MyRunnableClass());
        Thread thr2 = new Thread(new MyRunnableClass());
        thr1.start();
        System.out.println(thr1.getName());
        thr2.start();
        System.out.println(thr2.getName());


        //Второй способ запуска потока. Класс Тред
        Thread thr3 = new MyThreadClass();
        Thread thr4 = new MyThreadClass();
        thr3.start();
        thr4.start();

        //Третий способ запуска потока. Анонимный класс
        Thread thr5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }
            }
        });

        //.join. Ожидание завершения работы другим потоком
        Thread thr6 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("*");
            }
        });
        thr6.start();
        try {
            thr6.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("END");
    }
}

class MyRunnableClass implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
}

class MyThreadClass extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
}
