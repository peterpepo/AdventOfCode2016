package day14;

import java.util.ArrayList;
import java.util.List;

public class HashDealer {

    private HashGenerator hashGen;
    private List<String> hashBuffer;
    private Integer currentHashIndex = -1;

    private final Integer BUFFER_CAPACITY = 1000;

    public HashDealer(HashGenerator hashGen) {
        this.hashGen = hashGen;
        this.hashBuffer = new ArrayList<>();
    }

    /**
     * Returns next "nice" hash (contains TRIPLET of Character, which occurs as
     * QINTENT in next 1000 hashes provided by HashGenerator.
     */
    public String getNextOneTime() {

        boolean solutionHasBeenFound = false;

        String currentHash = null;

        while (!solutionHasBeenFound) {
            currentHashIndex++;
            Integer qintentPointer = 0;

            // If there are no more hashes in the buffer, get and store one
            if (hashBuffer.isEmpty()) {
                hashBuffer.add(HashUtils.getHexOfByteArray(hashGen.getNextHash()));
            }

            // Pop first hash in the buffer
            currentHash = hashBuffer.remove(0);

            // Check whether it contains a TRIPLET
            Character currentTripletChar = HashUtils.getFirstTripletChar(currentHash);

            // Continue with next hash, if it doesn't contain a TRIPLET
            if (currentTripletChar == null) {
                continue;
            }

            /*
            Look in next 1000 hashes, whether they contain QINTENT of character,
            previously found in TRIPLET.
            Term BUFFER_CAPACITY might mislead. I opted to merge it with maximum
            number of hashes in which we are looking for qintent.
            In reality those might be two completely different numbers.
            Although: it doesn't really make sense to have buffer bigger than 1000,
            if we will read only those in next step.
            In fact, buffer is growing continuously, up to BUFFER_CAPACITY in order
            to avoid recalculating, if next nice hash is required.
             */
            while (qintentPointer < BUFFER_CAPACITY) {

                /*
                Put another hash, if we reached end of buffer, but not 1000,
                maximum forward
                 */
                if (hashBuffer.isEmpty() || qintentPointer > hashBuffer.size() - 1) {
                    hashBuffer.add(HashUtils.getHexOfByteArray(hashGen.getNextHash()));
                }

                // Get n-th member of our buffer
                String lookForQintentIn = hashBuffer.get(qintentPointer);

                // If we find a quintent, set driving variable and stop looking for it
                if (HashUtils.containsQintentOf(lookForQintentIn, currentTripletChar)) {
                    solutionHasBeenFound = true;
                    break;
                }

                qintentPointer++;

            }

        }
        return currentHash;
    }

    /**
     * Return index(order) of current solution in HashGenerator provided
     */
    public Integer currentHashIndex() {
        return this.currentHashIndex;
    }

}
