package day22.shiftPuzzle;

public class Puzzle15 {

    private static int[][] solution = {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 0}};
    private Puzzle15 previousStep;

    private int[][] boardConfig = new int[4][4];
    int emptyX = -1;
    int emptyY = -1;

    public Puzzle15(int[][] newBoardConfig, Puzzle15 previousStep) {
        this(newBoardConfig);
        this.previousStep = previousStep;
    }

    public String traceSteps() {
        StringBuilder sb = new StringBuilder();

        if (previousStep != null) {
            sb.append(previousStep.traceSteps());
        }
        sb.append(this + "\n");

        return sb.toString();
    }

    public Puzzle15(int[][] newBoardConfig) {
        for (int i = 0; i < boardConfig.length; i++) {
            for (int j = 0; j < boardConfig[i].length; j++) {
                this.boardConfig[i][j] = newBoardConfig[i][j];
            }
        }

        for (int x = 0; x < this.boardConfig.length; x++) {
            for (int y = 0; y < this.boardConfig[x].length; y++) {
                if (this.boardConfig[x][y] == 0) {
                    emptyX = x;
                    emptyY = y;
                    break;
                }

            }
            if (emptyX != -1 && emptyY != -1) {
                break;
            }
        }
    }

    public boolean sameAs(Puzzle15 other) {
        for (int x = 0; x < boardConfig.length; x++) {
            for (int y = 0; y < boardConfig[x].length; y++) {
                if (boardConfig[x][y] != other.boardConfig[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSolution() {
        for (int x = 0; x < boardConfig.length; x++) {
            for (int y = 0; y < boardConfig[x].length; y++) {
                if (boardConfig[x][y] != solution[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getDislocatedPiecesCount() {
        int wrongPieces = 0;
        for (int x = 0; x < boardConfig.length; x++) {
            for (int y = 0; y < boardConfig[x].length; y++) {
                if(boardConfig[x][y] == 0) {
                    continue;
                }
                if (boardConfig[x][y] != solution[x][y]) {
                    wrongPieces++;
                }
            }
        }
        return wrongPieces;
    }

    public int getManhattan() {
        int totalDistance = 0;
        for (int x = 0; x < boardConfig.length; x++) {
            for (int y = 0; y < boardConfig[x].length; y++) {
                if (boardConfig[x][y] == 0) {
                    continue;
                }
                for (int xs = 0; xs < solution.length; xs++) {
                    for (int ys = 0; ys < solution[xs].length; ys++) {
                        if (boardConfig[x][y] == solution[xs][ys]) {
                            totalDistance += Math.abs(x - xs) + Math.abs(y - ys);
                        }
                    }
                }
            }
        }
//        System.out.println("Total distance: "+ totalDistance);
        return totalDistance;
    }

    public Puzzle15 moveUp() {
        if (emptyY > 0) {
            Puzzle15 newPuzzle = new Puzzle15(boardConfig, this);

            newPuzzle.boardConfig[emptyX][emptyY] = boardConfig[emptyX][emptyY - 1];
            newPuzzle.boardConfig[emptyX][emptyY - 1] = boardConfig[emptyX][emptyY];
            newPuzzle.emptyY--;
            return newPuzzle;
        }
        return null;
    }

    public Puzzle15 moveDown() {
        if (emptyY < 3) {
            Puzzle15 newPuzzle = new Puzzle15(boardConfig, this);

            newPuzzle.boardConfig[emptyX][emptyY] = boardConfig[emptyX][emptyY + 1];
            newPuzzle.boardConfig[emptyX][emptyY + 1] = boardConfig[emptyX][emptyY];
            newPuzzle.emptyY++;

            return newPuzzle;
        }
        return null;
    }

    public Puzzle15 moveLeft() {
        if (emptyX > 0) {
            Puzzle15 newPuzzle = new Puzzle15(boardConfig, this);

            newPuzzle.boardConfig[emptyX][emptyY] = boardConfig[emptyX - 1][emptyY];
            newPuzzle.boardConfig[emptyX - 1][emptyY] = boardConfig[emptyX][emptyY];
            newPuzzle.emptyX--;
            return newPuzzle;
        }
        return null;
    }

    public Puzzle15 moveRight() {
        if (emptyX < 3) {
            Puzzle15 newPuzzle = new Puzzle15(boardConfig, this);

            newPuzzle.boardConfig[emptyX][emptyY] = boardConfig[emptyX + 1][emptyY];
            newPuzzle.boardConfig[emptyX + 1][emptyY] = boardConfig[emptyX][emptyY];
            newPuzzle.emptyX++;
            return newPuzzle;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < boardConfig.length; y++) {
            for (int x = 0; x < boardConfig[y].length; x++) {
                sb.append(boardConfig[x][y]);
                if (x < 3) {
                    sb.append("\t");
                }

            }
            sb.append("\n");
        }
        sb.append("~~~");
        return sb.toString();
    }

}
