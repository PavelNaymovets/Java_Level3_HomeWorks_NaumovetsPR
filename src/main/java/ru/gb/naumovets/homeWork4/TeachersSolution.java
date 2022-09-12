package ru.gb.naumovets.homeWork4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TeachersSolution {
    private static final char FIRST = 'A';
    private static final char LAST = 'D';
    private char letter = FIRST;

    public synchronized void tryPrint(char target) {
        while(letter != target){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(target);
        next();
        notifyAll();
    }

    private void next() {
        if(letter == LAST){
            letter = FIRST;
        } else {
            letter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int repeat = 5;

        TeachersSolution solution = new TeachersSolution();
        List<Thread> threads = IntStream.rangeClosed(FIRST, LAST)
                .mapToObj(it -> (char) it)
                .map(it -> new Thread(() -> {
                    for (int i = 0; i < repeat; i++) {
                        solution.tryPrint(it);
                    }
                }))
                .peek(Thread::start)
                .collect(Collectors.toList());
        for (Thread thread : threads) {
            thread.join();
        }
    }

    // Без Stream API
//        List<Thread> threads = new ArrayList<>();
//        for (char current = FIRST; current <= LAST; current++) {
//            final char it = current;
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < repeat; j++) {
//                    container.tryPrint(it);
//                }
//            });
//
//            thread.start();
//            threads.add(thread);
//        }

}
