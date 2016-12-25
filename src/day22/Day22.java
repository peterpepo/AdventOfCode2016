package day22;

import commons.PuzzleInputReader;
import day22.shiftPuzzle.ShiftPuzzle;
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
//        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day22/Day22-puzzleInput.txt");

        // Test input
        PuzzleInputReader puzzleInput = new PuzzleInputReader("src/day22/day22-puzzle2TestInput.txt");
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
        for (NodePair pair:nodePairs) {
            for(Map.Entry<Point, Node> es:nodes.entrySet()) {
                if(es.getValue().equals(pair.node1)) {
                    System.out.print(es.getKey());
                    break;
                }
            }
            System.out.print("---");
            for(Map.Entry<Point, Node> es:nodes.entrySet()) {
                if(es.getValue().equals(pair.node2)) {
                    System.out.print(es.getKey());
                    break;
                }
            }
            System.out.println();
        }
        
        // Test
        ShiftPuzzle.solve();
        
        
    }

}
