package day24;

import java.util.Objects;

public class PointPair {
    Point point1;
    Point point2;

    public PointPair(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
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
        final PointPair other = (PointPair) obj;
        if((!Objects.equals(this.point1, other.point2) || !Objects.equals(this.point2, other.point1)) && (!Objects.equals(this.point1, other.point1) || !Objects.equals(this.point2, other.point2))) {
            return false;
        }
        
        
        return true;
    }
    
    
    
}
