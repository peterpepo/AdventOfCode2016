package day13;

import java.util.LinkedList;
import java.util.List;

public class Day13 {

    /*
    Uses Lee Algorithm
    https://en.wikipedia.org/wiki/Lee_algorithm
    */
    public static void solve() {

        int rowNum[] = {-1, 0, 0, 1};
        int colNum[] = {0, -1, 1, 0};

        // Test algorithm for calculating if position is obstacle or free
        Space testSpace = new Space(1350, 1000, 1000);

        List<QueueNode> queue = new LinkedList<>();

        // Start Point
        Point startPoint = new Point(1, 1);
////////////////////////////////////////////////////////////////////////////////
// PUZZLE 1
////////////////////////////////////////////////////////////////////////////////
        // Mark Point as visited
        testSpace.visit(startPoint.getX(), startPoint.getY());

        // Final Point
        Point finalPoint = new Point(31, 39);

        // Start Node - Point[0,0]
        queue.add(new QueueNode(startPoint, 0));

        while (!queue.isEmpty()) {
            QueueNode currentNode = queue.remove(0);
            Point currentPoint = currentNode.getPoint();

            // If we find solution in this node, print it and end
            if (currentPoint.getX() == finalPoint.getX() && currentPoint.getY() == finalPoint.getY()) {
                System.out.println("Puzzle1: " + currentNode.getDistanceFromStart());
                break;
            }

            // Go every possible way
            for (int i = 0; i < 4; i++) {
                int newY = currentPoint.getY() + rowNum[i];
                int newX = currentPoint.getX() + colNum[i];

                // if the point is on the map and we haven't visited this place before
                if (testSpace.isOnMap(newX, newY) && !testSpace.isVisited(newX, newY) && testSpace.getFree(newX, newY)) {
                    testSpace.visit(newX, newY);
                    queue.add(new QueueNode(new Point(newX, newY), currentNode.getDistanceFromStart() + 1));
                }
            }

        }
////////////////////////////////////////////////////////////////////////////////
// PUZZLE02
////////////////////////////////////////////////////////////////////////////////
        queue.clear();
        testSpace.clearVisited();

        queue.add(new QueueNode(startPoint, 0));
        testSpace.visit(startPoint.getX(), startPoint.getY());
        int visitedPlaces = 1; // Consider startpoint as visited place

        while (!queue.isEmpty()) {
            QueueNode currentNode = queue.remove(0);
            Point currentPoint = currentNode.getPoint();

            // If we find solution in this node, print it and end
            if (currentNode.getDistanceFromStart() >= 50) {
                continue;
            }

            // Go every possible way
            for (int i = 0; i < 4; i++) {
                int newY = currentPoint.getY() + rowNum[i];
                int newX = currentPoint.getX() + colNum[i];

                // if the point is on the map and we haven't visited this place before
                if (testSpace.isOnMap(newX, newY) && !testSpace.isVisited(newX, newY) && testSpace.getFree(newX, newY)) {
                    testSpace.visit(newX, newY);
                    visitedPlaces++;
                    queue.add(new QueueNode(new Point(newX, newY), currentNode.getDistanceFromStart() + 1));
                }
            }

        }
        System.out.println("Puzzle2: "+visitedPlaces);
    }

}
