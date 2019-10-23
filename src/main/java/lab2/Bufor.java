package main.java.lab2;

import java.util.Random;

import static java.lang.System.nanoTime;

public class Bufor {
    private Integer value;
    private static Bufor instance = null;
    private static final Integer capacity = 10000;
    private static Random random;
    private int counter = 1;

    private Bufor(Integer value) {
        this.value = value;
        random = new Random();
    }

    public static synchronized Bufor initialize() {
        if (instance == null) {
            instance = new Bufor(0);
        }
        return instance;
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

    public static int getRandomPortion(ThreadType type) {
        return type.getValue() * random.nextInt(capacity / 2);
    }

    public static int getRandomPortionWithVariableLikelihood(ThreadType type) {
        int param = random.nextInt(capacity);
        if (param < (capacity / 2)) {
            return type.getValue() * random.nextInt(capacity / 10);
        }
        else if (param < (3 * capacity / 4)) {
            return type.getValue() * random.nextInt(capacity / 8);
        }
        else if (param < (19 * capacity / 20)) {
            return type.getValue() * random.nextInt(capacity / 6);
        }
        else {
            return type.getValue() * random.nextInt(capacity / 2);
        }
    }
}
