package day24;

import java.util.List;

public class Maze {

    private boolean[][] map;
    private boolean[][] visited;

    private final char OBSTACLE = '#';
    private final char FREE = '.';

    int currentX;
    int currentY;

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public Maze(List<String> initialMap, int startX, int startY) {
        this(initialMap);
        this.currentX = startX;
        this.currentY = startY;
    }

    public Maze(List<String> initialMap) {
        this.map = new boolean[initialMap.get(0).length()][initialMap.size()];
        this.visited = new boolean[initialMap.get(0).length()][initialMap.size()];

        for (int y = 0; y < initialMap.size(); y++) {
            for (int x = 0; x < initialMap.get(y).length(); x++) {
                if (initialMap.get(y).charAt(x) == OBSTACLE) {
                    this.map[x][y] = false;
                } else {
                    this.map[x][y] = true;
                }

                this.visited[x][y] = false;
            }
        }
    }

    public boolean isOnMap(Integer xPosition, Integer yPosition) {
        if (xPosition >= map.length || yPosition >= map[0].length || xPosition < 0 || yPosition < 0) {
            return false;
        }
        return true;
    }

    public boolean isVisited(Integer xPosition, Integer yPosition) {
        return this.visited[xPosition][yPosition];
    }

    public void visit(Integer xPosition, Integer yPosition) {
        this.visited[xPosition][yPosition] = true;
    }

    public boolean getFree(Integer xPosition, Integer yPosition) {
        return this.map[xPosition][yPosition];
    }

    public void clearVisited() {
        for (int y = 0; y < visited.length; y++) {
            for (int x = 0; x < visited[y].length; x++) {
                this.visited[x][y] = false;
            }
        }
    }

    private boolean isFree(Integer xPosition, Integer yPosition) {
        return map[xPosition][yPosition];
    }

    public void printSpace() {
        for (int y = 0; y < this.map[0].length; y++) {
            for (int x = 0; x < this.map.length; x++) {
                if (map[x][y]) {
                    System.out.print(FREE);
                } else {
                    System.out.print(OBSTACLE);
                }
            }
            System.out.println();
        }
    }

}
