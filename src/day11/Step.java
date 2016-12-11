package day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Step {

    private Map<Integer, List<String>> floors = new HashMap<Integer, List<String>>();
    private int currentFloor;

    public Step(Map<Integer, List<String>> floors, int newFloor) {
        this.floors = floors;
        this.currentFloor = newFloor;
    }

    public Step(Step base, int newFloor, List<String> addToFloor) {
        // Copy floors
        for(Integer row: base.floors.keySet()) {
            List<String> newRow = new ArrayList<>();
            for(String col:base.floors.get(row)) {
                newRow.add(col);
            }
            floors.put(row, newRow);
        }
        this.currentFloor = newFloor;

        
        this.floors.get(this.getCurrentFloor()).addAll(addToFloor);
//        System.out.println("adding");
        this.floors.get(base.getCurrentFloor()).removeAll(addToFloor);

//        System.out.println(this.getCurrentFloor());
        
//        System.out.println(this);
        // Debug, move away later
        if (this.isSolution()) {
//            System.out.println("solution has been found");
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    private boolean containsUnprotectedChip() {

        // Check all floors
        for (List<String> floor : floors.values()) {
            List<Character> microChips = new ArrayList<>();
            List<Character> generators = new ArrayList<>();
            // Check every position on that floor
            for (String item : floor) {
                // It's a generator
                if (item.charAt(1) == 'G') {
                    generators.add(item.charAt(0));
                } // It's a microchip
                else if (item.charAt(1) == 'M') {
                    microChips.add(item.charAt(0));
                }
            }

            // Check for microchip being irradiated, only if there is any RTG
            if (generators.size() > 0) {
                microChips.removeAll(generators);
                if (microChips.size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValid() {
        return !containsUnprotectedChip();
    }

    public List<String> getCurrentFloorMicroChips() {
        List<String> microChips = new ArrayList<>();

        for (String item : floors.get(this.getCurrentFloor())) {
            if (item.charAt(1) == 'M') {
                microChips.add(item);
            }
        }

        return microChips;
    }

    public List<String> getCurrentFloorGenerators() {
        List<String> generators = new ArrayList<>();

        for (String item : floors.get(this.getCurrentFloor())) {
            if (item.charAt(1) == 'G') {
                generators.add(item);
            }
        }

        return generators;
    }

    public boolean isSolution() {
        // If there is anything on any floor, but 4th, it's not solution yet
        for (int i = 1; i <= 3; i++) {
            if (floors.get(i).size() > 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 4; i >= 1; i--) {
            sb.append("FLOOR " + i + ": " + floors.get(i) + "\n");
        }
        return sb.toString();
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
        final Step other = (Step) obj;
        if (this.currentFloor != other.currentFloor) {
            return false;
        }
//        if (!Objects.equals(this.floors, other.floors)) {
//            return false;
//        }
        for (Integer floor : this.floors.keySet()) {
            for (String item : this.floors.get(floor)) {
                if (!other.floors.get(floor).contains(item)) {
                    return false;
                }
            }
        }
        for (Integer floor : other.floors.keySet()) {
            for (String item : other.floors.get(floor)) {
                if (!this.floors.get(floor).contains(item)) {
                    return false;
                }
            }
        }
        return true;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 83 * hash + Objects.hashCode(this.floors);
//        hash = 83 * hash + this.currentFloor;
//        return hash;
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Step other = (Step) obj;
//        if (this.currentFloor != other.currentFloor) {
//            return false;
//        }
//        if (!Objects.equals(this.floors, other.floors)) {
//            return false;
//        }
//        return true;
//    }
}
