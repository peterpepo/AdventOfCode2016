package day17;


public class QueueNode<T> {

    private T puzzle;
    private int distance;
    private int priority;

    public QueueNode(T puzzle, int distance) {
        this.puzzle = puzzle;
        this.distance = distance;
    }

    public QueueNode(T puzzle, int distance, int priority) {
        this(puzzle, distance);
        this.priority = priority;
    }

    public T getContent() {
        return puzzle;
    }

    public int getDistance() {
        return distance;
    }

    public int getPriority() {
        return priority;
    }

}
