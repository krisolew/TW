package main.java.lab2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static DummyBufor dummyBufor;
    public static StringFileWriter writer;
    public final static Random random = new Random();

    public static void main(String[] args) {
        int capacity = 1000;
        int numOfThreads = 1000;
        int range = capacity/2;

        dummyBufor = new DummyBufor(0, capacity);
        try {
            writer = new StringFileWriter("src/main/resources/lab2/times.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ThreadsExecutor producerExecutor = new ThreadsExecutor();
//        ThreadsExecutor consumerExecutor = new ThreadsExecutor();
        List<MyThread> producer = new LinkedList<>();
        List<MyThread> consumer = new LinkedList<>();

        for (int i = 0; i < numOfThreads; i++) {
            producer.add(new MyThread(getRandomPortion(ThreadType.PRODUCER, range)));
            consumer.add(new MyThread(getRandomPortion(ThreadType.CONSUMER, range)));
        }

        consumer.forEach(Thread::start);
        producer.forEach(Thread::start);

//        try {
//            for (MyThread thread : producer) {
//                thread.join();
//            }
//            for (MyThread thread : consumer) {
//                thread.join();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer.forEach(Thread::stop);
        producer.forEach(Thread::stop);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getRandomPortion(ThreadType type, int range) {
        int i = type.getValue() * random.nextInt(range);
        return i;
    }

    public static int getRandomPortionWithVariableLikelihood(ThreadType type, int range) {
        int param = random.nextInt(range);
        if (param < (range/2)) {
            return type.getValue() * random.nextInt(range / 5);
        }
        else if (param < (3 * range / 4)) {
            return type.getValue() * random.nextInt(range / 4);
        }
        else if (param < (19 * range / 20)) {
            return type.getValue() * random.nextInt(range / 3);
        }
        else {
            return type.getValue() * random.nextInt(range);
        }
    }
}
