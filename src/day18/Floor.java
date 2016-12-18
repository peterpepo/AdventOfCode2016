package day18;

public class Floor {

    private Boolean[][] floorTiles;

    public Floor(int width, int height) {
        this.floorTiles = new Boolean[height][width];
    }

    public Floor(int width, int height, String firstRow) {
        this.floorTiles = new Boolean[height][width];

        int i = 0;
        for (Character c : firstRow.toCharArray()) {
            if (c == '.') {
                floorTiles[0][i++] = true;
            } else {
                floorTiles[0][i++] = false;
            }
        }
    }

    public Floor(String firstRow) {
        this.floorTiles = new Boolean[firstRow.length()][firstRow.length()];

        int i = 0;
        for (Character c : firstRow.toCharArray()) {
            if (c == '.') {
                floorTiles[0][i++] = true;
            } else {
                floorTiles[0][i++] = false;
            }
        }
    }

    private boolean isSafe(int yPos, int xPos) {
        if (yPos >= floorTiles.length) {
            System.err.println("Out of bounds");
        } else {
            if (xPos < 0 || xPos >= floorTiles[yPos].length) {
                return true;
            } else {
                return floorTiles[yPos][xPos];
            }
        }

        return false;
    }

    public void calculateTiles() {
        for (int row = 0; row < floorTiles.length; row++) {
            for (int col = 0; col < floorTiles[row].length; col++) {
                if (floorTiles[row][col] != null) {
                    continue;
                }

                if (!isSafe(row - 1, col - 1) && !isSafe(row - 1, col) && isSafe(row - 1, col + 1)) {
                    floorTiles[row][col] = false;
                } else if (isSafe(row - 1, col - 1) && !isSafe(row - 1, col) && !isSafe(row - 1, col + 1)) {
                    floorTiles[row][col] = false;
                } else if (!isSafe(row - 1, col - 1) && isSafe(row - 1, col) && isSafe(row - 1, col + 1)) {
                    floorTiles[row][col] = false;
                } else if (isSafe(row - 1, col - 1) && isSafe(row - 1, col) && !isSafe(row - 1, col + 1)) {
                    floorTiles[row][col] = false;
                } else {
                    floorTiles[row][col] = true;
                }
            }
        }
    }

    public Integer countSafeTiles(int rows, int cols) {
        Integer safeTilesCounter = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (floorTiles[row][col]) {
                    safeTilesCounter++;
                }
            }
        }
        return safeTilesCounter;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (Boolean[] row : floorTiles) {
            for (Boolean tile : row) {
                if (tile == null) {
                    sb.append('?');
                } else if (tile) {
                    sb.append('.');
                } else {
                    sb.append('^');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }

}
