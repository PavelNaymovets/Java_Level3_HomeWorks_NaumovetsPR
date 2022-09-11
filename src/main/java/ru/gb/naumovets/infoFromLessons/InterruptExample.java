package ru.gb.naumovets.infoFromLessons;

public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            boolean interrupted = false; // переменная нужна, чтобы прервать поток
            for (int i = 0; i < 10; i++) {
                if(Thread.currentThread().isInterrupted() || interrupted){ //проверяем boolean interrupted переменную потока. Иначе поток не остановится
                    break;
                }
                System.out.println(i);
                try{
                    Thread.sleep(500);
                } catch(InterruptedException e){
                    interrupted = true;
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
