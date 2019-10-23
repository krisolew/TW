package main.java.lab2;

import static java.lang.System.nanoTime;

public class DummyBufor extends BasicBufor {

    private static BasicBufor instance = null;

    public DummyBufor(Integer value) {
        super(value);
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
                    ", porcja: " + portion + ", czas oczekiwania: " + thread.countTime());
        }
    }

}
