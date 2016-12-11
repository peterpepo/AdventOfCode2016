package day11;

import java.util.ArrayList;
import java.util.HashMap;
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
        
    }

}
