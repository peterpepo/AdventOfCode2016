package day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day11 {

    public static void solve() {

        /*
        F4 .  .  .  .  .  
        F3 .  .  .  LG .  
        F2 .  HG .  .  .  
        F1 E  .  HM .  LM
         */
        // Sample configuration
        List<String> floor1 = new ArrayList<>();
        floor1.add("HM");
        floor1.add("LM");

        List<String> floor2 = new ArrayList<>();
        floor2.add("HG");

        List<String> floor3 = new ArrayList<>();
        floor3.add("LG");

        List<String> floor4 = new ArrayList<>();

        Map<Integer, List<String>> initialMap = new HashMap<Integer, List<String>>();
        initialMap.put(1, floor1);
        initialMap.put(2, floor2);
        initialMap.put(3, floor3);
        initialMap.put(4, floor4);

        Step initialStep = new Step(initialMap, 1);
        System.out.println("----");
        System.out.println(initialStep);
        System.out.println("----");

        Route testRoute = new Route(initialStep);
//    List<String> testPerm = new ArrayList();
//    testPerm.add("HM");
//    testPerm.add("LM");
//    for(List<String> ls: CombinatoricUtils.getPermutationsList(testPerm,1)) {
//        for(String s:ls) {
//            System.out.print(s+"+");
//        }
//        System.out.println();
//    }
        List<String> floorPuzzle1 = new ArrayList<>();
        floorPuzzle1.add("TG");
        floorPuzzle1.add("TM");
        floorPuzzle1.add("PG");
        floorPuzzle1.add("SG");

        List<String> floorPuzzle2 = new ArrayList<>();
        floorPuzzle2.add("PM");
        floorPuzzle2.add("SM");

        List<String> floorPuzzle3 = new ArrayList<>();
        floorPuzzle3.add("AG");
        floorPuzzle3.add("AM");
        floorPuzzle3.add("RG");
        floorPuzzle3.add("RM");

        List<String> floorPuzzle4 = new ArrayList<>();

        Map<Integer, List<String>> puzzleMap = new HashMap<Integer, List<String>>();
        puzzleMap.put(1, floorPuzzle1);
        puzzleMap.put(2, floorPuzzle2);
        puzzleMap.put(3, floorPuzzle3);
        puzzleMap.put(4, floorPuzzle4);

        Step initialPuzzleStep = new Step(puzzleMap, 1);
        System.out.println(initialPuzzleStep);
        System.out.println("~~~");
        Route puzzleRoute = new Route(initialPuzzleStep);

        // QUEUEING
//        Integer minimalLength = Integer.MAX_VALUE;
        Integer minimalLength = 50;
        List<Route> queue = new LinkedList<>();

        // Start route
        queue.add(puzzleRoute);
//    queue.add(testRoute);

        // Process queue
        while (!queue.isEmpty()) {
//            System.out.println("queue size: "+queue.size());
            Route currentRoute = queue.remove(0);

            if (!currentRoute.isSolution()) {
                if (currentRoute.getLength()+1 < minimalLength) {
                    queue.addAll(currentRoute.expand());
                }
            } else {
                System.out.println("Some solution has been found: "+currentRoute.getLength());
                minimalLength = currentRoute.getLength();
            }
        }
    }

}
