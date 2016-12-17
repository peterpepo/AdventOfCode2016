package day16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day16 {

    private List<Boolean> data;

    public Day16(String initString) {
        data = new ArrayList<>();

        for (Character c : initString.toCharArray()) {
            if (c == '0') {
                data.add(Boolean.FALSE);
            } else {
                data.add(Boolean.TRUE);
            }
        }
    }

    private void extendData() {
        Integer dataInitialLength = data.size();

        data.add(Boolean.FALSE);

        for (int i = dataInitialLength - 1; i >= 0; i--) {
            data.add(!data.get(i));
        }
    }

    public void generateData(Integer length) {
        while (data.size() < length) {
            this.extendData();
        }

        // Trim off excess length
        while (data.size() > length) {
            data.remove(data.size() - 1);
        }
    }

    public void generateCheckSum() {
        /*
        *If data is stored as ArrayList, convert it to a Linked List
        *As we are going to pop items from beginning of a list, LinkedList is much faster
         */
        if (data instanceof ArrayList) {
            data = new LinkedList(data);
        }

        Integer originalDataSize = data.size();

        for (int i = 0; i < originalDataSize / 2; i++) {
            data.add(data.remove(0).equals(data.remove(0)));
        }

        if ((data.size() % 2) != 1) {
            generateCheckSum();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Boolean b : data) {
            if (b.equals(Boolean.FALSE)) {
                sb.append('0');
            } else {
                sb.append('1');
            }
        }
        return sb.toString();
    }

    public static void solve() {
        final String PUZZLE_INPUT = "01000100010010111";

        Day16 puzzle1 = new Day16(PUZZLE_INPUT);
        puzzle1.generateData(272);
        puzzle1.generateCheckSum();
        System.out.println("First puzzle: " + puzzle1);

        Day16 puzzle2 = new Day16(PUZZLE_INPUT);
        puzzle2.generateData(35651584);
        puzzle2.generateCheckSum();
        System.out.println("Second puzzle: " + puzzle2);
    }
}
