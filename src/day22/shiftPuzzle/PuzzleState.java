package day22.shiftPuzzle;

import day22.*;
import java.util.HashMap;
import java.util.Map;

public class PuzzleState {

    private PuzzleState previousStep;

    private Map<Point, Node> boardSolution = new HashMap<>();
    private Map<Point, Node> boardConfig = new HashMap<>();

    private int maxX = 0, maxY = 0;

    private PuzzleState(Map<Point, Node> boardConfig) {
        // Copies source map
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
            if (other.boardConfig.get(p) != this.boardConfig.get(p)) {
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
        return boardConfig.get(new Point(maxX-1, 0)).isEmpty();
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
        int totalDistance = 0;
        for (Point p : boardConfig.keySet()) {

            // Don't calculate manhattan for "empty"
            if (p.equals(getEmpty())) {
                continue;
            }

            for (Point ps : boardSolution.keySet()) {
                if (boardConfig.get(p) == boardSolution.get(ps)) {
                    totalDistance += Math.abs(p.getX() - ps.getX()) + Math.abs(p.getY() - ps.getY());
                }
            }
        }
        return totalDistance;
    }

    public PuzzleState moveUp() {
        if (getEmpty().getY() > 0) {
            PuzzleState newPuzzle = new PuzzleState(boardConfig, this);

            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() - 1)));
            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY() - 1), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())));

            return newPuzzle;
        }
        return null;
    }

    public PuzzleState moveDown() {
        if (getEmpty().getY() < maxY) {
            PuzzleState newPuzzle = new PuzzleState(boardConfig, this);

            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY() + 1)));
            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY() + 1), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())));

            return newPuzzle;
        }
        return null;
    }

    public PuzzleState moveLeft() {
        if (getEmpty().getX() > 0) {
            PuzzleState newPuzzle = new PuzzleState(boardConfig, this);

            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX() - 1, getEmpty().getY())));
            newPuzzle.boardConfig.replace(new Point(getEmpty().getX() - 1, getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())));

            return newPuzzle;
        }
        return null;
    }

    public PuzzleState moveRight() {
        if (getEmpty().getX() < maxX) {
            PuzzleState newPuzzle = new PuzzleState(boardConfig, this);

            newPuzzle.boardConfig.replace(new Point(getEmpty().getX(), getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX() + 1, getEmpty().getY())));
            newPuzzle.boardConfig.replace(new Point(getEmpty().getX() + 1, getEmpty().getY()), boardConfig.get(new Point(getEmpty().getX(), getEmpty().getY())));

            return newPuzzle;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                sb.append(boardConfig.get(new Point(x, y)).getName());
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
