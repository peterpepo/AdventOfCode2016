package day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Robot implements ChipTakeable {

    private String name;
    private List<Chip> handsContent;

    public Robot(String name) {
        this.name = name;
        handsContent = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public boolean isFull() {
//        System.out.println(handsContent.size());

        return handsContent.size() >= 2;

    }

    public void takeChip(Chip newChip) {
        
        handsContent.add(newChip);
        if (handsContent.size() >= 2) {
            if ((handsContent.get(0).getTypeNumber().equals(61) && handsContent.get(1).getTypeNumber().equals(17))
                    || (handsContent.get(0).getTypeNumber().equals(17) && handsContent.get(1).getTypeNumber().equals(61))) {
                System.out.println("\tbot " + name+" has chip(61) and chip(17)");
            }
        }
//        System.out.println(name+" takes chip of type "+newChip.getTypeNumber());
    }

    public Chip giveChipLow() {
        Collections.sort(handsContent);

        Chip chipToBeReturned = handsContent.get(0);

        handsContent.remove(chipToBeReturned);

        return chipToBeReturned;
    }

    public Chip giveChipHigh() {
        Collections.sort(handsContent);

        Chip chipToBeReturned = handsContent.get(handsContent.size() - 1);

        handsContent.remove(chipToBeReturned);

        return chipToBeReturned;
    }

    @Override
    public String toString() {
        return ("My name is: " + name);
    }

}
