package main.java.lab2;

import static java.lang.System.nanoTime;

public class AlgoBufor extends BasicBufor{

    public AlgoBufor(Integer value) {
        super(value);
    }

    public void operation(int portion, ThreadsExecutor threadsExecutor) throws InterruptedException {
        synchronized (this) {
            if (null == threadsExecutor.firstThread) {
                threadsExecutor.waitThreads();
            }
            while (this.value + portion < 0 || this.value + portion > capacity) {
                this.wait();
            }
            threadsExecutor.firstThread.setEndTime(nanoTime());
            this.value += portion;
            System.out.println("Nr: " + counter++ + ", stan bufora: " + (value - portion) + " -> " + value +
                    ", porcja: " + portion + ", czas oczekiwania: " + threadsExecutor.firstThread.countTime());
            threadsExecutor.notifyThreads();
            this.notifyAll();

        }
    }

}
