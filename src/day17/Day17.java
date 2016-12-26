package day17;

import java.util.PriorityQueue;

public class Day17 {

    public static void solve() {
        solve1();
        solve2();
    }

    public static void solve1() {
        MazePosition initialMaze = new MazePosition();

        PriorityQueue<QueueNode<MazePosition>> mazeQueue = new PriorityQueue<>(new QueueNodeComparator());

        // Start Node - Point[0,0]
        mazeQueue.add(new QueueNode(initialMaze, 0, 0));

        while (!mazeQueue.isEmpty()) {

            QueueNode<MazePosition> currentNode = mazeQueue.remove();

            // If we find solution in this node, print it and end
            if (currentNode.getContent().isSolution()) {
                System.out.println("First puzzle: " + currentNode.getDistance() + ": " + currentNode.getContent().getPreviousSteps());
                break;
            }

            MazePosition newUp = currentNode.getContent().moveUp();
            if (newUp != null) {
                mazeQueue.add(new QueueNode(newUp, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
            }

            MazePosition newDown = currentNode.getContent().moveDown();
            if (newDown != null) {
                mazeQueue.add(new QueueNode(newDown, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
            }

            MazePosition newLeft = currentNode.getContent().moveLeft();
            if (newLeft != null) {
                mazeQueue.add(new QueueNode(newLeft, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
            }

            MazePosition newRight = currentNode.getContent().moveRight();
            if (newRight != null) {
                mazeQueue.add(new QueueNode(newRight, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
            }

        }
    }

    /*
    Still room for optimisation (e.g check already longer solutions first, then shorter)
    */
    public static void solve2() {
        int maxLength = 0;

        MazePosition initialMaze = new MazePosition();

        PriorityQueue<QueueNode<MazePosition>> mazeQueue = new PriorityQueue<>(new QueueNodeComparator());

        // Start Node - Point[0,0]
        mazeQueue.add(new QueueNode(initialMaze, 0, 0));

        while (!mazeQueue.isEmpty()) {

            QueueNode<MazePosition> currentNode = mazeQueue.remove();

            if (currentNode.getContent().isSolution()) {
                if (currentNode.getDistance() > maxLength) {
                    maxLength = currentNode.getDistance();

                }
                continue;
            }

            MazePosition newUp = currentNode.getContent().moveUp();
            if (newUp != null) {
                mazeQueue.add(new QueueNode(newUp, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
            }

            MazePosition newDown = currentNode.getContent().moveDown();
            if (newDown != null) {
                mazeQueue.add(new QueueNode(newDown, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
            }

            MazePosition newLeft = currentNode.getContent().moveLeft();
            if (newLeft != null) {
                mazeQueue.add(new QueueNode(newLeft, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
            }

            MazePosition newRight = currentNode.getContent().moveRight();
            if (newRight != null) {
                mazeQueue.add(new QueueNode(newRight, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
            }

        }
        System.out.println("Second puzzle: " + maxLength);
    }

}
