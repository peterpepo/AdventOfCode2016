package day04;

import commons.PuzzleInputReader;
import java.util.ArrayList;
import java.util.List;

public class Day04 {

    public static void solve() {
        /*
        While there's place for refactoring, e.g not iterating through the input
        twice, I opted for easier to read code (strict split between puzzles
        */

        PuzzleInputReader firstPuzzleInput = new PuzzleInputReader("src/day04/Day04-puzzleInput.txt");

        // FIRST PUZZLE
        int firstPuzzleSectorSum = 0;
        for (String s : firstPuzzleInput.getListOfLines()) {
            Room testRoom = new Room(s);

            // If the hash is valid, sum up sector id to the total
            if (testRoom.isHashValid()) {
                firstPuzzleSectorSum += testRoom.getRoomSectorId();
            }
        }
        System.out.println("First puzzle: " + firstPuzzleSectorSum);

        // SECOND PUZZLE
        /*
        From puzzle definition, it was not clear, for what value are we looking for.
        Therefore I checked decryption results, by description "What is the sector ID of
        the room where North Pole objects are stored?", founding this value.
        Purpose is to avoid spamming console with all decrypted values.
        */
        final String WANTED_ROOM = "northpole object storage";

        for (String s : firstPuzzleInput.getListOfLines()) {
            Room testRoom = new Room(s);

            if (testRoom.isHashValid()) {
                if (testRoom.getDecryptedRoomNumber().equals(WANTED_ROOM)) {
                    System.out.println("Second puzzle: " + testRoom.getRoomSectorId());
                }
            }
        }
    }

}
