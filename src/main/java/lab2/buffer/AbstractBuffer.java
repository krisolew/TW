package main.java.lab2.buffer;

import main.java.lab2.threads.MyThread;

import static java.lang.System.nanoTime;

public abstract class AbstractBuffer {
    private static int counter = 0;
    private int capacity;
    private Integer value = 0;

    public AbstractBuffer(int capacity) {
        this.capacity = capacity;
    }

    public abstract void consume(MyThread thread) throws InterruptedException;

    public abstract void produce(MyThread thread) throws InterruptedException;

    protected void executeOperation(MyThread thread) throws InterruptedException {
        synchronized (this) {
            while (this.value + thread.getPortion() < 0 || this.value + thread.getPortion() > capacity) {
                this.wait();
            }
            thread.setEndTime(nanoTime());
            this.value += thread.getPortion();
            System.out.println("Nr: " + counter++ + ", stan bufora: " + (value - thread.getPortion()) + " -> " + value +
                    ", porcja: " + thread.getPortion() + ", czas oczekiwania: " + thread.getWaitingTime());
            this.notifyAll();
        }
    }
}
