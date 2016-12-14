package day14;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

    private static final String SALT_TEST = "abc";

    private static final String SALT_PUZZLE = "jlmsuwbz";
    private static final Integer MAX_FORWARD = 1000;

    public static void solve() {

        Md5HashGenerator testHashGenerator = new Md5HashGenerator(SALT_TEST);
        List<String> hashBuffer = new ArrayList<>();

        // I will refactor this
        Integer solutionIndex = -1;
        Integer solutionCount = 0;

        while (solutionCount < 64) {
            solutionIndex++;
            Integer qintentPointer = 0;

            //If there are no more hashes in the buffer, get and store one
            if (hashBuffer.isEmpty()) {
//				hashBuffer.add(testHashGenerator.getNextHash());    //PUZZLE-01
                hashBuffer.add(testHashGenerator.getNextLevel2016Hash());    //PUZZLE-02
            }

            String currentHash = hashBuffer.remove(0);
            Character currentTripletChar = HashUtils.getFirstTripletChar(currentHash);

            if (currentTripletChar == null) {
                continue;
            }

            while (qintentPointer < MAX_FORWARD) {

                if (hashBuffer.isEmpty() || qintentPointer > hashBuffer.size() - 1) {
//                    hashBuffer.add(testHashGenerator.getNextHash());    //PUZZLE-01
                    hashBuffer.add(testHashGenerator.getNextLevel2016Hash());    //PUZZLE-02
                }

                String lookForQintentIn = hashBuffer.get(qintentPointer);

                if (HashUtils.containsQintentOf(lookForQintentIn, currentTripletChar)) {
                    System.out.println("Solution has been found, index: " + Integer.toString(solutionIndex));
                    solutionCount++;
                    break;
//					return;
                }

                qintentPointer++;

            }

        }
    }

}
