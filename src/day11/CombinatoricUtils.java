package day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombinatoricUtils {

    public static List<String> getPermutations(List<String> sourceList, int length) {
        if (length == 1) {
            return sourceList;
        } else {
            List<String> result = new ArrayList<>();

            for (String cs : sourceList) {
                List<String> reducedList = new ArrayList<>(sourceList);
                reducedList.remove(cs);

                for (String s : getPermutations(reducedList, length - 1)) {
                    result.add(cs + s);
                }
            }
            return result;

        }
    }

    public static List<List<String>> getPermutationsList(List<String> sourceList, int length) {
        List<List<String>> result = new ArrayList<List<String>>();

        if (length == 1) {
            for (String s : sourceList) {
                List<String> r = new ArrayList<>();
                r.add(s);
                result.add(r);
            }
            return result;
        } else {
            for (String s : sourceList) {

                List<String> reducedList = new ArrayList<>(sourceList);
                reducedList.remove(s);

                for (List<String> listOfSubsequent : getPermutationsList(reducedList, length - 1)) {
                    List<String> r = new ArrayList<>();
                    r.add(s);
                    r.addAll(listOfSubsequent);
                    if (result.containsAll(r)) {
                        continue;
                    } else {
                        Collections.sort(r);
                        if (result.contains(r)) {
                            continue;
                        } else {
                            result.add(r);
                        }
                    }
                }
            }
            return result;

        }
    }
}
