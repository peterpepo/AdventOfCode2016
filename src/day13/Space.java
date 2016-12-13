package day13;

public class Space {

    private Integer magicNumber;

    private boolean[][] map;
    private boolean[][] visited;

    public Space(Integer magicNumber, Integer xMax, Integer yMax) {
        this.magicNumber = magicNumber;
        this.map = new boolean[yMax][xMax];
        this.visited = new boolean[yMax][xMax];

        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                this.map[y][x] = this.isFree(x, y);
                this.visited[y][x] = false;
            }
        }
    }

    public boolean isOnMap(Integer xPosition, Integer yPosition) {
//        System.out.print("is on map ?: ["+xPosition+";"+yPosition+"]");
        if (xPosition >= map[0].length || yPosition >= map.length || xPosition < 0 || yPosition < 0) {
//            System.out.println(false);
            return false;
        }
//        System.out.println(true);
        return true;
    }

    public boolean isVisited(Integer xPosition, Integer yPosition) {
//        System.out.println("is on visited ?: ["+xPosition+";"+yPosition+"]:"+this.visited[yPosition][xPosition]);
        return this.visited[yPosition][xPosition];
    }

    public void visit(Integer xPosition, Integer yPosition) {
        this.visited[yPosition][xPosition] = true;
    }

    public boolean getFree(Integer xPosition, Integer yPosition) {
        return this.map[yPosition][xPosition];
    }

    public void clearVisited() {
        for (int y = 0; y < visited.length; y++) {
            for (int x = 0; x < visited[y].length; x++) {
                this.visited[y][x] = false;
            }
        }
    }

    private boolean isFree(Integer xPosition, Integer yPosition) {
        Integer fieldValue;
        boolean result;

        // x*x + 3*x + 2*x*y + y + y*y
        fieldValue = (xPosition * xPosition) + (3 * xPosition) + (2 * xPosition * yPosition) + (yPosition)
                + (yPosition * yPosition);

        // Add the office designer's favorite number
        fieldValue = fieldValue + this.magicNumber;

        result = (Integer.bitCount(fieldValue) % 2) == 0;

        return result;
    }

    public void printSpace() {
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                if (map[y][x]) {
                    System.out.print('.');
                } else {
                    System.out.print('#');
                }
            }
            System.out.println();
        }
    }

}
