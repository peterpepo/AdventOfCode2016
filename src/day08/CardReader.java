package day08;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardReader {

    private Display display;

    public CardReader(Display newDisplay) {
        display = newDisplay;
    }

    public void processInstruction(String instruction) {
        List<Integer> parameters = new ArrayList<>();

        Pattern p;
        Matcher m;

        // All operations contain 2 numbers, get them first
        p = Pattern.compile("\\d+");
        m = p.matcher(instruction);

        while (m.find()) {
            parameters.add(Integer.parseInt(m.group()));
        }

        // test for rect 1x1 operation
        // rect \d+x\d+
        p = Pattern.compile("rect \\d+x\\d+");
        m = p.matcher(instruction);

        if (m.find()) {
            display.createRectangle(parameters.get(1), parameters.get(0));
            return;
        }

        // test for rotate row y=0 by 20 operation
        // rotate row y=\d+ by \d+
        p = Pattern.compile("rotate row y=\\d+ by \\d+");
        m = p.matcher(instruction);

        if (m.find()) {
            display.rotateRow(parameters.get(0), parameters.get(1));
            return;
        }

        // test for rotate column x=5 by 1
        // rotate column x=\d+ by \d+
        p = Pattern.compile("rotate column x=\\d+ by \\d+");
        m = p.matcher(instruction);

        if (m.find()) {
            display.rotateColumn(parameters.get(0), parameters.get(1));
            return;
        }

    }
}
