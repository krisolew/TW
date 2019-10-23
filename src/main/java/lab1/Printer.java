package main.java.lab1;

public class Printer extends Thread {
    private int number;

    Printer(int number) {
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            while (Main.number != this.number) {
                synchronized (Main.LOCK) {
                    try {
                        Main.LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println(number);
            if (++Main.number == Main.NUMBER_OF_THREADS) {
                Main.number = 0;
            }
            synchronized (Main.LOCK) {
                Main.LOCK.notifyAll();
            }
        }
    }
}
