package day01;

public class CityMap {
    public static int getDistanceAFromB (int x1, int y1, int x2, int y2) {
//        return Math.abs(x1) + Math.abs(y1) + Math.abs(x2) + Math.abs(y2);
return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
    
    public static int getDistanceAFromB(Position p1, Position p2) {
        return getDistanceAFromB(p1.getPositionX(), p1.getPositionY(), p2.getPositionX(), p2.getPositionY());
    }
    
}
