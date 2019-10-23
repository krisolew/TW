package main.java.lab2;

public class MyThread extends Thread {
    private BasicBufor bufor;
    private ThreadType type;
    private long startTime;
    private long endTime;

    public MyThread(ThreadType type, BasicBufor bufor) {
        this.bufor = bufor;
        this.type = type;
    }

    @Override
    public void run() {
        try {
            int portion = DummyBufor.getRandomPortionWithVariableLikelihood(type);
            bufor.operation(portion, this);
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
