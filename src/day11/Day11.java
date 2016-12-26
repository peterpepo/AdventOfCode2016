package day11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Day11 {

    public static void solve() {
        /*
        TESTING
         */
        Item thydrogenGenenerator = new Item("Hydrogen", ItemType.GENERATOR);
        Item thydrogenMicrochip = new Item("Hydrogen", ItemType.MICROCHIP);
        Item tthaliumMicrochip = new Item("Thalium", ItemType.MICROCHIP);
        Item tthaliumGenerator = new Item("Thalium", ItemType.GENERATOR);

        Floor tf1 = new Floor();
        tf1.addItem(thydrogenMicrochip);
        tf1.addItem(thydrogenGenenerator);
        tf1.addItem(tthaliumMicrochip);
        tf1.addItem(tthaliumGenerator);

//        System.out.println("Is safe?: " + tf1.isSafe());

        Set<Item> initSet = new HashSet<>();
        initSet.add(tthaliumMicrochip);
        initSet.add(thydrogenGenenerator);
        initSet.add(thydrogenMicrochip);
        initSet.add(tthaliumGenerator);

//        for (Set<Item> row : Utils.getCombinationsOfLength(initSet, 2)) {
//            System.out.println(row);
//        }

//        String s1 = "abc";
//        System.out.println(s1.hashCode());
//        String s2 = "abc";
//        s2 = "cde";
//        s2 = "abc";
//        System.out.println(s2.hashCode());

        // Test puzzle
        Item hm = new Item("Hydrogen", ItemType.MICROCHIP);
        Item hg = new Item("Hydrogen", ItemType.GENERATOR);
        Item lm = new Item("Lithium", ItemType.MICROCHIP);
        Item lg = new Item("Lithium", ItemType.GENERATOR);

        Map<Integer, Floor> initialFloorMap = new HashMap<>();
        Floor f1 = new Floor();
        f1.addItem(hm);
        f1.addItem(lm);
        Floor f2 = new Floor();
        f2.addItem(hg);
        Floor f3 = new Floor();
        f3.addItem(lg);
        Floor f4 = new Floor();
        
        initialFloorMap.put(0, f1);
        initialFloorMap.put(1, f2);
        initialFloorMap.put(2, f3);
        initialFloorMap.put(3, f4);

        Set<Building> visitedStates = new HashSet<>();
        Building initialBuilding = new Building(initialFloorMap, 0);

        PriorityQueue<QueueNode<Building>> queue = new PriorityQueue<>(new QueueNodeComparator());

        // Mark Point as visited
        visitedStates.add(initialBuilding);

        queue.add(new QueueNode(initialBuilding, 0, 0));
        
        while (!queue.isEmpty()) {
            System.out.println("[INFO]\tQueue size: " + queue.size());
            QueueNode<Building> currentNode = queue.remove();

            // If we find solution in this node, print it and end
            if (currentNode.getContent().isSolution()) {
                System.out.println("Puzzle1: " + currentNode.getDistance());
                break;
            }

            for (Building newBuilding : currentNode.getContent().expand()) {
                if (!visitedStates.contains(newBuilding)) {
                    queue.add(new QueueNode(newBuilding, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
                    visitedStates.add(newBuilding);
                }
            }

        }

        
        /*
        TEST2
        */
        
        Floor testFloor0 = new Floor();
        testFloor0.addItem(lm);
        System.out.println("Is safe: "+testFloor0.isSafe());
        
        Floor testFloor1 = new Floor();
        testFloor1.addItem(hg);
        testFloor1.addItem(hm);
        System.out.println("Is safe: "+testFloor1.isSafe());
    }

}
