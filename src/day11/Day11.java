package day11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Day11 {

    public static void solve() {

        List<QueueNode> queue = new LinkedList<>();
// TEST
//        List<String> floor1 = new ArrayList<>();
//        floor1.add("HM");
//        floor1.add("LM");
//
//        List<String> floor2 = new ArrayList<>();
//        floor2.add("HG");
//
//        List<String> floor3 = new ArrayList<>();
//        floor3.add("LG");
//
//        List<String> floor4 = new ArrayList<>();

        // Start Point
        List<String> floor1 = new ArrayList<>();
        floor1.add("TG");
        floor1.add("TM");
        floor1.add("PG");
        floor1.add("SG");

        List<String> floor2 = new ArrayList<>();
        floor2.add("PM");
        floor2.add("SM");

        List<String> floor3 = new ArrayList<>();
        floor3.add("AG");
        floor3.add("AM");
        floor3.add("RG");
        floor3.add("RM");

        List<String> floor4 = new ArrayList<>();
        
        
        Map<Integer, List<String>> initialMap = new HashMap<Integer, List<String>>();
        initialMap.put(1, floor1);
        initialMap.put(2, floor2);
        initialMap.put(3, floor3);
        initialMap.put(4, floor4);
        BuildingConfig initialBuildingConfig = new BuildingConfig(initialMap, 1);

        queue.add(new QueueNode(initialBuildingConfig, 0));

        // Don't process those, which we have seen
        List<Map<Integer, List<String>>> seen = new ArrayList<Map<Integer, List<String>>>();

        while (!queue.isEmpty()) {
            QueueNode currentNode = queue.remove(0);
            BuildingConfig buildingConfig = currentNode.getBuildingConfig();

            // If we find solution in this node, print it and end
            if (buildingConfig.isSolution()) {
                System.out.println("Puzzle1: " + currentNode.getDistanceFromStart());
                break;
            }

            seen.add(buildingConfig.getCopyOfFloors());

            // Go every possible way
            for (BuildingConfig newBuildingConfig : buildingConfig.expand()) {
//                boolean addIt = false;
//                for (Map<Integer, List<String>> BCContent : seen) {
//                    for (Integer floorNumber : BCContent.keySet()) {
//                        for (String item : BCContent.get(floorNumber)) {
//                            if (!newBuildingConfig.getCopyOfFloors().get(floorNumber).contains(item)) {
//                                addIt = true;
//                                break;
//                            }
//                        }
//                        for (String item : newBuildingConfig.getCopyOfFloors().get(floorNumber)) {
//                            if (!BCContent.get(floorNumber).contains(item)) {
//                                addIt = true;
//                                break;
//                            }
//                        }
//                    }
//                }
//                if (addIt) {
//                    queue.add(new QueueNode(newBuildingConfig, currentNode.getDistanceFromStart() + 1));
////                    System.out.println("add");
//                } else {
//                    System.out.println("skip");
//                }
queue.add(new QueueNode(newBuildingConfig, currentNode.getDistanceFromStart() + 1));
            }

        }

    }
}
