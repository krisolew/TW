package main.java.lab2;

import main.java.lab2.bufor.BuforType;
import main.java.lab2.configuration.LikelihoodType;
import main.java.lab2.configuration.RunConfiguration;
import main.java.lab2.threads.MyThread;
import main.java.lab2.threads.ThreadType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        RunConfiguration configuration = new RunConfiguration("src/main/resources/lab2/times.txt", 1000, 1000, LikelihoodType.CONSTANT, BuforType.DUMMY);

        List<MyThread> producer = new LinkedList<>();
        List<MyThread> consumer = new LinkedList<>();

        for (int i = 0; i < configuration.numOfThreads; i++) {
            producer.add(new MyThread(configuration, ThreadType.PRODUCER));
            consumer.add(new MyThread(configuration, ThreadType.CONSUMER));
        }

        consumer.forEach(Thread::start);
        producer.forEach(Thread::start);

        try {
            Thread.sleep(10000);
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
