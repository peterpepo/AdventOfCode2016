package day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BuildingConfig {

    private BuildingConfig parent;
    private Map<Integer, List<String>> floors = new HashMap<Integer, List<String>>();
    private int currentFloor;

    private boolean haveBeenThere(Map<Integer, List<String>> anotherConfig) {
        for(BuildingConfig bc:getAlreadySeen()) {
            for(Integer floorNumber:bc.floors.keySet()) {
                for(String item:bc.floors.get(floorNumber)) {
                    if(!anotherConfig.containsValue(item)) {
                        return false;
                    }
                }
            }
            for(Integer floorNumber:anotherConfig.keySet()) {
                for(String item:anotherConfig.get(floorNumber)) {
                    if(!bc.floors.containsValue(item)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private List<BuildingConfig> getAlreadySeen() {
        ArrayList<BuildingConfig> seen = new ArrayList<>();
        if (parent != null) {
            seen.addAll(parent.getAlreadySeen());
        }
        seen.add(this);

        return seen;
    }

    
    public Map<Integer, List<String>> getCopyOfFloors() {
        return this.copyFloors();
    }
    
    private Map<Integer, List<String>> copyFloors() {
        Map<Integer, List<String>> newFloors = new HashMap<Integer, List<String>>();

        for (Integer floorNumber : floors.keySet()) {
            List<String> floorContent = new ArrayList<>();

            for (String item : floors.get(floorNumber)) {
                floorContent.add(item);
            }

            newFloors.put(floorNumber, floorContent);
        }

        return newFloors;
    }

    private Map<Integer, List<String>> replaceFloorContent(Map<Integer, List<String>> floorConfig,
            List<String> content,
            Integer oldFloorNumber,
            Integer newFloorNumber) {
        floorConfig.get(oldFloorNumber).removeAll(content);
        floorConfig.get(newFloorNumber).addAll(content);

        return floorConfig;
    }

    public List<BuildingConfig> expand() {
        // new steps
        List<BuildingConfig> newBuildingConfigs = new ArrayList<>();

        // Populates list of things, which can be carried to other floors
        List<String> canBeTakenAway = new ArrayList<>();
        canBeTakenAway.addAll(this.getCurrentFloorGenerators());
        canBeTakenAway.addAll(this.getCurrentFloorMicroChips());

        List<List<String>> takeAwayCombinations = new ArrayList<List<String>>();

        // Create every possible combination of 2 what can be taken away
        for (int i = 1; i <= 2; i++) {
            takeAwayCombinations.addAll(CombinatoricUtils.getPermutationsList(canBeTakenAway, i));
//            System.out.println("new possibilities: "+CombinatoricUtils.getPermutationsList(canBeTakenAway, i).size());
        }

        for (int i = 0; i < takeAwayCombinations.size(); i++) {
            // Go up a floor, if we are below 4th -> move every possible combination up there
            if (this.currentFloor < 4) {
                Map<Integer, List<String>> newFloorItems = this.copyFloors();
                newFloorItems = this.replaceFloorContent(newFloorItems, takeAwayCombinations.get(i), currentFloor, currentFloor + 1);

                BuildingConfig newBuildingConfig = new BuildingConfig(newFloorItems, currentFloor + 1, this);
                if (newBuildingConfig.isValid()) {
                    newBuildingConfigs.add(newBuildingConfig);
                }
//                System.out.println(newBuildingConfig);
//                System.out.println(newBuildingConfig.isValid());
            }

            // Go down a floor, if we are above 1st -> move every possible combination down there
            if (this.currentFloor > 1) {
                Map<Integer, List<String>> newFloorItems = this.copyFloors();
                newFloorItems = this.replaceFloorContent(newFloorItems, takeAwayCombinations.get(i), currentFloor, currentFloor - 1);

                BuildingConfig newBuildingConfig = new BuildingConfig(newFloorItems, currentFloor - 1, this);
                if (newBuildingConfig.isValid()) {
                    newBuildingConfigs.add(newBuildingConfig);
                }
//                System.out.println(newBuildingConfig);
//                System.out.println(newBuildingConfig.isValid());
            }
        }
        return newBuildingConfigs;
    }

    private List<String> getCurrentFloorMicroChips() {
        List<String> microChips = new ArrayList<>();

        for (String item : floors.get(currentFloor)) {
            if (item.charAt(1) == 'M') {
                microChips.add(item);
            }
        }

        return microChips;
    }

    private List<String> getCurrentFloorGenerators() {
        List<String> generators = new ArrayList<>();

        for (String item : floors.get(currentFloor)) {
            if (item.charAt(1) == 'G') {
                generators.add(item);
            }
        }

        return generators;
    }

    public BuildingConfig(Map<Integer, List<String>> floors, int newFloor) {
        this.floors = floors;
        this.currentFloor = newFloor;
    }

    public BuildingConfig(Map<Integer, List<String>> floors, int newFloor, BuildingConfig parent) {
        this.floors = floors;
        this.currentFloor = newFloor;
        this.parent = parent;
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

        for (Integer floor : floors.keySet()) {
            sb.append(floor + ":");
            for (String item : floors.get(floor)) {
                sb.append(item + ";");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
