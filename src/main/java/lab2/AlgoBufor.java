package main.java.lab2;

import static java.lang.System.nanoTime;

public class AlgoBufor{
    private int capacity;
    private Integer value;
    private int counter = 1;

    public AlgoBufor(Integer initValue, int capacity) {
        this.value = initValue;
        this.capacity = capacity;
    }

    public void operation(int portion, ThreadsExecutor threadsExecutor) throws InterruptedException {

        synchronized (Main.dummyBufor) {
            if (null == threadsExecutor.firstThread) {
                threadsExecutor.waitThreads();
            }
            while (this.value + portion < 0 || this.value + portion > capacity) {
                Main.dummyBufor.wait();
            }
            threadsExecutor.firstThread.setEndTime(nanoTime());
            this.value += portion;
            threadsExecutor.notifyThreads();
            Main.dummyBufor.notifyAll();
        }
    }
}
