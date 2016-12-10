package day08;

import commons.PuzzleInputReader;

public class Day08 {

    public static void solve() {
        Display sixByFiftyDisplay = new Display(6, 50);
        CardReader cardReader = new CardReader(sixByFiftyDisplay);

        PuzzleInputReader testPuzzleInput = new PuzzleInputReader("src/day08/Day08-puzzleInput.txt");
        for (String s : testPuzzleInput.getListOfLines()) {
            cardReader.processInstruction(s);
        }

        // FIRST PUZZLE
        System.out.println("First puzzle: " + sixByFiftyDisplay.getOnCount());

        // SECOND PUZZLE
        System.out.println("Second puzzle: ");
        System.out.println(sixByFiftyDisplay);

    }

}
