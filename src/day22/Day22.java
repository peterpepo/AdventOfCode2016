package day22;

import commons.PuzzleInputReader;
import day22.shiftPuzzle.ShiftPuzzle;
import day22.shiftPuzzle.ShiftPuzzleSolver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 {

    public static void solve() {
        // Map of nodes
        Map<Point, Node> nodes = new HashMap<>();

        // Regexp matching
        final String POS_CAP_USED = "x(\\d+)-y(\\d+)\\s+(\\d+)T\\s+(\\d+)";
        Pattern p = Pattern.compile(POS_CAP_USED);
        Matcher m;

        // Puzzle input
        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day22/Day22-puzzleInput.txt");

        // Test input
//        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day22/day22-puzzle2TestInput.txt");
        for (String s : puzzleInput.getListOfLines()) {
            m = p.matcher(s);
            if (m.find()) {
                int xPos = Integer.parseInt(m.group(1));
                int yPos = Integer.parseInt(m.group(2));
                int capacity = Integer.parseInt(m.group(3));
                int used = Integer.parseInt(m.group(4));

                nodes.put(new Point(xPos, yPos), new Node(xPos + ":" + yPos, capacity, used));
            }
        }

        System.out.println("[INFO]\tNodes loaded: " + nodes.size());

        /*
        Debug output of nodes, loaded from file
         */
//        for (Node node : nodes.values()) {
//            System.out.println("[DEBUG]\t" + node);
//        }
        // Puzzle01
        Set<NodePair> nodePairs = new HashSet<>();
        for (Node currentNode : nodes.values()) {
            List<Node> otherNodes = new ArrayList<>(nodes.values());
            otherNodes.remove(currentNode);

            for (Node otherNode : otherNodes) {
                if (currentNode.getUsed() > 0 && currentNode.getUsed() <= otherNode.getFree()) {
                    nodePairs.add(new NodePair(currentNode, otherNode));
                }
            }
        }
        System.out.println("[INFO]\tFirst puzzle: " + nodePairs.size());

        // Puzzle02
//        for (NodePair pair:nodePairs) {
//            for(Map.Entry<Point, Node> es:nodes.entrySet()) {
//                if(es.getValue().equals(pair.node1)) {
//                    System.out.print(es.getKey());
//                    break;
//                }
//            }
//            System.out.print("---");
//            for(Map.Entry<Point, Node> es:nodes.entrySet()) {
//                if(es.getValue().equals(pair.node2)) {
//                    System.out.print(es.getKey());
//                    break;
//                }
//            }
//            System.out.println();
//        }
        for (int y = 0; y < 26; y++) {
            for (int x = 0; x < 36; x++) {
                System.out.print(nodes.get(new Point(x, y)).getNice());
                if (x < 35) {
                    System.out.print("\t");
                }
            }
            System.out.println();

        }

        // Test
//        Node p1 = new Node("Point-1", 1, 1);
//        Node p2 = new Node("Point-2", 2, 2);
//        Node p3 = new Node("Point-3", 3, 3);
//        Node p4 = new Node("Point-4", 4, 4);
//        Node p5 = new Node("Point-5", 5, 5);
//        Node p6 = new Node("Point-6", 6, 6);
//        Node p7 = new Node("Point-7", 7, 7);
//        Node p8 = new Node("Point-8", 8, 8);
//        Node p9 = new Node("Point-9", 9, 9);
//        Node p10 = new Node("Point-10", 10, 10);
//        Node p11 = new Node("Point-11", 11, 11);
//        Node p12 = new Node("Point-12", 12, 12);
//        Node p13 = new Node("Point-13", 13, 13);
//        Node p14 = new Node("Point-14", 14, 14);
//        Node p15 = new Node("Point-15", 15, 15);
//        Node empty = new Node("EMPTY", 16, 0);
//
//        Map<Point, Node> initMap = new HashMap<>();
//        initMap.put(new Point(0, 0), p11);
//        initMap.put(new Point(0, 1), p8);
//        initMap.put(new Point(0, 2), p10);
//        initMap.put(new Point(0, 3), p6);
//        initMap.put(new Point(1, 0), p14);
//        initMap.put(new Point(1, 1), p4);
//        initMap.put(new Point(1, 2), p3);
//        initMap.put(new Point(1, 3), p7);
//        initMap.put(new Point(2, 0), p1);
//        initMap.put(new Point(2, 1), p2);
//        initMap.put(new Point(2, 2), p12);
//        initMap.put(new Point(2, 3), p13);
//        initMap.put(new Point(3, 0), p9);
//        initMap.put(new Point(3, 1), p5);
//        initMap.put(new Point(3, 2), p15);
//        initMap.put(new Point(3, 3), empty);
        Map<Point, Node> initMap = new HashMap<>();

        ShiftPuzzleSolver puzzle2 = new ShiftPuzzleSolver(nodes);

        puzzle2.solveUpperRight();
//        puzzle2.solveUpperLeft();

    }

}
