package day07;

import commons.PuzzleInputReader;

public class Day07 {

    public static void solve() {

        int supportTLSCount = 0;
        int supportSLSCount = 0;

        PuzzleInputReader firstPuzzleInput = new PuzzleInputReader("src/day07/Day07-puzzleInput.txt");
        for (String s : firstPuzzleInput.getListOfLines()) {
            Address a = new Address(s);
            if (a.supportsTLS()) {
                supportTLSCount++;
            }
            
            if (a.supportsSLS()) {
                supportSLSCount++;
            }

        }
        // FIRST PUZZLE
        System.out.println("First puzzle: " + supportTLSCount);
        
        // SECOND PUZZLE
        System.out.println("Second puzzle: " + supportSLSCount);
    }

}
