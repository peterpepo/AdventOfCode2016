package day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Building {

    public Building getCopy() {
        Map<Integer, Floor> newFloors = new HashMap<>();
        for (Map.Entry<Integer, Floor> s : floors.entrySet()) {
            newFloors.put(s.getKey(), s.getValue().getCopy());
        }
        return new Building(newFloors, floorNumber);
    }

    private void moveUp() {
        floorNumber++;
    }

    private void moveDown() {
        floorNumber--;
    }

//    public int getAbsoluteDistance() {
//        int totalDistance = 0;
//        
//        for(int i=0; i<2; i++) {
//            totalDistance += (3-i)*floors.get(i).getSize();
//        }
//        
//        return totalDistance;
//    }
//    
    public int getValue() {
        int totalValue = 0;
        for(int i=0; i<4; i++) {
            totalValue = totalValue + (i+1)*3*floors.get(i).getValue();
        }
        totalValue += (1+this.floorNumber);
//        System.out.println(totalValue);
        return totalValue;
    }
    
    private boolean isFloorEmpty(int floorNum) {
        return floors.get(floorNum).getSize() == 0;
    }

    public Building(Map<Integer, Floor> floors, int floorNumber) {
        this.floors = floors;
        this.floorNumber = floorNumber;
    }

    private Map<Integer, Floor> floors = new HashMap<>();
    private int floorNumber;

    private boolean isSafe() {
        for (Floor floor : floors.values()) {
            if (!floor.isSafe()) {
                return false;
            }
        }
        return true;
    }

    public boolean isSolution() {
        for (int i = 0; i < 3; i++) {
            if (floors.get(i).getSize() > 0) {
                return false;
            }
        }
        return true;
    }

    public List<Building> expand() {
        int[] move = {-1, 1};

        List<Building> newBuildings = new LinkedList<>();

        Set<Set<Item>> removalCombinations = new HashSet<>();
        // Either 1
        removalCombinations.addAll(Utils.getCombinationsOfLength(floors.get(floorNumber).getAllItems(), 1));
        // Or 2 items can be taken at time
        removalCombinations.addAll(Utils.getCombinationsOfLength(floors.get(floorNumber).getAllItems(), 2));

        // Try to remove every possible combination of items
        for (Set<Item> takingAway : removalCombinations) {
            // Try to go up and down
            for (int i = 0; i < move.length; i++) {
                int newFloorNumber = this.floorNumber + move[i];

                // If it is a valid floor number
                if (newFloorNumber >= 0 && newFloorNumber <= 3) {
//                    // Don't bring stuff back down
//                    if (floorNumber == 1) {
//                        if (isFloorEmpty(0)) {
//                            if (move[i] == -1) {
//                                continue;
//                            }
//                        }
//                    }
//                    if (floorNumber == 2) {
//                        if (isFloorEmpty(0) && isFloorEmpty(1)) {
//                            if (move[i] == -1) {
//                                continue;
//                            }
//                        }
//                    }

                    Building newBuilding = this.getCopy();
                    newBuilding.floors.get(this.floorNumber).removeItems(takingAway);
                    newBuilding.floors.get(newFloorNumber).addItems(takingAway);
                    if (move[i] == -1) {
                        newBuilding.moveDown();
                    } else {
                        newBuilding.moveUp();
                    }

                    if (newBuilding.isSafe()) {
                        newBuildings.add(newBuilding);
                    }

                }

            }

        }
        return newBuildings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(floorNumber + ";");

        for (Integer floorNumber : floors.keySet()) {
            sb.append(floorNumber + ";");

            sb.append(floors.get(floorNumber).hashCode());
            sb.append(";");
        }
        return sb.toString();
    }
//
//    @Override
//    public int hashCode() {
//        return this.toString().hashCode();
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash += 3 * floors.get(0).hashCode();
        hash += 5 * floors.get(1).hashCode();
        hash += 7 * floors.get(2).hashCode();
        hash += 11 * floors.get(3).hashCode();
        hash*= (floorNumber+1);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Building other = (Building) obj;
        return true;
    }

}
