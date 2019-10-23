package main.java.lab2;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MyThread extends Thread {
    private static NumberFormat TIME_FORMATTER = new DecimalFormat("#0.0000000");

    private long startTime;
    private long endTime;
    private int portion;

    public MyThread(int portion) {
        this.portion = portion;
    }

    @Override
    public void run() {
        try {
            Main.dummyBufor.operation(portion, this);
            Main.writer.write(portion + " " + getTime());
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

    public String getTime() {
        return TIME_FORMATTER.format((double) (endTime - startTime) / 1_000_000_000.0);
    }
}
