package day17;

import java.util.Comparator;

public class QueueNodeComparator implements Comparator<QueueNode> {

    @Override
    public int compare(QueueNode o1, QueueNode o2) {
        if (o1.getPriority() < o2.getPriority()) {
            return -1;
        } else if (o1.getPriority() > o2.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }

}
