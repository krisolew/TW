package main.java.lab2;

import java.util.Random;

public class BasicBufor {
    protected static final Integer capacity = 10000;
    protected static Random random = new Random();
    protected Integer value;
    protected int counter = 1;

    public BasicBufor(Integer value) {
        this.value = value;
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

    public void operation(int portion, MyThread thread) throws InterruptedException {}
}
