package main.java.lab2.configuration;

import main.java.lab2.buffer.AbstractBuffer;
import main.java.lab2.buffer.FairBuffer;
import main.java.lab2.buffer.BufferType;
import main.java.lab2.buffer.NaiveBuffer;
import main.java.lab2.threads.ThreadType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RunConfiguration {
    private final static Random RANDOM = new Random();
    private final static String PACKAGE_PATH = "src/main/resources/lab2/";
    private final static String FILE_NAME_PATTERN = "times_.txt";
    private static Integer FILE_NUMBER = 0;

    public AbstractBuffer buffer;
    public FileWriter writer;
    public int capacity;
    public int numOfThreads;
    public int range;
    public LikelihoodType likelihoodType;

    public RunConfiguration(String file, int capacity, int numOfThreads, LikelihoodType likelihoodType, BufferType bufferType) throws IOException {
        this.buffer = bufferType == BufferType.NAIVE ? new NaiveBuffer(capacity) : new FairBuffer(capacity);
        this.capacity = capacity;
        this.numOfThreads = numOfThreads;
        this.writer = new FileWriter(PACKAGE_PATH + file);
        this.range = capacity / 2;
        this.likelihoodType = likelihoodType;
    }

    public static int getRandomPortion(ThreadType type, int range) {
        return type.getValue() * RANDOM.nextInt(range);
    }

    public static int getRandomPortionWithVariableLikelihood(ThreadType type, int range) {
        int param = RANDOM.nextInt(range);
        if (param < (range / 2)) {
            return type.getValue() * RANDOM.nextInt(range / 5);
        } else if (param < (3 * range / 4)) {
            return type.getValue() * RANDOM.nextInt(range / 4);
        } else if (param < (19 * range / 20)) {
            return type.getValue() * RANDOM.nextInt(range / 3);
        } else {
            return type.getValue() * RANDOM.nextInt(range);
        }
    }

    public static List<RunConfiguration> getRunConfigurations() throws IOException {
        List<RunConfiguration> configurations = new LinkedList<>();

        configurations.add(new RunConfiguration(getFileName(), 10000, 100,
                LikelihoodType.CONSTANT, BufferType.NAIVE));
        configurations.add(new RunConfiguration(getFileName(), 10000, 100,
                LikelihoodType.CONSTANT, BufferType.FAIR));
        configurations.add(new RunConfiguration(getFileName(), 10000, 100,
                LikelihoodType.VARIABLE, BufferType.NAIVE));
        configurations.add(new RunConfiguration(getFileName(), 10000, 100,
                LikelihoodType.VARIABLE, BufferType.FAIR));

//        configurations.add(new RunConfiguration(getFileName(), 10000, 1000,
//                LikelihoodType.CONSTANT, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(getFileName(), 10000, 1000,
//                LikelihoodType.CONSTANT, BufferType.FAIR));
//        configurations.add(new RunConfiguration(getFileName(), 10000, 1000,
//                LikelihoodType.VARIABLE, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(getFileName(), 10000, 1000,
//                LikelihoodType.VARIABLE, BufferType.FAIR));
//
//        configurations.add(new RunConfiguration(getFileName(), 100000, 100,
//                LikelihoodType.CONSTANT, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(getFileName(), 100000, 100,
//                LikelihoodType.CONSTANT, BufferType.FAIR));
//        configurations.add(new RunConfiguration(getFileName(), 100000, 100,
//                LikelihoodType.VARIABLE, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(getFileName(), 100000, 100,
//                LikelihoodType.VARIABLE, BufferType.FAIR));
//
//        configurations.add(new RunConfiguration(getFileName(), 100000, 1000,
//                LikelihoodType.CONSTANT, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(getFileName(), 100000, 1000,
//                LikelihoodType.CONSTANT, BufferType.FAIR));
//        configurations.add(new RunConfiguration(getFileName(), 100000, 1000,
//                LikelihoodType.VARIABLE, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(getFileName(), 100000, 1000,
//                LikelihoodType.VARIABLE, BufferType.FAIR));
        return configurations;
    }

    private static String getFileName() {
        FILE_NUMBER++;
        return FILE_NAME_PATTERN.replace("_", FILE_NUMBER.toString());
    }
}
