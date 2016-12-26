package day17;

public class MazePosition {

    private static final String SALT = "vwbaicqe";
    private MazePosition previousStep;
    private final String previousStepsHash;
    
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPreviousSteps() {
        StringBuilder sb = new StringBuilder();

        if (previousStep != null) {
            sb.append(previousStep.getPreviousSteps());
            if (getX() > previousStep.getX()) {
                sb.append("R");
            } else if (getX() < previousStep.getX()) {
                sb.append("L");
            } else if (getY() > previousStep.getY()) {
                sb.append("D");
            } else {
                sb.append("U");
            }
        }

        return sb.toString();
    }

    public boolean isSolution() {
        return getX() == 3 && getY() == 3;
    }

    public MazePosition() {
        this.x = 0;
        this.y = 0;
        previousStepsHash = Md5HashGenerator.getHashString(SALT + getPreviousSteps());
    }

    public MazePosition(int x, int y, MazePosition previousStep) {
        this.x = x;
        this.y = y;
        this.previousStep = previousStep;
        previousStepsHash = Md5HashGenerator.getHashString(SALT + getPreviousSteps());
    }

    private boolean isUnlocked(char c) {
        if (c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f') {
            return true;
        } else {
            return false;
        }
    }

    public MazePosition moveLeft() {
        MazePosition newMazePosition = null;

        // We are not on the edge of map
        if (x > 0) {
            if (isUnlocked(previousStepsHash.charAt(2))) {
                newMazePosition = new MazePosition(getX() - 1, getY(), this);
            }
        }
        return newMazePosition;
    }

    public MazePosition moveRight() {
        MazePosition newMazePosition = null;

        
        // We are not on the edge of map
        if (x < 3) {
            if (isUnlocked(previousStepsHash.charAt(3))) {
                newMazePosition = new MazePosition(getX() + 1, getY(), this);
            }
        }
        return newMazePosition;
    }

    public MazePosition moveUp() {
        MazePosition newMazePosition = null;

        // We are not on the edge of map
        if (y > 0) {
            if (isUnlocked(previousStepsHash.charAt(0))) {
                newMazePosition = new MazePosition(getX(), getY() - 1, this);
            }
        }
        return newMazePosition;
    }

    public MazePosition moveDown() {
        MazePosition newMazePosition = null;

        // We are not on the edge of map
        if (y < 3) {
            if (isUnlocked(previousStepsHash.charAt(1))) {
                newMazePosition = new MazePosition(getX(), getY() + 1, this);
            }
        }
        return newMazePosition;
    }

}
