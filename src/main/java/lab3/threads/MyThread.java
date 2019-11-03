package main.java.lab3.threads;

import main.java.lab3.MandelbrotSet;

public class MyThread extends Thread {

    private MandelbrotSet mandelbrotSet;
    private int firstDimension;
    private int secondDimension;

    public MyThread(MandelbrotSet mandelbrotSet, int firstDimension, int secondDimension) {
        this.mandelbrotSet = mandelbrotSet;
        this.firstDimension = firstDimension;
        this.secondDimension = secondDimension;
    }

    @Override
    public void run() {
        mandelbrotSet.count(firstDimension, secondDimension);
    }
}
