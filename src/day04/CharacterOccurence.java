package day04;

public class CharacterOccurence implements Comparable {

    private Character c;
    private int count;

    public CharacterOccurence(Character c, int count) {
        this.c = c;
        this.count = count;
    }

    public Character getChar() {
        return this.c;
    }

    public int getCount() {
        return this.count;
    }

    // Not required by puzzle, debugging purposes
    @Override
    public String toString() {
        return "Character: " + c + ", count: " + count;
    }

    @Override
    public int compareTo(Object o) {

        // First compare by ocurence count
        if (((CharacterOccurence) o).getCount() < this.getCount()) {
            return -1;
        } // If counts are the same, compare by natural ordering of characters
        else if (((CharacterOccurence) o).getCount() == this.getCount()) {
            return this.getChar().compareTo(((CharacterOccurence) o).getChar());
        } else {
            return 1;
        }
    }

}
