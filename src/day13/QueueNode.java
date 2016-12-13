package day13;

public class QueueNode {

    private Point point;
    private int distanceFromStart;

    public QueueNode(Point point, int distanceFromStart) {
        this.point = point;
        this.distanceFromStart = distanceFromStart;
    }
    
    public int getDistanceFromStart() {
        return this.distanceFromStart;
    }

    public Point getPoint() {
        return point;
    }
    
    

}
