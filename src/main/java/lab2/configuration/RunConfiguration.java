package main.java.lab2.configuration;

import main.java.lab2.DataWriter;
import main.java.lab2.buffer.AbstractBuffer;
import main.java.lab2.buffer.BufferType;
import main.java.lab2.buffer.FairBuffer;
import main.java.lab2.buffer.NaiveBuffer;
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

    private List<Integer> portions = new LinkedList<>();
    private List<String> times = new LinkedList<>();
    private LikelihoodType likelihoodType;
    private AbstractBuffer buffer;
    private int numOfThreads;
    private int range;

    private RunConfiguration(int bufferCapacity, int numOfThreads, LikelihoodType likelihoodType, BufferType bufferType) {
        this.buffer = bufferType == BufferType.NAIVE ? new NaiveBuffer(bufferCapacity) : new FairBuffer(bufferCapacity);
        this.numOfThreads = numOfThreads;
        this.range = bufferCapacity / 2;
        this.likelihoodType = likelihoodType;
    }

    public void saveDate() throws IOException {
        DataWriter writer = new DataWriter(PACKAGE_PATH + getFileName());
        writer.write(portions, times);
        writer.close();
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

    public synchronized void addData(Integer portion, String time) {
        this.portions.add(portion);
        this.times.add(time);
    }

    public static List<RunConfiguration> getRunConfigurations() {
        List<RunConfiguration> configurations = new LinkedList<>();

//        configurations.add(new RunConfiguration(10000, 100,
//                LikelihoodType.CONSTANT, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(10000, 100,
//                LikelihoodType.CONSTANT, BufferType.FAIR));
//        configurations.add(new RunConfiguration(10000, 100,
//                LikelihoodType.VARIABLE, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(10000, 100,
//                LikelihoodType.VARIABLE, BufferType.FAIR));

        configurations.add(new RunConfiguration(10000, 1000,
                LikelihoodType.CONSTANT, BufferType.NAIVE));
        configurations.add(new RunConfiguration(10000, 1000,
                LikelihoodType.CONSTANT, BufferType.FAIR));
        configurations.add(new RunConfiguration(10000, 1000,
                LikelihoodType.VARIABLE, BufferType.NAIVE));
        configurations.add(new RunConfiguration(10000, 1000,
                LikelihoodType.VARIABLE, BufferType.FAIR));
//
//        configurations.add(new RunConfiguration(100000, 100,
//                LikelihoodType.CONSTANT, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(100000, 100,
//                LikelihoodType.CONSTANT, BufferType.FAIR));
//        configurations.add(new RunConfiguration(100000, 100,
//                LikelihoodType.VARIABLE, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(100000, 100,
//                LikelihoodType.VARIABLE, BufferType.FAIR));
//
//        configurations.add(new RunConfiguration(100000, 1000,
//                LikelihoodType.CONSTANT, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(100000, 1000,
//                LikelihoodType.CONSTANT, BufferType.FAIR));
//        configurations.add(new RunConfiguration(100000, 1000,
//                LikelihoodType.VARIABLE, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(100000, 1000,
//                LikelihoodType.VARIABLE, BufferType.FAIR));
        return configurations;
    }

    private static String getFileName() {
        FILE_NUMBER++;
        return FILE_NAME_PATTERN.replace("_", FILE_NUMBER.toString());
    }

    public AbstractBuffer getBuffer() {
        return buffer;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }

    public int getRange() {
        return range;
    }

    public LikelihoodType getLikelihoodType() {
        return likelihoodType;
    }
}
