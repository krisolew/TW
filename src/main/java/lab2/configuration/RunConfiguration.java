package main.java.lab2.configuration;

import main.java.lab2.bufor.DummyBufor;
import main.java.lab2.threads.ThreadType;

import java.io.IOException;
import java.util.Random;

public class RunConfiguration {
    private final static Random random = new Random();
    public DummyBufor dummyBufor;
    public StringFileWriter writer;
    public int capacity;
    public int numOfThreads;
    public int range;
    public Likelihood likelihood;

    public RunConfiguration(String file, int capacity, int numOfThreads, Likelihood likelihood) throws IOException {
        this.dummyBufor = new DummyBufor(0, capacity);
        this.capacity = capacity;
        this.numOfThreads = numOfThreads;
        this.writer = new StringFileWriter(file);
        this.range = capacity/2;
        this.likelihood = likelihood;
    }

    public static int getRandomPortion(ThreadType type, int range) {
        return type.getValue() * random.nextInt(range);
    }

    public static int getRandomPortionWithVariableLikelihood(ThreadType type, int range) {
        int param = random.nextInt(range);
        if (param < (range/2)) {
            return type.getValue() * random.nextInt(range / 5);
        }
        else if (param < (3 * range / 4)) {
            return type.getValue() * random.nextInt(range / 4);
        }
        else if (param < (19 * range / 20)) {
            return type.getValue() * random.nextInt(range / 3);
        }
        else {
            return type.getValue() * random.nextInt(range);
        }
    }
}
