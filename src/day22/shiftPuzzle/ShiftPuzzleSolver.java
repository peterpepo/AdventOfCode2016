package day22.shiftPuzzle;

import day22.Node;
import day22.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ShiftPuzzleSolver {

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

    public void solve() {

        Node p1 = new Node("Point-1", 1, 1);
        Node p2 = new Node("Point-2", 2, 2);
        Node p3 = new Node("Point-3", 3, 3);
        Node p4 = new Node("Point-4", 4, 4);
        Node p5 = new Node("Point-5", 5, 5);
        Node p6 = new Node("Point-6", 6, 6);
        Node p7 = new Node("Point-7", 7, 7);
        Node p8 = new Node("Point-8", 8, 8);
        Node p9 = new Node("Point-9", 9, 9);
        Node p10 = new Node("Point-10", 10, 10);
        Node p11 = new Node("Point-11", 11, 11);
        Node p12 = new Node("Point-12", 12, 12);
        Node p13 = new Node("Point-13", 13, 13);
        Node p14 = new Node("Point-14", 14, 14);
        Node p15 = new Node("Point-15", 15, 15);
        Node empty = new Node("EMPTY", 16, 0);

        Map<Point, Node> initMap = new HashMap<>();
        initMap.put(new Point(0, 0), p11);
        initMap.put(new Point(0, 1), p8);
        initMap.put(new Point(0, 2), p10);
        initMap.put(new Point(0, 3), p6);
        initMap.put(new Point(1, 0), p14);
        initMap.put(new Point(1, 1), p4);
        initMap.put(new Point(1, 2), p3);
        initMap.put(new Point(1, 3), p7);
        initMap.put(new Point(2, 0), p1);
        initMap.put(new Point(2, 1), p2);
        initMap.put(new Point(2, 2), p12);
        initMap.put(new Point(2, 3), p13);
        initMap.put(new Point(3, 0), p9);
        initMap.put(new Point(3, 1), p5);
        initMap.put(new Point(3, 2), p15);
        initMap.put(new Point(3, 3), empty);

        Map<Point, Node> targetMap = new HashMap<>();
        targetMap.put(new Point(0, 0), p1);
        targetMap.put(new Point(0, 1), p5);
        targetMap.put(new Point(0, 2), p9);
        targetMap.put(new Point(0, 3), p13);
        targetMap.put(new Point(1, 0), p2);
        targetMap.put(new Point(1, 1), p6);
        targetMap.put(new Point(1, 2), p10);
        targetMap.put(new Point(1, 3), p14);
        targetMap.put(new Point(2, 0), p3);
        targetMap.put(new Point(2, 1), p7);
        targetMap.put(new Point(2, 2), p11);
        targetMap.put(new Point(2, 3), p15);
        targetMap.put(new Point(3, 0), p4);
        targetMap.put(new Point(3, 1), p8);
        targetMap.put(new Point(3, 2), p12);
        targetMap.put(new Point(3, 3), empty);

        int counter = 0;
        while (!puzzleQueue.isEmpty()) {
            if (counter % 1000 == 0) {
                System.out.println("[INFO]\tQueue size: " + puzzleQueue.size());
                System.out.println("[INFO]\tVisited-cache size: " + visitedStateCache.size());
            }
            counter++;
            
            QueueNode<PuzzleState> currentNode = puzzleQueue.remove();

            PuzzleState currentPuzzle = currentNode.getPuzzle();
            int currentDistance = currentNode.getDistance();

            if (currentPuzzle.isEmptyInUpperRight()) {
                System.out.println("===Solution with lenght of " + currentNode.getDistance() + " has been found.===");
//                System.out.println(currentPuzzle.traceSteps());
                break;
            }

            PuzzleState up = currentPuzzle.moveUp();
            if (up != null && !alreadyVisitedState(up)) {
                puzzleQueue.add(new QueueNode(up, currentDistance + 1, currentDistance + up.getManhattan()));
                visitedStateCache.add(up);
            }

            PuzzleState down = currentPuzzle.moveDown();
            if (down != null && !alreadyVisitedState(down)) {
                puzzleQueue.add(new QueueNode(down, currentDistance + 1, currentDistance + down.getManhattan()));
                visitedStateCache.add(down);
            }

            PuzzleState left = currentPuzzle.moveLeft();
            if (left != null && !alreadyVisitedState(left)) {
                puzzleQueue.add(new QueueNode(left, currentDistance + 1, currentDistance + left.getManhattan()));
                visitedStateCache.add(left);
            }

            PuzzleState right = currentPuzzle.moveRight();
            if (right != null && !alreadyVisitedState(right)) {
                puzzleQueue.add(new QueueNode(right, currentDistance + 1, currentDistance + right.getManhattan()));
                visitedStateCache.add(right);
            }
        }
        System.out.println("===Queue size after " + puzzleQueue.size());
    }

}
