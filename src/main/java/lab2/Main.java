package main.java.lab2;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int numOfThreads = 1000;

        List<MyThread> producer = new LinkedList<>();
        List<MyThread> consumer = new LinkedList<>();

        IntStream.range(0, numOfThreads).forEach(num -> producer.add(new MyThread(ThreadType.PRODUCER)));
        IntStream.range(0, numOfThreads).forEach(num -> consumer.add(new MyThread(ThreadType.CONSUMER)));

        consumer.forEach(Thread::start);
        producer.forEach(Thread::start);

        try {
            for (MyThread thread : producer) { thread.join(); }
            for (MyThread thread : consumer) { thread.join(); }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
