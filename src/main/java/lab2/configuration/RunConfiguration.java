package main.java.lab2.configuration;

import main.java.lab2.RScriptBuilder;
import main.java.lab2.buffer.AbstractBuffer;
import main.java.lab2.buffer.BufferType;
import main.java.lab2.buffer.FairBuffer;
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
    private final static String SCRIPT_FILE_NAME_PATTERN = "script_.r";
    private static Integer FILE_NUMBER = 0;
    private static RScriptBuilder scriptBuilder = new RScriptBuilder();

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
        FileWriter writer = new FileWriter(PACKAGE_PATH + getScriptFileName());
        writer.write(scriptBuilder.getScript(portions, times, range, 10));
        writer.close();
    }

    public static int getRandomPortion(ThreadType type, int range) {
        return type.getValue() * RANDOM.nextInt(range);
    }

    public static int getRandomPortionWithVariableLikelihood(ThreadType type, int range) {
        int param = RANDOM.nextInt(range);
        if (param < (19 * range / 20)) {
            return type.getValue() * RANDOM.nextInt(range / 5);
        } else {
            return type.getValue() * (RANDOM.nextInt(range / 10) + range * 9/10 + 1) ;
        }
    }

    public synchronized void addData(Integer portion, String time) {
        this.portions.add(portion);
        this.times.add(time);
    }

    public static List<RunConfiguration> getRunConfigurations() {
        List<RunConfiguration> configurations = new LinkedList<>();

        configurations.add(new RunConfiguration(10000, 100,
                LikelihoodType.CONSTANT, BufferType.NAIVE));
        configurations.add(new RunConfiguration(10000, 100,
                LikelihoodType.CONSTANT, BufferType.FAIR));
        configurations.add(new RunConfiguration(10000, 100,
                LikelihoodType.VARIABLE, BufferType.NAIVE));
        configurations.add(new RunConfiguration(10000, 100,
                LikelihoodType.VARIABLE, BufferType.FAIR));

//        configurations.add(new RunConfiguration(10000, 1000,
//                LikelihoodType.CONSTANT, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(10000, 1000,
//                LikelihoodType.CONSTANT, BufferType.FAIR));
//        configurations.add(new RunConfiguration(10000, 1000,
//                LikelihoodType.VARIABLE, BufferType.NAIVE));
//        configurations.add(new RunConfiguration(10000, 1000,
//                LikelihoodType.VARIABLE, BufferType.FAIR));
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

    private static String getScriptFileName() {
        FILE_NUMBER++;
        return SCRIPT_FILE_NAME_PATTERN.replace("_", FILE_NUMBER.toString());
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
