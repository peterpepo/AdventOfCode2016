package day17;

import java.util.PriorityQueue;

public class Day17 {
    
    public static void solve() {
        MazePosition initialMaze = new MazePosition();

        PriorityQueue<QueueNode<MazePosition>> mazeQueue = new PriorityQueue<>(new QueueNodeComparator());

        // Start Node - Point[0,0]
        mazeQueue.add(new QueueNode(initialMaze, 0, 0));

        while (!mazeQueue.isEmpty()) {
            System.out.println("[INFO]\tQueue size: " + mazeQueue.size());
            QueueNode<MazePosition> currentNode = mazeQueue.remove();

            // If we find solution in this node, print it and end
            if (currentNode.getContent().isSolution()) {
                System.out.println("First puzzle: " + currentNode.getDistance() + ": " + currentNode.getContent().getPreviousSteps());
                break;
//                return currentNode.getDistance();
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

}
