package day18;

public class Day18 {
    public static void solve() {
        /*
        final String TEST_INPUT = "..^^.";
        
        Floor puzzle1Test = new Floor(TEST_INPUT.length(), TEST_INPUT.length(), TEST_INPUT);
        
        System.out.println(puzzle1Test);
        puzzle1Test.calculateTiles();
        System.out.println(puzzle1Test);
        */
        
        final String PUZZLE_INPUT = "^.^^^.^..^....^^....^^^^.^^.^...^^.^.^^.^^.^^..^.^...^.^..^.^^.^..^.....^^^.^.^^^..^^...^^^...^...^.";
        Floor puzzle1Floor = new Floor(PUZZLE_INPUT);
        puzzle1Floor.calculateTiles();
        System.out.println("First puzzle: "+puzzle1Floor.countSafeTiles(40, PUZZLE_INPUT.length()));
        
        Floor puzzle2Floor = new Floor(PUZZLE_INPUT.length(), 400000, PUZZLE_INPUT);
        puzzle2Floor.calculateTiles();
        System.out.println("Second puzzle: "+puzzle2Floor.countSafeTiles(400000, PUZZLE_INPUT.length()));

        
    }
    
}
