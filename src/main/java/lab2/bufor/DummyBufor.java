package main.java.lab2.bufor;

import main.java.lab2.threads.MyThread;

import static java.lang.System.nanoTime;

public class DummyBufor extends AbstractBufor {

    public DummyBufor(int capacity) {
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
