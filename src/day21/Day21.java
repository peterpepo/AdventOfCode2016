package day21;

import commons.PuzzleInputReader;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 {

    public static void solve() {
        final String PUZZLE1_INPUT = "abcdefgh";

        /*
        PUZZLE - 01
         */
        Scrambler puzzle = new Scrambler(PUZZLE1_INPUT);

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
//        // Load all intervals from puzzle input
//        for (String s : firstPuzzleInput.getListOfLines()) {
//            System.out.println(puzzle);
//            System.out.println("-~-~-~-~-~");
//            System.out.println(s);
//            m = swapPositionWithPosition.matcher(s);
//            if(m.find()) {
//                puzzle.swapByIndex(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
//                continue;
//            }
//            
//            m = swapLetterWithLetter.matcher(s);
//            if(m.find()) {
//                puzzle.swapByChar(m.group(1).charAt(0), m.group(2).charAt(0));
//                continue;
//            }
//            
//            m = rotateLeftSteps.matcher(s);
//            if(m.find()) {
//                puzzle.rotateLeft(Integer.parseInt(m.group(1)));
//                continue;
//            }
//            
//            m = rotateRightSteps.matcher(s);
//            if(m.find()) {
//                puzzle.rotateRight(Integer.parseInt(m.group(1)));
//                continue;
//            }
//            
//            m = rotateBasedOnCharacter.matcher(s);
//            if(m.find()) {
//                puzzle.rotateBasedOnCharPosition(m.group(1).charAt(0));
//                continue;
//            }
//                    
//            m = reversePositionsFromTo.matcher(s);
//            if(m.find()) {
//                puzzle.reversePart(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2)));
//                continue;
//            }
//            
//            m = movePositionToPosition.matcher(s);
//            if(m.find()) {
//                puzzle.movePosition(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2)));
//                continue;
//            }
//        }
//System.out.println(puzzle);

        /*
        char[] testChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        for (char c : testChars) {
            UnScrambler unScrambler = new UnScrambler(PUZZLE_INPUT);
            System.out.println("Before: " + unScrambler);
            unScrambler.rotateBasedOnCharPosition(c);
            System.out.println("Scrambled: " + unScrambler);
            unScrambler.unRotateBasedOnCharPosition(c);
            System.out.println("UnScrambled: " + unScrambler);
            System.out.println("~~~");
        }
         */
        
        
        final String PUZZLE2_INPUT = "fbgdceah";
        UnScrambler puzzle2 = new UnScrambler(PUZZLE2_INPUT);
        // Load all intervals from puzzle input
        List<String> reversedInstructions = firstPuzzleInput.getListOfLines();
        Collections.reverse(reversedInstructions);
        
        for (String s : reversedInstructions) {
            System.out.println(puzzle2);
            System.out.println("-~-~-~-~-~");
            System.out.println(s);
            m = swapPositionWithPosition.matcher(s);
            if(m.find()) {
                puzzle2.swapByIndex(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                continue;
            }
            
            m = swapLetterWithLetter.matcher(s);
            if(m.find()) {
                puzzle2.swapByChar(m.group(1).charAt(0), m.group(2).charAt(0));
                continue;
            }
            
            m = rotateLeftSteps.matcher(s);
            if(m.find()) {
                puzzle2.rotateLeft(Integer.parseInt(m.group(1)));
                continue;
            }
            
            m = rotateRightSteps.matcher(s);
            if(m.find()) {
                puzzle2.rotateRight(Integer.parseInt(m.group(1)));
                continue;
            }
            
            m = rotateBasedOnCharacter.matcher(s);
            if(m.find()) {
                puzzle2.rotateBasedOnCharPosition(m.group(1).charAt(0));
                continue;
            }
                    
            m = reversePositionsFromTo.matcher(s);
            if(m.find()) {
                puzzle2.reversePart(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2)));
                continue;
            }
            
            m = movePositionToPosition.matcher(s);
            if(m.find()) {
                puzzle2.movePosition(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2)));
                continue;
            }
        }
System.out.println(puzzle2);
    }

}
