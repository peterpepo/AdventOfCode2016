package day20;

import java.util.Comparator;


public class IntervalComparator implements Comparator<Interval>{

    @Override
    public int compare(Interval o1, Interval o2) {
        if(o1.getLowerBound() < o2.getLowerBound()) {
            return -1;
        } else if (o1.getLowerBound() > o2.getLowerBound()) {
            return 1;
        } 
        else {
            if(o1.getUpperBound() < o2.getUpperBound()) {
                return -1;
            } else if (o1.getUpperBound() > o2.getUpperBound()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
