package day06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

    private Map<Integer, Map<Character, Integer>> message = new HashMap<Integer, Map<Character, Integer>>();

    public Message() {

    }

    public void addCharacter(Integer columnId, Character c) {
        Map<Character, Integer> currentColumn = message.get(columnId);

        if (currentColumn == null) {
            currentColumn = new HashMap<Character, Integer>();
            currentColumn.put(c, 1);

            message.put(columnId, currentColumn);
        } else {
            if (currentColumn.get(c) == null) {
                currentColumn.put(c, 1);
            } else {
                currentColumn.replace(c, currentColumn.get(c) + 1);
            }
        }
    }

    public void addString(String source) {
        for (int i = 0; i < source.length(); i++) {
            addCharacter(i, source.charAt(i));
        }
    }

    private List getWithSpecifiedOccurence(int occurence, Map<Character, Integer> source) {
        List resultList = new ArrayList<Character>();

        for (Character c : source.keySet()) {
            if (source.get(c) == occurence) {
                resultList.add(c);
            }
        }

        return resultList;

    }

    private Character getMostFrequent(Integer columnId) {
        Map<Character, Integer> currentColumn = message.get(columnId);

        int maximumOccurences = Collections.max(currentColumn.values());

        return (Character) getWithSpecifiedOccurence(maximumOccurences, currentColumn).get(0);
    }

    public String getCleanedUpMessageMostRepeated() {
        StringBuilder sb = new StringBuilder();

        for (Integer column : message.keySet()) {
            sb.append(this.getMostFrequent(column));
        }

        return sb.toString();
    }

    private Character getLeastFrequent(Integer columnId) {
        Map<Character, Integer> currentColumn = message.get(columnId);

        int maximumOccurences = Collections.min(currentColumn.values());

        return (Character) getWithSpecifiedOccurence(maximumOccurences, currentColumn).get(0);
    }

    public String getCleanedUpMessageLeastRepeated() {
        StringBuilder sb = new StringBuilder();

        for (Integer column : message.keySet()) {
            sb.append(this.getLeastFrequent(column));
        }

        return sb.toString();
    }

}
