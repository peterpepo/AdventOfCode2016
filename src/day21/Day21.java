package day21;

import commons.PuzzleInputReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 {

    public static void solve() {
        final String PUZZLE_INPUT = "abcdefgh";
        
        /*
        PUZZLE - 01
         */
        Scrambler puzzle = new Scrambler(PUZZLE_INPUT);
        
        

        /*
        REGEXP PATTERNS FOR EACH OF COMMANDS
         */
        Pattern swapPositionWithPosition = Pattern.compile("swap position (\\d)+ with position (\\d)+");
        Pattern swapLetterWithLetter = Pattern.compile("swap letter (\\w) with letter (\\w)");
        Pattern rotateLeftSteps = Pattern.compile("rotate left (\\d)+ step");
        Pattern rotateRightSteps = Pattern.compile("rotate right (\\d)+ step");
        Pattern rotateBasedOnCharacter = Pattern.compile("rotate based on position of letter (\\w)");
        Pattern reversePositionsFromTo = Pattern.compile("reverse positions (\\d)+ through (\\d)+");
        Pattern movePositionToPosition = Pattern.compile("move position (\\d)+ to position (\\d)+");
        
        Matcher m;
        
        PuzzleInputReader firstPuzzleInput = new PuzzleInputReader("src/day21/Day21-puzzleInput.txt");
        // Load all intervals from puzzle input
        for (String s : firstPuzzleInput.getListOfLines()) {
            System.out.println(puzzle);
            System.out.println("-~-~-~-~-~");
            System.out.println(s);
            m = swapPositionWithPosition.matcher(s);
            if(m.find()) {
                puzzle.swapByIndex(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                continue;
            }
            
            m = swapLetterWithLetter.matcher(s);
            if(m.find()) {
                puzzle.swapByChar(m.group(1).charAt(0), m.group(2).charAt(0));
                continue;
            }
            
            m = rotateLeftSteps.matcher(s);
            if(m.find()) {
                puzzle.rotateLeft(Integer.parseInt(m.group(1)));
                continue;
            }
            
            m = rotateRightSteps.matcher(s);
            if(m.find()) {
                puzzle.rotateRight(Integer.parseInt(m.group(1)));
                continue;
            }
            
            m = rotateBasedOnCharacter.matcher(s);
            if(m.find()) {
                puzzle.rotateBasedOnCharPosition(m.group(1).charAt(0));
                continue;
            }
                    
            m = reversePositionsFromTo.matcher(s);
            if(m.find()) {
                puzzle.reversePart(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2)));
                continue;
            }
            
            m = movePositionToPosition.matcher(s);
            if(m.find()) {
                puzzle.movePosition(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2)));
                continue;
            }
        }
System.out.println(puzzle);

//System.out.println("~~~TEST~~~");
//Scrambler test = new Scrambler("abcde");
//test.swapByIndex(4, 0);
//test.swapByChar('d', 'b');
//test.reversePart(0, 4);
//test.rotateLeft(1);
//test.movePosition(1, 4);
//test.movePosition(3, 0);
//test.rotateBasedOnCharPosition('b');
//test.rotateBasedOnCharPosition('d');
//System.out.println(test);
    }

}
