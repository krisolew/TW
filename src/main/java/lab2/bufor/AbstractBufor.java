package main.java.lab2.bufor;

import main.java.lab2.threads.MyThread;

import static java.lang.System.nanoTime;

public abstract class AbstractBufor {
    private static final Object LOCK = new Object();
    private static int counter = 0;
    private int capacity;
    private Integer value = 0;

    public AbstractBufor(int capacity) {
        this.capacity = capacity;
    }

    public abstract void consume(MyThread thread) throws InterruptedException;

    public abstract void produce(MyThread thread) throws InterruptedException;

    protected void executeOperation(MyThread thread) throws InterruptedException {
        synchronized (LOCK) {
            while (this.value + thread.getPortion() < 0 || this.value + thread.getPortion() > capacity) {
                LOCK.wait();
            }
            thread.setEndTime(nanoTime());
            this.value += thread.getPortion();
            System.out.println("Nr: " + counter++ + ", stan bufora: " + (value - thread.getPortion()) + " -> " + value +
                    ", porcja: " + thread.getPortion() + ", czas oczekiwania: " + thread.getTime());
            LOCK.notifyAll();
        }
    }
}
