package day24;

import commons.PuzzleInputReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Day24 {

    private static int findMinimalPath(List<String> mazeLines, Point startPoint, Point endPoint) {
        Maze testMaze = new Maze(mazeLines);

        int rowNum[] = {-1, 0, 0, 1};
        int colNum[] = {0, -1, 1, 0};

        PriorityQueue<QueueNode<Point>> mazeQueue = new PriorityQueue<>(new QueueNodeComparator());

        // Mark Point as visited
        testMaze.visit(startPoint.getX(), startPoint.getY());

        // Start Node - Point[0,0]
        mazeQueue.add(new QueueNode(startPoint, 0, 0));

        while (!mazeQueue.isEmpty()) {
//            System.out.println("[INFO]\tQueue size: " + mazeQueue.size());
            QueueNode<Point> currentNode = mazeQueue.remove();

            // If we find solution in this node, print it and end
            if (currentNode.getContent().equals(endPoint)) {
//                System.out.println("Puzzle1: " + currentNode.getDistance());
                return currentNode.getDistance();
            }

            // Go every possible way
            for (int i = 0; i < 4; i++) {
                int newY = currentNode.getContent().getY() + rowNum[i];
                int newX = currentNode.getContent().getX() + colNum[i];

                // if the point is on the map and we haven't visited this place before
                if (testMaze.isOnMap(newX, newY) && !testMaze.isVisited(newX, newY) && testMaze.getFree(newX, newY)) {
                    testMaze.visit(newX, newY);
                    mazeQueue.add(new QueueNode(new Point(newX, newY), currentNode.getDistance() + 1, currentNode.getDistance() + 1));
                }
            }

        }
        return -1;
    }

    /*
    Credits: http://stackoverflow.com/a/10305419
     */
    private static <E> List<List<E>> generatePerm(List<E> original) {
        if (original.size() == 0) {
            List<List<E>> result = new ArrayList<List<E>>();
            result.add(new ArrayList<E>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<List<E>>();
        List<List<E>> permutations = generatePerm(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<E>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    public static void solvePuzzle(int puzzleNum) {
        /*
        Load Map
         */
        // Puzzle Map
        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day24/Day24-puzzleInput.txt");
        // Test Map
//        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day24/Day24-testInput.txt");

        /*
        Discover all vents in the map
         */
        List<Point> vents = new ArrayList<>();

        Point initialPosition = null;

        for (int y = 0; y < puzzleInput.getListOfLines().size(); y++) {
            for (int x = 0; x < puzzleInput.getListOfLines().get(y).length(); x++) {
                if (Character.isDigit(puzzleInput.getListOfLines().get(y).charAt(x))) {
                    // In case we find ZERO, mark it as initial point
                    if (puzzleInput.getListOfLines().get(y).charAt(x) == '0') {
                        initialPosition = new Point(x, y);
                    }
                    // Add coordinations of discovered vent to list
                    Point newPoint = new Point(x, y);
                    vents.add(newPoint);
                }
            }
        }
        System.out.println("[INFO]\tVents found: " + vents.size());

        /*
        Calculate distances between each pair of points
         */
        Map<PointPair, Integer> distances = new HashMap<>();
        List<Point> reducedVents = new ArrayList<>(vents);
        while (reducedVents.size() > 1) {
            Point startPoint = reducedVents.remove(0);

            for (Point endPoint : reducedVents) {
                int distance = findMinimalPath(puzzleInput.getListOfLines(), startPoint, endPoint);
                distances.put(new PointPair(startPoint, endPoint), distance);
            }
        }
        System.out.println("[INFO]\tDistances found: " + distances.size());

        /*
        Print distances between Points
        Uncomment to debug your puzzle
         */
//        for (PointPair pp : distances.keySet()) {
//            System.out.println("[DIST]\t" + pp.getPoint1().toString() + "->" + pp.getPoint2().toString() + ": " + distances.get(pp));
//        }

        /*
        Find the shortest path, try every possible combination
         */
        List<Point> ventsExceptStart = new ArrayList<>(vents);

        // Remove initial position, we always start there
        ventsExceptStart.remove(initialPosition);

        List<List<Point>> allPossibleCombinations = generatePerm(ventsExceptStart);
        int minimumLength = Integer.MAX_VALUE;

        for (List<Point> currentSequence : allPossibleCombinations) {
            Point startVent = null;
            int currentDistance = 0;

            for (Point nextPoint : currentSequence) {
                if (startVent == null) {
                    startVent = initialPosition;
                }
                try {
                    currentDistance += distances.get(new PointPair(startVent, nextPoint));
                } catch (NullPointerException e) {
                    System.out.println("[ERROR]\t" + startVent + "->" + nextPoint);
                }
                startVent = nextPoint;
            }
            /*
            Puzzle02
            Add additional length to starting point
             */
            if (puzzleNum == 2) {
                currentDistance += distances.get(new PointPair(startVent, initialPosition));
            }
            if (currentDistance < minimumLength) {
                minimumLength = currentDistance;
            }
        }
        System.out.println("[PUZZLE" + puzzleNum + "]\tMinimum distance: " + minimumLength);

    }

    public static void solve() {
        solvePuzzle(1);
        solvePuzzle(2);
    }

}
