package main.java.lab2.buffer;

import main.java.lab2.threads.MyThread;

import static java.lang.System.nanoTime;

public class NaiveBuffer extends AbstractBuffer {

    public NaiveBuffer(int capacity) {
        super(capacity);
    }

    @Override
    public void consume(MyThread thread) throws InterruptedException {
        thread.setStartTime(nanoTime());
        executeOperation(thread);
    }

    @Override
    public void produce(MyThread thread) throws InterruptedException {
        thread.setStartTime(nanoTime());
        executeOperation(thread);
    }
}
