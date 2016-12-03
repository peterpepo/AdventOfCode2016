package day02;

import commons.PuzzleInputReader;

public class Day02 {
	
	public static void solve() {


		PuzzleInputReader firstPuzzleInput = new PuzzleInputReader("src/day02/firstPuzzleInput.txt");

		// First puzzle
		ButtonInstructionReader firstTask = new ButtonInstructionReader(new NineDigitKeyboard());
		
		System.out.print("First puzzle: ");
		for(String s:firstPuzzleInput.getListOfLines()) {
			System.out.print(firstTask.getButtonByLine(s));
		}
		
		// Linebreak
		System.out.println();
		
		// Second puzzle
		ButtonInstructionReader secondTask = new ButtonInstructionReader(new StarKeyboard());
		
		System.out.print("Second puzzle: ");
		for(String s:firstPuzzleInput.getListOfLines()) {
			System.out.print(secondTask.getButtonByLine(s));
		}
		
		System.out.println();
		
		
		

	}

}
