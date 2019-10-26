package main.java.lab2.buffer;

import main.java.lab2.threads.MyThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.nanoTime;

public class FairBuffer extends AbstractBuffer {
    private final Lock consumerLock = new ReentrantLock();
    private final Lock producerLock = new ReentrantLock();

    public FairBuffer(int capacity) {
        super(capacity);
    }

    @Override
    public void consume(MyThread thread) throws InterruptedException {
        thread.setStartTime(nanoTime());

        consumerLock.lock();
        executeOperation(thread);
        consumerLock.unlock();
    }

    @Override
    public void produce(MyThread thread) throws InterruptedException {
        thread.setStartTime(nanoTime());

        producerLock.lock();
        executeOperation(thread);
        producerLock.unlock();
    }
}
