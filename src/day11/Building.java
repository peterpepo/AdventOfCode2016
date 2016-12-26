package day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

        sb.append("CF:" + floorNumber + ";");

        for (Integer floorNumber : floors.keySet()) {
            sb.append("F" + floorNumber + ":");
            List<String> generators = new ArrayList<>();
            List<String> microchips = new ArrayList<>();

            for (Item item : floors.get(floorNumber).getAllItems()) {
                if (item.getItemType() == ItemType.GENERATOR) {
                    generators.add(item.getName());
                } else if (item.getItemType() == ItemType.MICROCHIP) {
                    microchips.add(item.getName());
                }
            }
            Collections.sort(microchips);
            Collections.sort(generators);

            for (String microchip : microchips) {
                sb.append(microchip + ",");
            }
            sb.append(":");
            for (String generator : generators) {
                sb.append(generator + ",");
            }
            sb.append(";");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
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
        if (!this.toString().equals(other.toString())) {
            return false;
        }
        return true;
    }
    
    

}
