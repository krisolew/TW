package main.java.lab1;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    static final Object LOCK = new Object();
    static final int NUMBER_OF_THREADS = 5;

    static int number = 0;

    public static void main(String[] args) {

        List<Printer> threads = new LinkedList<>();
        IntStream.range(0, NUMBER_OF_THREADS).forEach(i -> threads.add(new Printer(i)));
        threads.forEach(Thread::start);

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
