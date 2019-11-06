package main.java.lab2;

import java.util.List;

import static java.lang.Math.abs;

public class RScriptBuilder {
    private final static String DATA = "data <- data.frame (portion=c(&&&), time=c(&&&))";
    private final static String BOX_PLOT = "boxplot(time ~ cut(portion, breaks=c(&&&), include.lowest = TRUE), data = data, xlab = \"Portion\", ylab = \"Time\" )";

    public String getScript(List<Integer> portions, List<String> times, int portionRange, int numOfRanges) {
        StringBuilder portionBuilder = new StringBuilder();
        StringBuilder timeBuilder = new StringBuilder();
        StringBuilder breakBuilder = new StringBuilder();

        for (int portion : portions) portionBuilder.append(abs(portion)).append(", ");

        for (String time : times) {
            if (time.contains(",")) time = time.replace(",", ".");
            timeBuilder.append(time).append(", ");
        }

        for (int i = 0; i <= numOfRanges; i++) {
            breakBuilder.append(i * portionRange / numOfRanges).append(", ");
        }

        String Portions = cutLastChar(portionBuilder.toString());
        String Times = cutLastChar(timeBuilder.toString());
        String Breaks = cutLastChar(breakBuilder.toString());

        String Data = DATA.replaceFirst("&&&", Portions);
        Data = Data.replaceFirst("&&&", Times);
        String BoxPlot = BOX_PLOT.replaceFirst("&&&", Breaks);

        return (Data + "\n" + BoxPlot);
    }


    private String cutLastChar(String str) {
        if (str != null && str.length() > 1) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }
}
