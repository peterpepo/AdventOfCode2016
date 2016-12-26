package day24;

import java.util.Objects;

public class PointPair {

    Point point1;
    Point point2;

    public PointPair(Point point1, Point point2) {
        if (point1.getX() < point2.getX()) {
            this.point1 = point1;
            this.point2 = point2;
        } else if (point1.getX() > point2.getX()) {
            this.point1 = point2;
            this.point2 = point1;
        } else {
            if (point1.getY() < point2.getY()) {
                this.point1 = point1;
                this.point2 = point2;
            } else {
                this.point1 = point2;
                this.point2 = point1;
            }
        }

    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = hash * Objects.hashCode(this.point1)
                * hash + Objects.hashCode(this.point2);
        return hash;
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
        if ((!Objects.equals(this.point1, other.point2) || !Objects.equals(this.point2, other.point1)) && (!Objects.equals(this.point1, other.point1) || !Objects.equals(this.point2, other.point2))) {
            return false;
        }

        return true;
    }

}
