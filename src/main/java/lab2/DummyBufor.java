package main.java.lab2;

import static java.lang.System.nanoTime;

public class DummyBufor extends BasicBufor {

    private DummyBufor(Integer value) {
        super(value);
    }

    public void operation(int portion, MyThread thread) throws InterruptedException {
        thread.setStartTime(nanoTime());
        synchronized (instance) {
            while (this.value + portion < 0 || this.value + portion > capacity) {
                instance.wait();
            }
            thread.setEndTime(nanoTime());
            this.value += portion;
            instance.notifyAll();
            System.out.println("Nr: " + counter++ + ", stan bufora: " + (value - portion) + " -> " + value +
                    ", porcja: " + portion + ", czas oczekiwania: " + thread.countTime());
        }
    }

}
