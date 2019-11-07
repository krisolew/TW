package main.java.lab3.configuration;

import java.util.LinkedList;
import java.util.List;

public class RunConfiguration {

    private int numOfThreads;
    private int pictureSize;
    private int uint;

    private RunConfiguration(int numOfThreads, int pictureSize) {
        this.numOfThreads = numOfThreads;
        this.pictureSize = pictureSize;
        this.uint = pictureSize / (numOfThreads * 10);
    }

    public static List<RunConfiguration> getRunConfigurations() {
        List<RunConfiguration> configurations = new LinkedList<>();

//        configurations.add(new RunConfiguration(10, 200));
//        configurations.add(new RunConfiguration(10, 500));
//        configurations.add(new RunConfiguration(10, 800));
//        configurations.add(new RunConfiguration(10, 1000));
//        configurations.add(new RunConfiguration(10, 1200));
//        configurations.add(new RunConfiguration(10, 1600));
//        configurations.add(new RunConfiguration(10, 1800));
//        configurations.add(new RunConfiguration(10, 2000));
//        configurations.add(new RunConfiguration(10, 2500));
//        configurations.add(new RunConfiguration(10, 3000));
//        configurations.add(new RunConfiguration(10, 4000));
//
//
        configurations.add(new RunConfiguration(1, 2000));
        configurations.add(new RunConfiguration(2, 2000));
        configurations.add(new RunConfiguration(4, 2000));
        configurations.add(new RunConfiguration(10, 2000));
        configurations.add(new RunConfiguration(20, 2000));
        configurations.add(new RunConfiguration(25, 2000));
        configurations.add(new RunConfiguration(40, 2000));
        configurations.add(new RunConfiguration(50, 2000));
        configurations.add(new RunConfiguration(100, 2000));
        configurations.add(new RunConfiguration(200, 2000));
//
//
//        configurations.add(new RunConfiguration(1, 4000));
//        configurations.add(new RunConfiguration(2, 4000));
//        configurations.add(new RunConfiguration(4, 4000));
//        configurations.add(new RunConfiguration(10, 4000));
//        configurations.add(new RunConfiguration(20, 4000));
//        configurations.add(new RunConfiguration(25, 4000));
//        configurations.add(new RunConfiguration(40, 4000));
//        configurations.add(new RunConfiguration(50, 4000));
//        configurations.add(new RunConfiguration(80, 4000));
//        configurations.add(new RunConfiguration(100, 4000));
//        configurations.add(new RunConfiguration(200, 4000));
//        configurations.add(new RunConfiguration(400, 4000));

        return configurations;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }

    public int getPictureSize() {
        return pictureSize;
    }

    public int getUint() {
        return uint;
    }
}
