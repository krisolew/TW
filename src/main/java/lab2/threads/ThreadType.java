package main.java.lab2.threads;

public enum ThreadType {
    CONSUMER(-1), PRODUCER(1);

    private int value;

    ThreadType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
