package day11Old;

public class QueueNode {

    private BuildingConfig buildingConfig;
    private int distanceFromStart;

    public QueueNode(BuildingConfig buildingConfig, int distanceFromStart) {
        this.buildingConfig = buildingConfig;
        this.distanceFromStart = distanceFromStart;
    }
    
    public int getDistanceFromStart() {
        return this.distanceFromStart;
    }

    public BuildingConfig getBuildingConfig() {
        return buildingConfig;
    }
    
    

}
