package main.java.lab2;

import java.io.FileWriter;
import java.io.IOException;

public class StringFileWriter {

    private FileWriter fileWriter;

    public StringFileWriter(String fileName) throws IOException {
        this.fileWriter = new FileWriter(fileName);
    }

    public synchronized void write(String content) throws IOException {
        fileWriter.write(content + '\n');
    }

    public void close() throws IOException {
        fileWriter.close();
    }
}
