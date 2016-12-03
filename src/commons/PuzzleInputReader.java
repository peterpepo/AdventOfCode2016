package commons;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PuzzleInputReader {
	private List<String> lines;
	
	public PuzzleInputReader(String fileName) {
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println("Error reading file: "+fileName);
		}
	}
	
	public List<String> getListOfLines() {
		return lines;
	}

}
