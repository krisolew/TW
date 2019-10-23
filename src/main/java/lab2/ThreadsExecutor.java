package main.java.lab2;

import java.util.LinkedList;
import java.util.List;

public class ThreadsExecutor {

    List<MyThread> threads = new LinkedList<>();

    public void add(MyThread thread){
        threads.add(thread);
    }
}
