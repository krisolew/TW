package main.java.lab2.configuration;

import main.java.lab2.bufor.AbstractBufor;
import main.java.lab2.bufor.AlgoBufor;
import main.java.lab2.bufor.BuforType;
import main.java.lab2.bufor.DummyBufor;
import main.java.lab2.threads.ThreadType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RunConfiguration {
    private final static Random RANDOM = new Random();
    private final static String PACKAGE_PATH = "src/main/resources/lab2/";
    private final static String FILE_NAME_PATTERN = "times_.txt";
    private static Integer FILE_NUMBER = 0;

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
        this.writer = new StringFileWriter(PACKAGE_PATH + file);
        this.range = capacity/2;
        this.likelihoodType = likelihoodType;
    }

    public static int getRandomPortion(ThreadType type, int range) {
        return type.getValue() * RANDOM.nextInt(range);
    }

    public static int getRandomPortionWithVariableLikelihood(ThreadType type, int range) {
        int param = RANDOM.nextInt(range);
        if (param < (range/2)) {
            return type.getValue() * RANDOM.nextInt(range / 5);
        }
        else if (param < (3 * range / 4)) {
            return type.getValue() * RANDOM.nextInt(range / 4);
        }
        else if (param < (19 * range / 20)) {
            return type.getValue() * RANDOM.nextInt(range / 3);
        }
        else {
            return type.getValue() * RANDOM.nextInt(range);
        }
    }

    public static List<RunConfiguration> getRunConfigurations() throws IOException {
        List<RunConfiguration> configurations = new LinkedList<>();

        configurations.add(new RunConfiguration(getFileName(), 10000, 100,
                LikelihoodType.CONSTANT, BuforType.DUMMY));
        configurations.add(new RunConfiguration(getFileName(), 10000, 100,
                LikelihoodType.CONSTANT, BuforType.ALGO));
        configurations.add(new RunConfiguration(getFileName(), 10000, 100,
                LikelihoodType.VARIABLE, BuforType.DUMMY));
        configurations.add(new RunConfiguration(getFileName(), 10000, 100,
                LikelihoodType.VARIABLE, BuforType.ALGO));

        configurations.add(new RunConfiguration(getFileName(), 10000, 1000,
                LikelihoodType.CONSTANT, BuforType.DUMMY));
        configurations.add(new RunConfiguration(getFileName(), 10000, 1000,
                LikelihoodType.CONSTANT, BuforType.ALGO));
        configurations.add(new RunConfiguration(getFileName(), 10000, 1000,
                LikelihoodType.VARIABLE, BuforType.DUMMY));
        configurations.add(new RunConfiguration(getFileName(), 10000, 1000,
                LikelihoodType.VARIABLE, BuforType.ALGO));

        configurations.add(new RunConfiguration(getFileName(), 100000, 100,
                LikelihoodType.CONSTANT, BuforType.DUMMY));
        configurations.add(new RunConfiguration(getFileName(), 100000, 100,
                LikelihoodType.CONSTANT, BuforType.ALGO));
        configurations.add(new RunConfiguration(getFileName(), 100000, 100,
                LikelihoodType.VARIABLE, BuforType.DUMMY));
        configurations.add(new RunConfiguration(getFileName(), 100000, 100,
                LikelihoodType.VARIABLE, BuforType.ALGO));

        configurations.add(new RunConfiguration(getFileName(), 100000, 1000,
                LikelihoodType.CONSTANT, BuforType.DUMMY));
        configurations.add(new RunConfiguration(getFileName(), 100000, 1000,
                LikelihoodType.CONSTANT, BuforType.ALGO));
        configurations.add(new RunConfiguration(getFileName(), 100000, 1000,
                LikelihoodType.VARIABLE, BuforType.DUMMY));
        configurations.add(new RunConfiguration(getFileName(), 100000, 1000,
                LikelihoodType.VARIABLE, BuforType.ALGO));
        return configurations;
    }

    private static String getFileName(){
        FILE_NUMBER++;
        return FILE_NAME_PATTERN.replace("_", FILE_NUMBER.toString());
    }
}
