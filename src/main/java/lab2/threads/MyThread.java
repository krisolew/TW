package main.java.lab2.threads;

import main.java.lab2.configuration.LikelihoodType;
import main.java.lab2.configuration.RunConfiguration;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MyThread extends Thread {
    private static NumberFormat TIME_FORMATTER = new DecimalFormat("#0.0000000");

    private ThreadType type;
    private RunConfiguration configuration;
    private long startTime;
    private long endTime;
    private int portion;

    public MyThread(RunConfiguration configuration, ThreadType type) {
        this.configuration = configuration;
        this.portion = configuration.getLikelihoodType() == LikelihoodType.CONSTANT ?
                RunConfiguration.getRandomPortion(type, configuration.getRange()) :
                RunConfiguration.getRandomPortionWithVariableLikelihood(type, configuration.getRange());
        this.type = type;
    }

    @Override
    public void run() {
        try {
            if (type == ThreadType.CONSUMER) {
                configuration.getBuffer().consume(this);
            } else {
                configuration.getBuffer().produce(this);
            }
            configuration.addData(portion, getWaitingTime());
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

    public int getPortion() {
        return portion;
    }

    public String getWaitingTime() {
        return TIME_FORMATTER.format((double) (endTime - startTime) / 1_000_000_000.0);
    }
}
