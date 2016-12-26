package day11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Day11 {

    public static void solve() {
        /*
        REAL DATA
         */
        Item tg = new Item("Thulium", ItemType.GENERATOR);
        Item tm = new Item("Thulium", ItemType.MICROCHIP);

        Item pg = new Item("Plutonium", ItemType.GENERATOR);
        Item pm = new Item("Plutonium", ItemType.MICROCHIP);

        Item sg = new Item("Strontium", ItemType.GENERATOR);
        Item sm = new Item("Strontium", ItemType.MICROCHIP);

        Item prg = new Item("Promethium", ItemType.GENERATOR);
        Item prm = new Item("Promethium", ItemType.MICROCHIP);

        Item rg = new Item("Ruthenium", ItemType.GENERATOR);
        Item rm = new Item("Ruthenium", ItemType.MICROCHIP);

        Item eg = new Item("Elerium", ItemType.GENERATOR);
        Item em = new Item("Elerium", ItemType.MICROCHIP);

        Item dg = new Item("Dilithium", ItemType.GENERATOR);
        Item dm = new Item("Dilithium", ItemType.MICROCHIP);

        Map<Integer, Floor> initialFloorMap = new HashMap<>();
        Floor f1 = new Floor();
        f1.addItem(tg);
        f1.addItem(tm);
        f1.addItem(pg);
        f1.addItem(sg);
        f1.addItem(eg);
        f1.addItem(em);
        f1.addItem(dg);
        f1.addItem(dm);

        Floor f2 = new Floor();
        f2.addItem(pm);
        f2.addItem(sm);

        Floor f3 = new Floor();
        f3.addItem(prg);
        f3.addItem(prm);
        f3.addItem(rg);
        f3.addItem(rm);

        Floor f4 = new Floor();


        initialFloorMap.put(0, f1);
        initialFloorMap.put(1, f2);
        initialFloorMap.put(2, f3);
        initialFloorMap.put(3, f4);

        Set<Building> visitedStates = new HashSet<>();
        Set<Integer> seenValues = new HashSet<>();
        
        Building initialBuilding = new Building(initialFloorMap, 0);

//        PriorityQueue<QueueNode<Building>> queue = new PriorityQueue<>(new QueueNodeComparator());
        List<QueueNode<Building>> queue = new LinkedList<>();

        // Mark Point as visited
        visitedStates.add(initialBuilding);
        seenValues.add(initialBuilding.getValue());

        queue.add(new QueueNode(initialBuilding, 0, 0));

        while (!queue.isEmpty()) {
//            System.out.println("[INFO]\tQueue size: " + queue.size());
//            QueueNode<Building> currentNode = queue.remove();
            QueueNode<Building> currentNode = queue.remove(0);

            // If we find solution in this node, print it and end
            if (currentNode.getContent().isSolution()) {
                System.out.println("Puzzle1: " + currentNode.getDistance());
                break;
            }

            for (Building newBuilding : currentNode.getContent().expand()) {
//                if (!visitedStates.contains(newBuilding)) {
                if (!visitedStates.contains(newBuilding) && !seenValues.contains(newBuilding.getValue())) {
                    queue.add(new QueueNode(newBuilding, currentNode.getDistance() + 1, currentNode.getDistance() + 1));
                    visitedStates.add(newBuilding);
                    seenValues.add(newBuilding.getValue());
                }
            }

        }

    }

}
