package day22.shiftPuzzle;

public class Puzzle8 {

    private static int[][] solution = {{1, 4, 7}, {2, 5, 8}, {3, 6, 0}};
    private Puzzle8 previousStep;

    private int[][] boardConfig = new int[3][3];
    int emptyX = -1;
    int emptyY = -1;

    public Puzzle8(int[][] newBoardConfig, Puzzle8 previousStep) {
        this(newBoardConfig);
        this.previousStep = previousStep;
    }

    public String traceSteps() {
        StringBuilder sb = new StringBuilder();

        if (previousStep != null) {
            sb.append(previousStep.traceSteps());
        }
        sb.append(this+"\n");
        
        return sb.toString();
    }

    public Puzzle8(int[][] newBoardConfig) {
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

    public boolean sameAs(Puzzle8 other) {
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

    public Puzzle8 moveUp() {
        if (emptyY > 0) {
            Puzzle8 newPuzzle = new Puzzle8(boardConfig, this);

            newPuzzle.boardConfig[emptyX][emptyY] = boardConfig[emptyX][emptyY - 1];
            newPuzzle.boardConfig[emptyX][emptyY - 1] = boardConfig[emptyX][emptyY];
            newPuzzle.emptyY--;
            return newPuzzle;
        }
        return null;
    }

    public Puzzle8 moveDown() {
        if (emptyY < 2) {
            Puzzle8 newPuzzle = new Puzzle8(boardConfig, this);

            newPuzzle.boardConfig[emptyX][emptyY] = boardConfig[emptyX][emptyY + 1];
            newPuzzle.boardConfig[emptyX][emptyY + 1] = boardConfig[emptyX][emptyY];
            newPuzzle.emptyY++;

            return newPuzzle;
        }
        return null;
    }

    public Puzzle8 moveLeft() {
        if (emptyX > 0) {
            Puzzle8 newPuzzle = new Puzzle8(boardConfig, this);

            newPuzzle.boardConfig[emptyX][emptyY] = boardConfig[emptyX - 1][emptyY];
            newPuzzle.boardConfig[emptyX - 1][emptyY] = boardConfig[emptyX][emptyY];
            newPuzzle.emptyX--;
            return newPuzzle;
        }
        return null;
    }

    public Puzzle8 moveRight() {
        if (emptyX < 2) {
            Puzzle8 newPuzzle = new Puzzle8(boardConfig, this);

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
                if (x < 2) {
                    sb.append("\t");
                }

            }
            sb.append("\n");
        }
        sb.append("~~~");
        return sb.toString();
    }

}
