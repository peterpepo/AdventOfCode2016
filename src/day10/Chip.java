package day10;

public class Chip implements Comparable {

    private Integer typeNumber;

    public Chip(Integer typeNumber) {
        this.typeNumber = typeNumber;
    }

    public Integer getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(Integer typeNumber) {
        this.typeNumber = typeNumber;
    }

    @Override
    public int compareTo(Object o) {

        Chip anotherChip = (Chip) o;
        return this.getTypeNumber().compareTo(anotherChip.getTypeNumber());

    }

}
