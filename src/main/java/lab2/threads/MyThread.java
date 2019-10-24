package main.java.lab2.threads;

import main.java.lab2.configuration.LikelihoodType;
import main.java.lab2.configuration.RunConfiguration;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static java.lang.Math.abs;

public class MyThread extends Thread {
    private static NumberFormat TIME_FORMATTER = new DecimalFormat("#0.0000000");

    private ThreadType type;
    private RunConfiguration configuration;
    private long startTime;
    private long endTime;
    private int portion;

    public MyThread(RunConfiguration configuration, ThreadType type) {
        this.configuration = configuration;
        this.portion = configuration.likelihoodType == LikelihoodType.CONSTANT ?
                RunConfiguration.getRandomPortion(type, configuration.range) :
                RunConfiguration.getRandomPortionWithVariableLikelihood(type, configuration.range);
        this.type = type;
    }

    @Override
    public void run() {
        try {
            if (type == ThreadType.CONSUMER) {
                configuration.bufor.consume(this);
            } else {
                configuration.bufor.produce(this);
            }
            configuration.writer.write(getFileLog());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileLog() {
        String space = "\t";
        if (abs(portion) < 1000) space += space;
        return abs(portion) + space + getTime();
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getPortion() {
        return portion;
    }

    public String getTime() {
        return TIME_FORMATTER.format((double) (endTime - startTime) / 1_000_000_000.0);
    }
}
