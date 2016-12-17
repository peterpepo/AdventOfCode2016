package day16;

import java.util.ArrayList;
import java.util.List;

public class Day16 {

    private List<Boolean> data = new ArrayList<>();

    public Day16(String initString) {
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
//            System.out.println(this);
            this.extendData();
        }

        // Trim off excess length
        while (data.size() > length) {
            data.remove(data.size() - 1);
        }
    }

    public void generateCheckSum() {
        Integer originalDataSize = data.size();

        for (int i = 0; i < originalDataSize / 2; i++) {
//            System.out.println("Reducing: "+i);
            data.add(data.get(0).equals(data.get(1)));
            data.remove(0);
            data.remove(0);
        }

        
        if ((data.size() % 2) != 1) {
            System.out.println(data.size());
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
        Day16 d = new Day16("01000100010010111");
//        d.generateData(1000000);
        d.generateData(35651584);
//        System.out.println("Original data:" + d);
        d.generateCheckSum();
//        System.out.println("Checksum:" + d);
    }

}
