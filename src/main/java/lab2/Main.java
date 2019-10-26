package main.java.lab2;

import main.java.lab2.configuration.RunConfiguration;
import main.java.lab2.threads.MyThread;
import main.java.lab2.threads.ThreadType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<RunConfiguration> configurations = RunConfiguration.getRunConfigurations();
        List<MyThread> producer = new LinkedList<>();
        List<MyThread> consumer = new LinkedList<>();

        for (RunConfiguration configuration : configurations) {
            for (int i = 0; i < configuration.getNumOfThreads(); i++) {
                producer.add(new MyThread(configuration, ThreadType.PRODUCER));
                consumer.add(new MyThread(configuration, ThreadType.CONSUMER));
            }

            producer.forEach(Thread::start);
            consumer.forEach(Thread::start);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            producer.forEach(Thread::stop);
            consumer.forEach(Thread::stop);

            try {
                configuration.saveDate();
            } catch (IOException e) {
                System.out.println("Could not save data!");
                e.printStackTrace();
            }

            producer.clear();
            consumer.clear();
        }
    }
}
