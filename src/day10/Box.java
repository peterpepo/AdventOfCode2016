package day10;

import java.util.ArrayList;
import java.util.List;

public class Box implements ChipTakeable{

    private String name;
    private List<Chip> boxContent = new ArrayList<>();

    public Box(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public void takeChip(Chip newChip) {
        boxContent.add(newChip);
    }
    
    public int getValueOfChips() {
        Integer totalValue = 0;
        
        for(Chip c:boxContent) {
            totalValue += c.getTypeNumber();
        }
        
        return totalValue;
    }
}
