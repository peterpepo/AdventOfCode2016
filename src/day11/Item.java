package day11;

import java.util.Objects;

public class Item {

    private ItemType itemType;
    private String name;

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return (this.getName());
    }
   
    

}
