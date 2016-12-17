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

        for (int i = dataInitialLength-1; i >= 0; i--) {
            data.add(!data.get(i));
        }
    }
    
    public void generateData(Integer length) {
        while(data.size()<length) {
            this.extendData();
        }
        
        // Trim off excess
        for(int i=length; i<=data.size();i++) {
            data.remove(data.size()-1);
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
        Day16 d = new Day16("10000");
        d.generateData(20);
        System.out.println(d);
    }

}
