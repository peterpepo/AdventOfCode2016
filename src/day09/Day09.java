package day09;

import commons.PuzzleInputReader;

public class Day09 {

    public static void solve() {
        PuzzleInputReader testPuzzleInput = new PuzzleInputReader("src/day09/Day09-puzzleInput.txt");
        
        for (String s : testPuzzleInput.getListOfLines()) {
            System.out.println("First puzzle: "+ ExperimentalFormat.decompressOneLevel(s).length());
            System.out.println("Second puzzle: "+ ExperimentalFormat.getDecompressFullLength(s));
        }
    }

}
