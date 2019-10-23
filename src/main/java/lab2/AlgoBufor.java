package main.java.lab2;


import static java.lang.System.in;
import static java.lang.System.nanoTime;

public class AlgoBufor extends BasicBufor{

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
                instance.wait();
            }
            threadsExecutor.firstThread.setEndTime(nanoTime());
            this.value += portion;
            threadsExecutor.notifyThreads();
            instance.notifyAll();
        }
    }

}
