package day19;

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
                System.out.println(i+1 + ". elf has all " + elves[i] + " presents");
            }
        }

    }
}
