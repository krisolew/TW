package main.java.lab2;

import static java.lang.System.nanoTime;

public class DummyBufor {
    private int capacity;
    private Integer value;
    private int counter = 1;

    public DummyBufor(Integer initValue, int capacity) {
        this.value = initValue;
        this.capacity = capacity;
    }

    public void operation(int portion, MyThread thread) throws InterruptedException {
        thread.setStartTime(nanoTime());
        synchronized (this) {
            while (this.value + portion < 0 || this.value + portion > capacity) {
                this.wait();
            }
            thread.setEndTime(nanoTime());
            this.value += portion;
            this.notifyAll();
            System.out.println("Nr: " + counter++ + ", stan bufora: " + (value - portion) + " -> " + value +
                    ", porcja: " + portion + ", czas oczekiwania: " + thread.getTime());
        }
    }
}
