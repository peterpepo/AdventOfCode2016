package day22.shiftPuzzle;

import day22.*;
import java.util.HashMap;
import java.util.Map;

public class PuzzleState {

    private PuzzleState previousStep;

    private Map<Point, Node> boardSolution = new HashMap<>();
    private Map<Point, Node> boardConfig = new HashMap<>();

    private int maxX = 0, maxY = 0;

    public PuzzleState(Map<Point, Node> boardConfig) {
        // Copies source map
//        for (Point p : boardConfig.keySet()) {
//            this.boardConfig.put(p, boardConfig.get(p).getClone());
//        }
        this.boardConfig.putAll(boardConfig);

        for (Point p : this.boardConfig.keySet()) {
            if (p.getX() > maxX) {
                maxX = p.getX();
            }
            if (p.getY() > maxY) {
                maxY = p.getY();
            }
        }
    }

    public PuzzleState(Map<Point, Node> boardConfig, Map<Point, Node> boardSolution) {
        this(boardConfig);
        this.boardSolution = boardSolution;
    }

    public PuzzleState(Map<Point, Node> boardConfig, PuzzleState previousStep) {
        this(boardConfig);
        this.previousStep = previousStep;
        this.boardSolution = previousStep.boardSolution;
    }

    public String traceSteps() {
        StringBuilder sb = new StringBuilder();

        if (previousStep != null) {
            sb.append(previousStep.traceSteps());
        }
        sb.append(this + "\n");

        return sb.toString();
    }

    public boolean sameAs(PuzzleState other) {
        for (Point p : boardConfig.keySet()) {
            if (!other.boardConfig.get(p).equals(this.boardConfig.get(p))) {
                return false;
            }
        }
        return true;
    }

    // TODO: refactor to use sameAs method
    public boolean isSolution() {
        for (Point p : boardConfig.keySet()) {
            if (!boardSolution.get(p).equals(this.boardConfig.get(p))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmptyInUpperRight() {
        return boardConfig.get(new Point(maxX, 0)).isEmpty();
    }

    public boolean isEmptyInUpperLeft() {
        return boardConfig.get(new Point(0, 0)).isEmpty();
    }

    // Hamming distance prioritization
//    public int getDislocatedPiecesCount() {
//        int wrongPieces = 0;
//        for (int x = 0; x < boardConfig.length; x++) {
//            for (int y = 0; y < boardConfig[x].length; y++) {
//                if (boardConfig[x][y] == 0) {
//                    continue;
//                }
//                if (boardConfig[x][y] != solution[x][y]) {
//                    wrongPieces++;
//                }
//            }
//        }
//        return wrongPieces;
//    }
    private Point getEmpty() {
        for (Point p : boardConfig.keySet()) {
            if (boardConfig.get(p).isEmpty()) {
                return p;
            }
        }
        return null;
    }

    // Manhattan distance prioritization
    public int getManhattan() {
        return 0;
//        for (Point p : boardConfig.keySet()) {
//            if(boardConfig.get(p).isEmpty()) {
//                return Math.abs(p.getX() - maxX) + Math.abs(p.getY() - 0);
//            }
//        }
//        return Integer.MAX_VALUE;
    }

    public PuzzleState moveUp() {
        if (getEmpty().getY() > 0
                && boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() - 1)).getUsed() <= boardConfig.get(getEmpty()).getFree()) {
            PuzzleState newPuzzle = new PuzzleState(boardConfig, this);

//            newPuzzle.boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())).copy(boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() - 1)).getUsed());
//            newPuzzle.boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() - 1)).delete();

            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() - 1)));
            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY() - 1), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())));
            return newPuzzle;
        }
        return null;
    }

    public PuzzleState moveDown() {
        if (getEmpty().getY() < maxY
                && boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() + 1)).getUsed() <= boardConfig.get(getEmpty()).getFree()) {
            PuzzleState newPuzzle = new PuzzleState(boardConfig, this);

//            newPuzzle.boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())).copy(boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() + 1)).getUsed());
//            newPuzzle.boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() + 1)).delete();

            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() + 1)));
            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY() + 1), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())));
            return newPuzzle;
        }
        return null;
    }

    public PuzzleState moveLeft() {
        if (getEmpty().getX() > 0
                && boardConfig.get(new Point(getEmpty().getX() - 1, getEmpty().getY())).getUsed() <= boardConfig.get(getEmpty()).getFree()) {
            PuzzleState newPuzzle = new PuzzleState(boardConfig, this);

//            newPuzzle.boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())).copy(boardConfig.get(new Point(getEmpty().getX() - 1, getEmpty().getY())).getUsed());
//            newPuzzle.boardConfig.get(new Point(getEmpty().getX() - 1, getEmpty().getY())).delete();

            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX() - 1, getEmpty().getY())));
            newPuzzle.boardConfig.replace(new Point(getEmpty().getX() - 1, getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())));
            return newPuzzle;
        }
        return null;
    }

    public PuzzleState moveRight() {
        if (getEmpty().getX() < maxX
                && boardConfig.get(new Point(getEmpty().getX() + 1, getEmpty().getY())).getUsed() <= boardConfig.get(getEmpty()).getFree()) {
            PuzzleState newPuzzle = new PuzzleState(boardConfig, this);

//            newPuzzle.boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())).copy(boardConfig.get(new Point(getEmpty().getX() + 1, getEmpty().getY())).getUsed());
//            newPuzzle.boardConfig.get(new Point(getEmpty().getX() + 1, getEmpty().getY())).delete();

            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX() + 1, getEmpty().getY())));
            newPuzzle.boardConfig.replace(new Point(getEmpty().getX() + 1, getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())));
            return newPuzzle;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                sb.append(boardConfig.get(new Point(x, y)).getUsed() + "/" + boardConfig.get(new Point(x, y)).getCapacity());
                if (x < maxX) {
                    sb.append("\t");
                }

            }
            sb.append("\n");
        }
        sb.append("~~~");
        return sb.toString();
    }

}
