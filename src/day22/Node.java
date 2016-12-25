package day22;

public class Node {

    private String name;
    private int capacity;
    private int used;

    public Node(String name, int capacity, int used) {
        this.name = name;
        this.capacity = capacity;
        this.used = used;
    }
    
    public Node getClone() {
        return new Node (name, capacity, used);
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUsed() {
        return used;
    }

    public int getFree() {
        return capacity - used;
    }

    public boolean isEmpty() {
        return getUsed() == 0;
    }

    @Override
    public String toString() {
        return ("name: " + getName() + "\t capacity: " + getCapacity() + "\t used: " + getUsed() + "\t free:" + getFree());
    }
    
    public void delete() {
        this.used = 0;
    }
    
    public void delete(int amount) {
        this.used -= amount;
    }
    
    public void copy(int amount) {
        this.used += amount;
    }
    
    

}
