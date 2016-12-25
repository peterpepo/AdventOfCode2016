package day22.shiftPuzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import day22.*;
import java.util.HashMap;
import java.util.Map;

public class ShiftPuzzle {

    private static List<Puzzle8> visitedCache = new LinkedList<>();

    private static boolean alreadyVisited(Puzzle8 puzzle) {
        for (Puzzle8 otherPuzzle : visitedCache) {
            if (puzzle.sameAs(otherPuzzle)) {
                return true;
            }
        }
        return false;
    }

    private static List<Puzzle15> visited15Cache = new LinkedList<>();

    private static boolean alreadyVisited15(Puzzle15 puzzle) {
        for (Puzzle15 otherPuzzle : visited15Cache) {
            if (puzzle.sameAs(otherPuzzle)) {
                return true;
            }
        }
        return false;
    }

    private static void solve8() {
        int[][] boardConfig = {{0, 4, 7}, {1, 2, 8}, {3, 5, 6}};
        Puzzle8 initState = new Puzzle8(boardConfig);

        List<QueueNode<Puzzle8>> puzzleQueue = new LinkedList<>();

        puzzleQueue.add(new QueueNode<>(initState, 0));

        while (!puzzleQueue.isEmpty()) {
//            System.out.println("Queue size: "+puzzleQueue.size());
            QueueNode<Puzzle8> currentNode = puzzleQueue.remove(0);
            Puzzle8 currentPuzzle = currentNode.getPuzzle();
            int currentDistance = currentNode.getDistance();

            if (currentPuzzle.isSolution()) {
                System.out.println("===Solution with lenght of " + currentNode.getDistance() + " has been found.===");
                System.out.println(currentPuzzle.traceSteps());
                break;
            }

            visitedCache.add(currentPuzzle);

            Puzzle8 up = currentPuzzle.moveUp();
            if (up != null && !alreadyVisited(up)) {
                puzzleQueue.add(new QueueNode(up, currentDistance + 1));
            }

            Puzzle8 down = currentPuzzle.moveDown();
            if (down != null && !alreadyVisited(down)) {
                puzzleQueue.add(new QueueNode(down, currentDistance + 1));
            }

            Puzzle8 left = currentPuzzle.moveLeft();
            if (left != null && !alreadyVisited(left)) {
                puzzleQueue.add(new QueueNode(left, currentDistance + 1));
            }

            Puzzle8 right = currentPuzzle.moveRight();
            if (right != null && !alreadyVisited(right)) {
                puzzleQueue.add(new QueueNode(right, currentDistance + 1));
            }
        }
    }

    private static void solve15() {
        int[][] boardConfig = {{11, 8, 10, 6}, {14, 4, 3, 7}, {1, 2, 12, 13}, {9, 5, 15, 0}};
//int[][] boardConfig = {{5, 9, 12, 13}, {7, 6, 8, 14}, {1, 2, 0, 10}, {3, 4, 15, 11}};
        Puzzle15 initState = new Puzzle15(boardConfig);

//        List<QueueNode<Puzzle15>> puzzleQueue = new LinkedList<>();
        PriorityQueue<QueueNode<Puzzle15>> puzzleQueue = new PriorityQueue<>(new QueueNodeComparator());

        puzzleQueue.add(new QueueNode(initState, 0, 0));
        double counter = 0;

        while (!puzzleQueue.isEmpty()) {
            if (counter % 1000 == 0) {
                System.out.println("Queue size: " + puzzleQueue.size());
            }
            counter++;
            QueueNode<Puzzle15> currentNode = puzzleQueue.remove();

            Puzzle15 currentPuzzle = currentNode.getPuzzle();
            int currentDistance = currentNode.getDistance();

            if (currentPuzzle.isSolution()) {
                System.out.println("===Solution with lenght of " + currentNode.getDistance() + " has been found.===");
                System.out.println(currentPuzzle.traceSteps());
                break;
            }

            visited15Cache.add(currentPuzzle);

            Puzzle15 up = currentPuzzle.moveUp();
            if (up != null && !alreadyVisited15(up)) {
//                puzzleQueue.add(new QueueNode(up, currentDistance + 1, up.getDislocatedPiecesCount()));
                puzzleQueue.add(new QueueNode(up, currentDistance + 1, up.getManhattan()));
            }

            Puzzle15 down = currentPuzzle.moveDown();
            if (down != null && !alreadyVisited15(down)) {
//                puzzleQueue.add(new QueueNode(down, currentDistance + 1, down.getDislocatedPiecesCount()));
                puzzleQueue.add(new QueueNode(down, currentDistance + 1, down.getManhattan()));
            }

            Puzzle15 left = currentPuzzle.moveLeft();
            if (left != null && !alreadyVisited15(left)) {
//                puzzleQueue.add(new QueueNode(left, currentDistance + 1, left.getDislocatedPiecesCount()));
                puzzleQueue.add(new QueueNode(left, currentDistance + 1, left.getManhattan()));
            }

            Puzzle15 right = currentPuzzle.moveRight();
            if (right != null && !alreadyVisited15(right)) {
//                puzzleQueue.add(new QueueNode(right, currentDistance + 1, right.getDislocatedPiecesCount()));
                puzzleQueue.add(new QueueNode(right, currentDistance + 1, right.getManhattan()));
            }
        }
    }

