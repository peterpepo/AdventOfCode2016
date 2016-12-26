package day11;

public class QueueNode<T> {

    private T building;
    private int distance;
    private int priority;

    public QueueNode(T building, int distance) {
        this.building = building;
        this.distance = distance;
    }

    public QueueNode(T building, int distance, int priority) {
        this(building, distance);
        this.priority = priority;
    }

    public T getContent() {
        return building;
    }

    public int getDistance() {
        return distance;
    }

    public int getPriority() {
        return priority;
    }

}
