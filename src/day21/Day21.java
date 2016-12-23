package day21;

import commons.PuzzleInputReader;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 {

    private static String solvePuzzle(Scrambler scrambler, List<String> instructions) {
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
        for (String s : instructions) {
            m = swapPositionWithPosition.matcher(s);
            if (m.find()) {
                scrambler.swapByIndex(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                continue;
            }

            m = swapLetterWithLetter.matcher(s);
            if (m.find()) {
                scrambler.swapByChar(m.group(1).charAt(0), m.group(2).charAt(0));
                continue;
            }

            m = rotateLeftSteps.matcher(s);
            if (m.find()) {
                scrambler.rotateLeft(Integer.parseInt(m.group(1)));
                continue;
            }

            m = rotateRightSteps.matcher(s);
            if (m.find()) {
                scrambler.rotateRight(Integer.parseInt(m.group(1)));
                continue;
            }

            m = rotateBasedOnCharacter.matcher(s);
            if (m.find()) {
                scrambler.rotateBasedOnCharPosition(m.group(1).charAt(0));
                continue;
            }

            m = reversePositionsFromTo.matcher(s);
            if (m.find()) {
                scrambler.reversePart(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                continue;
            }

            m = movePositionToPosition.matcher(s);
            if (m.find()) {
                scrambler.movePosition(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                continue;
            }
        }

        return scrambler.toString();
    }

    public static void solve() {

        /*
        Common
         */
        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day21/Day21-puzzleInput.txt");

        /*
        PUZZLE - 01
        result: bgfacdeh
         */
        Scrambler puzzle1 = new Scrambler("abcdefgh");
        System.out.println("First puzzle: " + solvePuzzle(puzzle1, puzzleInput.getListOfLines()));

        /*
        PUZZLE - 02
        result: abfdgceh
         */
        UnScrambler puzzle2 = new UnScrambler("bdgheacf");
        List<String> reversedInstructions = puzzleInput.getListOfLines();
        Collections.reverse(reversedInstructions);
        System.out.println("Second puzzle: " + solvePuzzle(puzzle2, puzzleInput.getListOfLines()));

    }

}
