package ru.gb.naumovets.infoFromLessons;

import java.util.concurrent.*;

public class ExecuteService {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //Создаем 10 потоков
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 100; i++) {
//            Thread.sleep(1000);
//            for (int j = 0; j < 100; j++) {
//                //Отдаем задание 10 потокам
//                service.execute(()->{
//                    System.out.println(1);
//                });
//            }
//            //Заканчиваем работу потоков, чтобы не тратить ресурсы
//            service.shutdown();
//        }

        //Future хранят в себе информацию о выполняемой в параллельном потоке задаче
        ExecutorService service1 = Executors.newFixedThreadPool(4);
        service1.execute(() -> {
            System.out.println("Асинхронная задача");
        });
        Future future = service1.submit(() -> {
            System.out.println("Асинхронная задача");
        });
        System.out.println(future.get()); // null если задача завершилась

        Future<String> future1 = service1.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Асинхронный вызов");
                return "Результат из потока";
            }
        });
        System.out.println("future.get() = " + future1.get());
        service1.shutdown();
    }
}
