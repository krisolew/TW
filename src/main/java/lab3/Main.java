package main.java.lab3;

import main.java.lab3.configuration.RunConfiguration;
import main.java.lab3.threads.MyThread;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.System.nanoTime;

public class Main {

    private static NumberFormat TIME_FORMATTER = new DecimalFormat("#0.0000000");

    public static void main(String[] args) {

        List<RunConfiguration> configurations = RunConfiguration.getRunConfigurations();
        List<Future> futures = new LinkedList<>();

        for (RunConfiguration configuration : configurations) {
            MandelbrotSet mandelbrotSet = new MandelbrotSet(configuration.getPictureSize());
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(configuration.getNumOfThreads());

            long startTime = nanoTime();
            for (int i = 0; i < configuration.getNumOfThreads() * 10; i++) {
                futures.add(executor.submit(new MyThread(mandelbrotSet,
                        i * configuration.getUint(),
                        i * configuration.getUint() + configuration.getUint())));
            }

            for (Future future : futures) {
                while (!future.isDone()) ;
            }
            long endTime = nanoTime();

            addConsoleLog(configuration, getWaitingTime(startTime, endTime));

//            mandelbrotSet.setVisible(true);
            mandelbrotSet.dispose();
            futures.clear();
        }
        System.exit(0);
    }

    private static String getWaitingTime(long startTime, long endTime) {
        return TIME_FORMATTER.format((double) (endTime - startTime) / 1_000_000_000.0);
    }

    private static void addConsoleLog(RunConfiguration configuration, String time) {
        System.out.println("Number of threads: " + configuration.getNumOfThreads() +
                ", picture size: " + configuration.getPictureSize() +
                " -> Time: " + time);
    }
}
