package main.java.lab2;

import java.io.FileWriter;
import java.io.IOException;

public class StringFileWriter {

    private FileWriter fileWriter;

    public StringFileWriter(String fileName) throws IOException {
        this.fileWriter = new FileWriter(fileName);
    }

    public void write(String content) throws IOException {
        fileWriter.write(content);
        fileWriter.close();
    }
}
