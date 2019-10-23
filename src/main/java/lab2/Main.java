package main.java.lab2;

import main.java.lab2.configuration.Likelihood;
import main.java.lab2.configuration.RunConfiguration;
import main.java.lab2.threads.MyThread;
import main.java.lab2.threads.ThreadType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        RunConfiguration configuration = new RunConfiguration("src/main/resources/lab2/times.txt", 1000, 1000, Likelihood.CONSTANT);

//        ThreadsExecutor producerExecutor = new ThreadsExecutor();
//        ThreadsExecutor consumerExecutor = new ThreadsExecutor();
        List<MyThread> producer = new LinkedList<>();
        List<MyThread> consumer = new LinkedList<>();

        for (int i = 0; i < configuration.numOfThreads; i++) {
            producer.add(new MyThread(configuration, ThreadType.PRODUCER));
            consumer.add(new MyThread(configuration, ThreadType.CONSUMER));
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
            configuration.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
