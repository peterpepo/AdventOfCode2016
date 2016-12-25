package day24;

import commons.PuzzleInputReader;
import java.util.PriorityQueue;

public class Day24 {

    public static void solve() {
//        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day24/Day24-testInput.txt");
    PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day24/Day24-puzzleInput.txt");

        Maze testMaze = new Maze(puzzleInput.getListOfLines());
        testMaze.printSpace();

        int rowNum[] = {-1, 0, 0, 1};
        int colNum[] = {0, -1, 1, 0};

        PriorityQueue<QueueNode<Point>> mazeQueue = new PriorityQueue<>(new QueueNodeComparator());

        // Start Point
        Point startPoint = new Point(171, 7);

        // Mark Point as visited
        testMaze.visit(startPoint.getX(), startPoint.getY());

        // Final Point
        Point endPoint = new Point(165, 19);

        // Start Node - Point[0,0]
        mazeQueue.add(new QueueNode(startPoint, 0, 0));

        while (!mazeQueue.isEmpty()) {
            System.out.println("[INFO]\tQueue size: "+mazeQueue.size());
            QueueNode<Point> currentNode = mazeQueue.remove();

            // If we find solution in this node, print it and end
            if (currentNode.getContent().equals(endPoint)) {
                System.out.println("Puzzle1: " + currentNode.getDistance());
                break;
            }

            // Go every possible way
            for (int i = 0; i < 4; i++) {
                int newY = currentNode.getContent().getY() + rowNum[i];
                int newX = currentNode.getContent().getX() + colNum[i];

                // if the point is on the map and we haven't visited this place before
                if (testMaze.isOnMap(newX, newY) && !testMaze.isVisited(newX, newY) && testMaze.getFree(newX, newY)) {
                    testMaze.visit(newX, newY);
                    mazeQueue.add(new QueueNode(new Point(newX, newY), currentNode.getDistance() + 1, currentNode.getDistance() + 1));
                    System.out.println("[NEW]\t"+new Point(newX, newY));
                }
            }

        }

    }

}
