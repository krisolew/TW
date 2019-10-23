package main.java.lab2;

public class MyThread extends Thread {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public double countTime() {
        return (double) (endTime - startTime) / 1_000_000_000.0;
    }
}
