package day20;

import commons.PuzzleInputReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day20 {

    /*
    PUZZLE01 - 22887907
    PUZZLE02 - 109
     */
    public static void solve() {
        List<Interval> sourceIntervalList = new ArrayList<>();

        PuzzleInputReader firstPuzzleInput = new PuzzleInputReader("src/day20/Day20-puzzleInput.txt");

        // Load all intervals from puzzle input
        for (String s : firstPuzzleInput.getListOfLines()) {
            // lower and upper boundaries are split by '-' character
            String[] boundaries = s.split("-");
            sourceIntervalList.add(new Interval(Long.parseLong(boundaries[0]), Long.parseLong(boundaries[1])));
        }

        // Sort intervals in ascending order
        sourceIntervalList.sort(new IntervalComparator());

        // Merge overlapping intervals
        List<Interval> mergedIntervalList = new ArrayList<>();

        // Add first interval top top of the stack
        mergedIntervalList.add(0, sourceIntervalList.remove(0));

        while (!sourceIntervalList.isEmpty()) {
            // Get topmost (biggest) interval known
            Interval currentTopInterval = mergedIntervalList.get(0);

            // Take next from remaining
            Interval currentInterval = sourceIntervalList.remove(0);

            /*
            If currently checked interval doesn't overlap, add it to the top of the stack.
            It's obviously larger as previous one, as we sorted source stack asc.
             */
            if (currentTopInterval.getUpperBound() < currentInterval.getLowerBound()) {

                mergedIntervalList.add(0, currentInterval);

            } /*
            If the current interval overlaps biggest known
            (LOWER is already >= than known due to sort, thus we check if UPPER > UPPER of known)
            Expand the currently biggest known to this upper limit.
             */ else if (currentTopInterval.getUpperBound() < currentInterval.getUpperBound()) {

                currentTopInterval.setUpperBound(currentInterval.getUpperBound());

            }

        }

        /*
        The biggest is on top, sort once again (not mandatory, purpose is more
        readable solution).
        If we omit this step, we need to modify loops for finding puzzle solutions.
        Instead of current.lower - previous.upper, do current.upper - previous.lower
        (basically looping in opposite direction)
         */
        mergedIntervalList.sort(new IntervalComparator());

        // FIND FIRST PUZZLE
        Interval previousInterval = null;

        for (Interval currentInterval : mergedIntervalList) {
            // If there is no previous, load and continue
            if (previousInterval == null) {
                previousInterval = currentInterval;
                continue;
            }
            /*
            +1 and <, because between <0,1> and <2,3> there is no solutions
            Between <0,1> and <3,4> there is though
             */
            if (previousInterval.getUpperBound() + 1 < currentInterval.getLowerBound()) {
                System.out.println("First puzzle: " + (previousInterval.getUpperBound() + 1));
                break;
            }
            previousInterval = currentInterval;
        }

        // FIND SECOND PUZZLE
        long validIpCount = 0;
        previousInterval = null;
        /*
        Same loop as first puzzle.
        Instead of breaking after first "gap" we continue until the end.
         */
        for (Interval currentInterval : mergedIntervalList) {
            if (previousInterval == null) {
                previousInterval = currentInterval;
                continue;
            }

            /*
            -1, because betweeen <0,1> and <1,2> there is no free ip
            between <0,1> and <3,4> there is one.
            ********************************************************************
            We don't have to care about situations like <0,1><1,2> which would
            actually decrease total count (1-1-1 = -1), due to algorithm we used.
            If we had such intervals in source, it got merged into <0,2> already.
             */
            validIpCount += (currentInterval.getLowerBound() - previousInterval.getUpperBound() - 1);

            previousInterval = currentInterval;
        }
        System.out.println("Second puzzle: " + validIpCount);

    }

}
