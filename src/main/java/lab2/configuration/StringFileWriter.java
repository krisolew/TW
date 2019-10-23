package main.java.lab2.configuration;

import java.io.FileWriter;
import java.io.IOException;

public class StringFileWriter {

    private FileWriter fileWriter;

    StringFileWriter(String fileName) throws IOException {
        this.fileWriter = new FileWriter(fileName);
    }

    public synchronized void write(String content) throws IOException {
        fileWriter.write(content + '\n');
    }

    public void close() throws IOException {
        fileWriter.close();
    }
}
