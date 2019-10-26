package main.java.lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.lang.Math.abs;

public class DataWriter extends FileWriter {

    public DataWriter(String fileName) throws IOException {
        super(fileName);
    }

    public void write(List<Integer> portions, List<String> times) throws IOException {
        for (int i = 0; i < portions.size(); i++) {
            write(getFileLine(portions.get(i), times.get(i)));
        }
    }

    public void write(List data) throws IOException {
        for (Object o : data) write(o.toString());
    }

    private String getFileLine(int portion, String time) {
        String space = "\t";
        if ((abs(portion) < 1000 && portion > 0) || (portion < 0 && portion > -100)) space += space;
        return portion + space + time + "\n";
    }
}
