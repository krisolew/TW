package main.java.lab2;

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
