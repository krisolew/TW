package main.java.lab2.configuration;

import main.java.lab2.bufor.AbstractBufor;
import main.java.lab2.bufor.AlgoBufor;
import main.java.lab2.bufor.BuforType;
import main.java.lab2.bufor.DummyBufor;
import main.java.lab2.threads.ThreadType;

import java.io.IOException;
import java.util.Random;

public class RunConfiguration {
    private final static Random random = new Random();
    public AbstractBufor bufor;
    public StringFileWriter writer;
    public int capacity;
    public int numOfThreads;
    public int range;
    public LikelihoodType likelihoodType;

    public RunConfiguration(String file, int capacity, int numOfThreads, LikelihoodType likelihoodType, BuforType buforType) throws IOException {
        this.bufor = buforType == BuforType.DUMMY ? new DummyBufor(capacity) : new AlgoBufor(capacity);
        this.capacity = capacity;
        this.numOfThreads = numOfThreads;
        this.writer = new StringFileWriter(file);
        this.range = capacity/2;
        this.likelihoodType = likelihoodType;
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
