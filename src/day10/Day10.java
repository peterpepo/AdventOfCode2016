package day10;

import commons.PuzzleInputReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {

    public static void solve() {
        WorkArena wa = new WorkArena("Workplace #01");

        PuzzleInputReader testPuzzleInput = new PuzzleInputReader("src/day10/Day10-puzzleInput.txt");
        Pattern p;
        Matcher m;

        // Distribute values to robots
        p = Pattern.compile("value \\d+ goes to bot \\d+");

        for (String s : testPuzzleInput.getListOfLines()) {

            m = p.matcher(s);

            List<String> lineParameters = new ArrayList<>();

            while (m.find()) {
                Pattern p2 = Pattern.compile("\\d+");
                Matcher m2 = p2.matcher(m.group());

                while (m2.find()) {
                    lineParameters.add(m2.group());
                }

                Robot newRobot = wa.getRobot(lineParameters.get(1));
                wa.addRobot(newRobot);

                newRobot.takeChip(new Chip(Integer.parseInt(lineParameters.get(0))));
            }

        }


        System.out.println("~~~~~");

        // Define rules
        p = Pattern.compile("bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)");

        for (String s : testPuzzleInput.getListOfLines()) {

            m = p.matcher(s);

            while (m.find()) {

                Robot source = wa.getRobot(m.group(1));

                ChipTakeable target1;
                if (m.group(2).equals("bot")) {
                    target1 = wa.getRobot(m.group(3));
                } else {
                    target1 = wa.getBox(m.group(3));
                }

                ChipTakeable target2;
                if (m.group(4).equals("bot")) {
                    target2 = wa.getRobot(m.group(5));
                } else {
                    target2 = wa.getBox(m.group(5));
                }

                wa.addRule(source, ChipCompare.LOW, target1);
                wa.addRule(source, ChipCompare.HIGH, target2);
            }

        }

        System.out.println("First puzzle: ");
        while (!wa.isFinished()) {
            wa.playRound();
        }

        // SECOND PUZZLE
        Integer secondPuzzle = 1;
        for (Box b : wa.getBoxes()) {
            if (b.getName().equals("0") || b.getName().equals("1") || b.getName().equals("2")) {
                secondPuzzle *= b.getValueOfChips();
            }
        }
        System.out.println("Second puzzle: " + secondPuzzle);

    }

}
