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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.itemType);
        hash = 17 * hash + Objects.hashCode(this.name);
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
        final Item other = (Item) obj;
        return true;
    }
   
    
    

}
