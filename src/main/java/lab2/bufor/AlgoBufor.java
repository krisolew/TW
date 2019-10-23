package main.java.lab2.bufor;

import main.java.lab2.threads.MyThread;

import static java.lang.System.nanoTime;

public class AlgoBufor extends AbstractBufor {
    private final static Object CONSUMER_LOCK = new Object();
    private final static Object PRODUCER_LOCK = new Object();

    private boolean isConsumerFree = true;
    private boolean isProducerFree = true;

    public AlgoBufor(int capacity) {
        super(capacity);
    }

    @Override
    public void consume(MyThread thread) throws InterruptedException {
        thread.setStartTime(nanoTime());
        synchronized (CONSUMER_LOCK) {
            while (!isConsumerFree)
                CONSUMER_LOCK.wait();
            isConsumerFree = false;
        }
        executeOperation(thread);

        synchronized (CONSUMER_LOCK) {
            isConsumerFree = true;
            CONSUMER_LOCK.notify();
        }
    }

    @Override
    public void produce(MyThread thread) throws InterruptedException {
        thread.setStartTime(nanoTime());
        synchronized (PRODUCER_LOCK) {
            while (!isProducerFree)
                PRODUCER_LOCK.wait();
            isProducerFree = false;
        }
        executeOperation(thread);

        synchronized (PRODUCER_LOCK) {
            isProducerFree = true;
            PRODUCER_LOCK.notify();
        }
    }
}
