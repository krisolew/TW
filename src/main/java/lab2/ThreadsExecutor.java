package main.java.lab2;

import java.util.LinkedList;

import static java.lang.System.nanoTime;

public class ThreadsExecutor {

    LinkedList<MyThread> threads = new LinkedList<>();
    MyThread firstThread;

    public void add(MyThread thread){
        threads.add(thread);
    }

    public void waitThreads() {
        threads.forEach(thread -> thread.setStartTime(nanoTime()));
    }

    public void notifyThreads() {
        firstThread = threads.peek();
    }
}
