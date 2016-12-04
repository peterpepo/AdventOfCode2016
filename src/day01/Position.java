package day01;

public class Position {
    private int positionX;
    private int positionY;
    
    public Position() {
        
    }
    
    public Position (int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    
    @Override
    public boolean equals(Object anotherPosition) {

        if(anotherPosition instanceof Position) {
            if(((Position) anotherPosition).getPositionX() == this.getPositionX() && ((Position) anotherPosition).getPositionY() == this.getPositionY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.positionX;
        hash = 97 * hash + this.positionY;
        return hash;
    }
    
    @Override
    public String toString() {
        return("["+this.positionX+":"+this.positionY+"]");
    }
    
    
}
