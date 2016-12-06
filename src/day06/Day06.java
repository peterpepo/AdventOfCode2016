package day06;

import commons.PuzzleInputReader;

public class Day06 {

    public static void solve() {

        Message m1 = new Message();

        PuzzleInputReader firstPuzzleInput = new PuzzleInputReader("src/day06/Day06-puzzleInput.txt");

        
        for (String s : firstPuzzleInput.getListOfLines()) {
            m1.addString(s);
        }
        
        // FIRST PUZZLE
        System.out.println("First puzzle: " + m1.getCleanedUpMessageMostRepeated());
        
        // SECOND PUZZLE
        System.out.println("Second puzzle: " + m1.getCleanedUpMessageLeastRepeated());
    }

}
