package day11;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Floor {

    private Set<Item> generators = new HashSet<>();
    private Set<Item> microChips = new HashSet<>();
    
    public Floor() {
        
    }
    
    private Floor(Set<Item> generators, Set<Item> microChips) {
        this.generators = generators;
        this.microChips = microChips;
    }
    
    public Floor getCopy() {
        return new Floor(new HashSet<Item>(generators), new HashSet<Item>(microChips));
    }

    public Set<Item> getAllItems() {
        Set<Item> resultSet = new HashSet<>();
        resultSet.addAll(generators);
        resultSet.addAll(microChips);

        return resultSet;
    }
    
    public void removeItems(Set<Item> setOfItems) {
        for(Item item:setOfItems) {
            removeItem(item);
        }
    }
    
    public void addItems(Set<Item> setOfItems) {
        for(Item item:setOfItems) {
            addItem(item);
        }
    }

    public void removeItem(Item item) {
        if (item.getItemType() == ItemType.MICROCHIP) {
            microChips.remove(item);
        } else if (item.getItemType() == ItemType.GENERATOR) {
            generators.remove(item);
        }
    }

    public void addItem(Item item) {
        if (item.getItemType() == ItemType.MICROCHIP) {
            microChips.add(item);
        } else if (item.getItemType() == ItemType.GENERATOR) {
            generators.add(item);
        }
    }

    public boolean isSafe() {
        // Only if there is a generator
        if (generators.size() > 0) {
            boolean unprotected = true;

            // Check if every microchip is connected to it's generator
            for (Item microChip : microChips) {
                for (Item generator : generators) {
                    if (microChip.getName().equals(generator.getName())) {
                        unprotected = false;
                    }
                }
                if (unprotected) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getSize() {
        return generators.size() + microChips.size();
    }    
    
}