    private static List<PuzzleState> visitedStateCache = new LinkedList<>();

    private static boolean alreadyVisitedState(PuzzleState state) {
        for (PuzzleState otherPuzzle : visitedStateCache) {
            if (state.sameAs(otherPuzzle)) {
                return true;
            }
        }
        return false;
    }

    private static void solve15v2() {
        int[][] boardConfig = {{11, 8, 10, 6}, {14, 4, 3, 7}, {1, 2, 12, 13}, {9, 5, 15, 0}};
//int[][] boardConfig = {{5, 9, 12, 13}, {7, 6, 8, 14}, {1, 2, 0, 10}, {3, 4, 15, 11}};

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

        System.out.println("EMPTY is empty:" + empty.isEmpty());

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

        PuzzleState initState = new PuzzleState(initMap, targetMap);

        PriorityQueue<QueueNode<PuzzleState>> puzzleQueue = new PriorityQueue<>(new QueueNodeComparator());

        puzzleQueue.add(
                new QueueNode(initState, 0, 0));
        double counter = 0;

        visitedStateCache.add(initState);

        while (!puzzleQueue.isEmpty()) {
            if (counter % 1000 == 0) {
                System.out.println("[INFO]\tQueue size: " + puzzleQueue.size());
                System.out.println("[INFO]\tVisited-cache size: " + visitedStateCache.size());
            }
            counter++;
            QueueNode<PuzzleState> currentNode = puzzleQueue.remove();

            PuzzleState currentPuzzle = currentNode.getPuzzle();
            int currentDistance = currentNode.getDistance();

            if (currentPuzzle.isSolution()) {
//            if (currentPuzzle.isEmptyInUpperRight()) {
                System.out.println("===Solution with lenght of " + currentNode.getDistance() + " has been found.===");
//                System.out.println(currentPuzzle.traceSteps());
                break;
            }

            PuzzleState up = currentPuzzle.moveUp();
            if (up != null && !alreadyVisitedState(up)) {
//                puzzleQueue.add(new QueueNode(up, currentDistance + 1, up.getDislocatedPiecesCount()));
                puzzleQueue.add(new QueueNode(up, currentDistance + 1, currentDistance + up.getManhattan()));
                visitedStateCache.add(up);
            }

            PuzzleState down = currentPuzzle.moveDown();
            if (down != null && !alreadyVisitedState(down)) {
//                puzzleQueue.add(new QueueNode(down, currentDistance + 1, down.getDislocatedPiecesCount()));
                puzzleQueue.add(new QueueNode(down, currentDistance + 1, currentDistance + down.getManhattan()));
                visitedStateCache.add(down);
            }

            PuzzleState left = currentPuzzle.moveLeft();
            if (left != null && !alreadyVisitedState(left)) {
//                puzzleQueue.add(new QueueNode(left, currentDistance + 1, left.getDislocatedPiecesCount()));
                puzzleQueue.add(new QueueNode(left, currentDistance + 1, currentDistance + left.getManhattan()));
                visitedStateCache.add(left);
            }

            PuzzleState right = currentPuzzle.moveRight();
            if (right != null && !alreadyVisitedState(right)) {
//                puzzleQueue.add(new QueueNode(right, currentDistance + 1, right.getDislocatedPiecesCount()));
                puzzleQueue.add(new QueueNode(right, currentDistance + 1, currentDistance + right.getManhattan()));
                visitedStateCache.add(right);
            }
        }
        System.out.println("===Queue size after " + puzzleQueue.size());
    }

    public static void solve() {

//        solve8();
//        solve15();
        solve15v2();
    }

}
