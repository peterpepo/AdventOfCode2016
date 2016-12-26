package day11;

import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static Set<Set<Item>> getCombinationsOfLength(Set<Item> sourceItems, int length) {
        Set<Set<Item>> resultSet = new HashSet<>();

        if (length == 1) {
            for (Item item : sourceItems) {
                Set<Item> currentSet = new HashSet<>();
                currentSet.add(item);
                resultSet.add(currentSet);
            }
        } else {
            for (Item item : sourceItems) {

                Set<Item> reducedSet = new HashSet<>(sourceItems);
                reducedSet.remove(item);

                for (Set<Item> reducedItemSet : getCombinationsOfLength(reducedSet, length - 1)) {
                    Set<Item> currentSet = new HashSet<>();
                    currentSet.add(item);
                    currentSet.addAll(reducedItemSet);
                    resultSet.add(currentSet);
                }

            }

        }

        return resultSet;
    }

}
