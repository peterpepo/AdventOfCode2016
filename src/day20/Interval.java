package day20;

public class Interval {
    private long lowerBound, upperBound;

    public Interval(long lowerBound, long upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public long getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(long lowerBound) {
        this.lowerBound = lowerBound;
    }

    public long getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(long upperBound) {
        this.upperBound = upperBound;
    }
    
    @Override
    public String toString() {
        return "<"+lowerBound+";"+upperBound+">";
    }
}
