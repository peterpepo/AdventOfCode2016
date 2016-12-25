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

        // Start Point
//        Point startPoint = new Point(171, 7);

        // Mark Point as visited
        testMaze.visit(startPoint.getX(), startPoint.getY());

        // Final Point
//        Point endPoint = new Point(165, 19);

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
//                    System.out.println("[NEW]\t" + new Point(newX, newY));
                }
            }

        }
        return -1;
    }

    public static void solve() {
        /*
        Load Map
         */
        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day24/Day24-puzzleInput.txt");

        /*
        Discover all vents in the map
         */
        List<Point> vents = new ArrayList<>();

        for (int y = 0; y < puzzleInput.getListOfLines().size(); y++) {
            for (int x = 0; x < puzzleInput.getListOfLines().get(y).length(); x++) {
                if (Character.isDigit(puzzleInput.getListOfLines().get(y).charAt(x))) {
                    Point newPoint = new Point(x, y);
                    System.out.println(newPoint);
                    vents.add(newPoint);
                }
            }
        }
        System.out.println("[INFO]\tVents found: " + vents.size());
        
        /*
        Calculate distances between each pair of points
        */
        Map<PointPair, Integer> distances = new HashMap<>();
        
        while(vents.size()>1) {
            Point startPoint = vents.remove(0);
            
            for(Point endPoint:vents) {
                int distance = findMinimalPath(puzzleInput.getListOfLines(), startPoint, endPoint);
                distances.put(new PointPair(startPoint, endPoint), distance);
            }
        }
        System.out.println("[INFO]\tDistances found: " + distances.size());
        
        /*
        Print distances between Points
        */
        for(PointPair pp:distances.keySet()) {
            System.out.println("[DISTANCE]\t"+pp.getPoint1().toString()+"\t->\t"+pp.getPoint2().toString()+": "+distances.get(pp));
        }
        

        

    }

}
