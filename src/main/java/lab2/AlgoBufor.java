package main.java.lab2;

import java.util.Random;

import static java.lang.System.nanoTime;

public class AlgoBufor extends BasicBufor{

    private Integer value;
    private static AlgoBufor instance = null;
    private static final Integer capacity = 10000;
    private static Random random;
    private int counter = 1;

    private AlgoBufor(Integer value) {
        super(value);
    }

    public void operation(int portion, ThreadsExecutor threadsExecutor) throws InterruptedException {

        synchronized (instance) {
            if (null == threadsExecutor.firstThread) {
                threadsExecutor.waitThreads();
            }
            while (this.value + portion < 0 || this.value + portion > capacity) {
                threadsExecutor.firstThread.setStartTime(nanoTime());
                threadsExecutor.firstThread.wait();
            }
            threadsExecutor.firstThread.setEndTime(nanoTime());
            this.value += portion;
            threadsExecutor.notifyThreads();

        }
    }

}
