package day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.PuzzleInputReader;

public class Day03 {

	public static void solve() {
		int validTrianglesCount = 0;

		// Regexp pattern - Sequence of digits, e.g 123
		Pattern p = Pattern.compile("\\d+");

		// Regexp matcher
		Matcher m;

		PuzzleInputReader firstPuzzleInput = new PuzzleInputReader("src/day03/Day03-firstPuzzleInput.txt");

		// FIRST PUZZLE - Solve row by row
		for (String s : firstPuzzleInput.getListOfLines()) {

			m = p.matcher(s);

			int dimCount = 0;
			int[] dimensions = new int[3];

			// Loop through all numbers in input
			while (m.find()) {
				// If there were more than three numbers, stop
				if (dimCount >= 3) {
					break;
				}
				dimensions[dimCount] = Integer.parseInt(m.group());
				dimCount++;
			}

			// If three dimensions per row are found, otherwise we don't even
			// create triangle
			if (dimCount == 3) {
				Triangle t = new Triangle(dimensions[0], dimensions[1], dimensions[2]);

				if (t.isValid()) {
					validTrianglesCount++;
				}

			}
		}

		System.out.println("First puzzle: " + validTrianglesCount);

		// SECOND PUZZLE - Solve by columns

		validTrianglesCount = 0; // reset counter
		int[][] dimensions = new int[3][3]; // dimensions buffer
		int row = 0; // current row counter

		for (String s : firstPuzzleInput.getListOfLines()) {
			int col = 0; // current column counter

			m = p.matcher(s);

			// Loop through all numbers in input
			while (m.find()) {
				// when number is found store it in buffer [row-th][col-th]
				dimensions[row][col++] = Integer.parseInt(m.group());
			}

			// buffer full, reset counter and construct triangles
			if (row == 2) {
				row = 0;

				for (int i = 0; i <= 2; i++) {

					Triangle t = new Triangle(dimensions[0][i], dimensions[1][i], dimensions[2][i]);

					if (t.isValid()) {
						validTrianglesCount++;
					}
				}

			} else {
				row++;
			}
		}
		System.out.println("Second puzzle: " + validTrianglesCount);
	}

}
