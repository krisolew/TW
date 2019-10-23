package main.java.lab2.threads;

import main.java.lab2.configuration.Likelihood;
import main.java.lab2.configuration.RunConfiguration;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MyThread extends Thread {
    private static NumberFormat TIME_FORMATTER = new DecimalFormat("#0.0000000");

    private RunConfiguration configuration;
    private long startTime;
    private long endTime;
    private int portion;

    public MyThread(RunConfiguration configuration, ThreadType type) {
        this.configuration = configuration;
        this.portion = configuration.likelihood == Likelihood.CONSTANT ?
                RunConfiguration.getRandomPortion(type, configuration.range) :
                RunConfiguration.getRandomPortionWithVariableLikelihood(type, configuration.range);
    }

    @Override
    public void run() {
        try {
            configuration.dummyBufor.operation(portion, this);
            configuration.writer.write(portion + " " + getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setConfiguration(RunConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getTime() {
        return TIME_FORMATTER.format((double) (endTime - startTime) / 1_000_000_000.0);
    }
}
