package day01;

public enum Orientation {
    NORTH, EAST, SOUTH, WEST;

    public static Orientation turnRight90(Orientation originalOrientation) {
        switch (originalOrientation) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return null;
        }
    }

    public static Orientation turnLeft90(Orientation originalOrientation) {
        switch (originalOrientation) {
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            default:
                return null;
        }
    }
}
