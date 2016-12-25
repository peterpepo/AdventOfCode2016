package day22.shiftPuzzle;

import day22.Node;
import day22.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ShiftPuzzleSolver {

    private PuzzleState firstPartSolution;

    PriorityQueue<QueueNode<PuzzleState>> puzzleQueue = new PriorityQueue<>(new QueueNodeComparator());

    private static List<PuzzleState> visitedStateCache = new LinkedList<>();

    private static boolean alreadyVisitedState(PuzzleState state) {
        for (PuzzleState otherPuzzle : visitedStateCache) {
            if (state.sameAs(otherPuzzle)) {
                return true;
            }
        }
        return false;
    }

    public ShiftPuzzleSolver(Map<Point, Node> initMap) {
        PuzzleState initState = new PuzzleState(initMap);
        puzzleQueue.add(new QueueNode(initState, 0, 0));
        visitedStateCache.add(initState);
    }

    public void solveUpperRight() {

        int counter = 0;
        while (!puzzleQueue.isEmpty()) {

            QueueNode<PuzzleState> currentNode = puzzleQueue.remove();
            if (counter % 10 == 0) {
                System.out.println("[INFO]\tQueue size: " + puzzleQueue.size());
                System.out.println("[INFO]\tVisited-cache size: " + visitedStateCache.size());
                System.out.println("[INFO]\tCurrent-distance: " + currentNode.getDistance());
            }
            counter++;

            PuzzleState currentPuzzle = currentNode.getPuzzle();
            int currentDistance = currentNode.getDistance();

//            if(currentDistance >=26) {
//                System.out.println("Distance >= 26");
//            }
System.out.println("[INFO]\tCurrent-distance: " + currentNode.getDistance());
            if (currentPuzzle.isEmptyInUpperRight()) {
//            if (currentPuzzle.isDataUpperLeft()) {
                System.out.println("===Solution with lenght of " + currentNode.getDistance() + " has been found.===");
                firstPartSolution = currentPuzzle;
//                System.out.println(currentPuzzle.traceSteps());
                break;
            }

            PuzzleState up = currentPuzzle.moveUp();
            if (up != null && !alreadyVisitedState(up)) {
                puzzleQueue.add(new QueueNode(up, currentDistance + 1, currentDistance + 1 + up.getManhattan()));
                visitedStateCache.add(up);
            }

            PuzzleState down = currentPuzzle.moveDown();
            if (down != null && !alreadyVisitedState(down)) {
                puzzleQueue.add(new QueueNode(down, currentDistance + 1, currentDistance + 1 + down.getManhattan()));
                visitedStateCache.add(down);
            }

            PuzzleState left = currentPuzzle.moveLeft();
            if (left != null && !alreadyVisitedState(left)) {
                puzzleQueue.add(new QueueNode(left, currentDistance + 1, currentDistance + 1 + left.getManhattan()));
                visitedStateCache.add(left);
            }

            PuzzleState right = currentPuzzle.moveRight();
            if (right != null && !alreadyVisitedState(right)) {
                puzzleQueue.add(new QueueNode(right, currentDistance + 1, currentDistance + 1 + right.getManhattan()));
                visitedStateCache.add(right);
            }
        }
//        System.out.println("===Queue size after " + puzzleQueue.size());
    }

    public void solveUpperLeft() {
        puzzleQueue.clear();
        visitedStateCache.clear();

        puzzleQueue.add(new QueueNode(firstPartSolution, 0, 0));
        visitedStateCache.add(firstPartSolution);
        int counter = 0;
        while (!puzzleQueue.isEmpty()) {
            if (counter % 10 == 0) {
                System.out.println("[INFO]\tQueue size: " + puzzleQueue.size());
                System.out.println("[INFO]\tVisited-cache size: " + visitedStateCache.size());
            }
            counter++;

            QueueNode<PuzzleState> currentNode = puzzleQueue.remove();

            PuzzleState currentPuzzle = currentNode.getPuzzle();
            int currentDistance = currentNode.getDistance();

            if (currentPuzzle.isEmptyInUpperLeft()) {
                System.out.println("===Solution with lenght of " + currentNode.getDistance() + " has been found.===");
                firstPartSolution = currentPuzzle;
                System.out.println(currentPuzzle.traceSteps());
                break;
            }

            PuzzleState up = currentPuzzle.moveUp();
            if (up != null && !alreadyVisitedState(up)) {
                puzzleQueue.add(new QueueNode(up, currentDistance + 1, currentDistance + up.getManhattan()+1));
                visitedStateCache.add(up);
            }

            PuzzleState down = currentPuzzle.moveDown();
            if (down != null && !alreadyVisitedState(down)) {
                puzzleQueue.add(new QueueNode(down, currentDistance + 1, currentDistance + down.getManhattan()+1));
                visitedStateCache.add(down);
            }

            PuzzleState left = currentPuzzle.moveLeft();
            if (left != null && !alreadyVisitedState(left)) {
                puzzleQueue.add(new QueueNode(left, currentDistance + 1, currentDistance + left.getManhattan()+1));
                visitedStateCache.add(left);
            }

            PuzzleState right = currentPuzzle.moveRight();
            if (right != null && !alreadyVisitedState(right)) {
                puzzleQueue.add(new QueueNode(right, currentDistance + 1, currentDistance + right.getManhattan()+1));
                visitedStateCache.add(right);
            }
        }
        System.out.println("===Queue size after " + puzzleQueue.size());

    }
}
