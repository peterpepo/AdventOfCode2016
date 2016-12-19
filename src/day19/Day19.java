package day19;

import java.util.ArrayList;
import java.util.List;

public class Day19 {

    public static void solve() {

        // Number of elves, total
        // Test input
        int[] elves = new int[5];
        // Puzzle01 input
//        int[] elves = new int[3017957];

        // Give each elf one present
        for (int i = 0; i < elves.length; i++) {
            elves[i] = 1;
        }

        int currentElf = 0;
        boolean endOfTheGame = false;

        while (!endOfTheGame) {
            currentElf = (currentElf % elves.length);

            // If the elf still has something, he's part of the game and can steal
            if (elves[currentElf] != 0) {

                int stolenPresentsCount = 0;

                // Find next elf, who has something
                // Length is adjust by -1 (elf cannot steal from self)
                for (int i = 0; i < elves.length - 1; i++) {

                    // Adjust position
                    // If we run past last elf, return to first one etc..
                    int adjustedPosition = (currentElf + i + 1) % elves.length;

                    if (elves[adjustedPosition] != 0) {
                        stolenPresentsCount = elves[adjustedPosition];
                        elves[adjustedPosition] = 0;
                        break;
                    }

                }
                // If we didn't find anyone, the game is over
                if (stolenPresentsCount == 0) {
                    endOfTheGame = true;
                }

                // Increment elve's present count by amount, he just stole
                elves[currentElf] += stolenPresentsCount;
            }
            currentElf++;
        }

        // Find elf, who has all the presents
        for (int i = 0; i < elves.length; i++) {
            if (elves[i] != 0) {
                System.out.println(i + 1 + ". elf has all " + elves[i] + " presents");
            }
        }

    }

    public static void solve2() {

        boolean[] elves = new boolean[3017957];


        // Give each elf one present
        for (int i = 0; i < elves.length; i++) {
            elves[i] = true;
        }

        int currentElf = 0;
        boolean endOfTheGame = false;
        int elvesRemaining = elves.length;

        while (!endOfTheGame) {
            currentElf = (currentElf % elves.length);

            // If the elf still has something, he's part of the game and can steal
            if (elves[currentElf]) {

                // Find elve across the circle (if there are two, steal from closer)
                int skipElves = Math.floorDiv(elvesRemaining, 2);
//                System.out.println("Skip elves: "+skipElves);
                int adjustedPosition = -1;
                for (int i = 0; i < elves.length - 1; i++) {
                    adjustedPosition = (currentElf + i + 1) % elves.length;

                    if (elves[adjustedPosition]) {
                        skipElves--;
                    }

                    // We found the right one
                    if (skipElves == 0) {
                        break;
                    }
                }

                elves[adjustedPosition] = false;
                elvesRemaining--;

                // If we didn't find anyone, the game is over
                if (elvesRemaining == 1) {
                    endOfTheGame = true;
                }

                // Increment elve's present count by amount, he just stole
            }
            currentElf++;
        }

        // Find elf, who has all the presents
        for (int i = 0; i < elves.length; i++) {
            if (elves[i]) {
                System.out.println(i + 1 + ". elf has all ");
            }
        }

    }

    public static void solve3() {
        final Integer ELVES_COUNT = 3017957;
        List<Integer> elves = new ArrayList<>();

        for (Integer i = 0; i < ELVES_COUNT; i++) {
            elves.add(i);
        }

        Integer currentElf = 0;
        while (elves.size() > 1) {
            currentElf = currentElf % elves.size();

            int diameter = Math.floorDiv(elves.size(), 2);
            int otherElf = (currentElf + diameter) % elves.size();

            elves.set(currentElf, elves.get(currentElf) + elves.remove(otherElf));
        }

    }

    public static void solve4() {
        final Integer ELVES_COUNT = 3017957;
        List<Integer> elves = new ArrayList<>();

        for (Integer i = 0; i < ELVES_COUNT; i++) {
            elves.add(i + 1);
        }

        Integer currentElfOrder = 0;
        while (elves.size() > 1) {
            currentElfOrder = currentElfOrder % elves.size();

            int otherElfOrder = (currentElfOrder + (Math.floorDiv(elves.size(), 2))) % elves.size();

            elves.remove(otherElfOrder);

            if (otherElfOrder > currentElfOrder) {
                currentElfOrder++;
            }
        }
        System.out.println("Second puzzle: Elf #" + elves.get(0) + " has all presents");
    }
}
