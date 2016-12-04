package day01;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private Position position;
    private Position firstPositionVisitedTwice;
    private Orientation orientation;
    private List<Position> visitedPoints = new ArrayList<>();
    private boolean stopped = false;

    public Person() {
        this.position = new Position(0, 0);
        this.orientation = Orientation.NORTH;
    }

    public String printPosition() {
        return ("X: " + position.getPositionX() + ", Y: " + position.getPositionY() + ", orientation: " + orientation);
    }

    public void move(String instruction) {
        String changeDirection = "";
        int distance = 0;
        int matchesCount = 0;
        List<String> instructionParts = new ArrayList<>();

        Matcher match = Pattern.compile("[0-9]+|[a-z]+|[A-Z]+").matcher(instruction);

        while (match.find()) {
            instructionParts.add(match.group());
        }

        if (instructionParts.size() == 2) {
            changeDirection = instructionParts.get(0);
            distance = Integer.parseInt(instructionParts.get(1));
        }

        switch (changeDirection) {
            case "R":
                this.orientation = Orientation.turnRight90(this.orientation);
                break;
            case "L":
                this.orientation = Orientation.turnLeft90(this.orientation);
                break;
        }

        for (int i = 1; i <= distance; i++) {
            moveStep();
            
            if (this.visitedPoints.contains(this.position)) {
                if (firstPositionVisitedTwice == null) {
//                    System.out.println("I have been there!");
                    this.firstPositionVisitedTwice = new Position(this.position.getPositionX(), this.position.getPositionY());
                }
            } else {
                this.visitedPoints.add(new Position(this.position.getPositionX(), this.position.getPositionY()));
            }
//            System.out.println(this.positionX() + ":" + this.positionY());
        }
    }

    private void moveStep() {
        switch (this.orientation) {
            case NORTH:
                this.position.setPositionY(this.position.getPositionY() + 1);
                break;
            case SOUTH:
                this.position.setPositionY(this.position.getPositionY() - 1);
                break;
            case EAST:
                this.position.setPositionX(this.position.getPositionX() + 1);
                break;
            case WEST:
                this.position.setPositionX(this.position.getPositionX() - 1);
                break;
        }
    }

    public void moveStopWhenHasBeenThereTwice(String instruction) {
        this.move(instruction);

        if (this.visitedPoints.contains(this.position)) {
            if (firstPositionVisitedTwice != null) {
                this.firstPositionVisitedTwice = this.position;
            }
        } else {
            this.visitedPoints.add(this.position);
        }
    }

    public int positionX() {
        return this.position.getPositionX();
    }

    public int positionY() {
        return this.position.getPositionY();
    }

    public Position getPosition() {
        return position;
    }

    public Position getFirstPositionVisitedTwice() {
        return firstPositionVisitedTwice;
    }

}
