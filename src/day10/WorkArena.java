package day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkArena {

    private String name;

    private Map<WorkArenaRule, ChipTakeable> rulesList = new HashMap<>();
    private Map<String, Robot> robotList = new HashMap<>();
    private Map<String, Box> boxList = new HashMap<>();

    public void addRobot(Robot robot) {
        robotList.put(robot.getName(), robot);
    }

    public void addBox(Box box) {
        boxList.put(box.getName(), box);
    }

    public List<Robot> getRobots() {
        return new ArrayList<Robot>(robotList.values());
    }

    public List<Box> getBoxes() {
        return new ArrayList<Box>(boxList.values());
    }

    public Robot getRobot(String name) {
        Robot r = robotList.get(name);

        if (r == null) {
            r = new Robot(name);
        }

        addRobot(r);

        return r;
    }

    public Box getBox(String name) {
        Box b = boxList.get(name);

        if (b == null) {
            b = new Box(name);
        }

        addBox(b);

        return b;
    }

    public void addRule(Robot sourceRobot, ChipCompare chipCompare, ChipTakeable target) {
        rulesList.put(new WorkArenaRule(sourceRobot, chipCompare), target);
    }

    public boolean existsRule(Robot sourceRobot, ChipCompare chipCompare, ChipTakeable target) {
        if (rulesList.get(new WorkArenaRule(sourceRobot, chipCompare)) != null) {
            return true;
        } else {
            return false;
        }
    }

    public WorkArena(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinished() {
        for (Robot robot : getRobots()) {
            if (robot.isFull()) {
                return false;
            }
        }
        return true;
    }

    public void playRound() {
        for (Robot robot : getRobots()) {
            if (robot.isFull()) {
                ChipTakeable target1 = rulesList.get(new WorkArenaRule(robot, ChipCompare.LOW));
                ChipTakeable target2 = rulesList.get(new WorkArenaRule(robot, ChipCompare.HIGH));

                target1.takeChip(robot.giveChipLow());
                target2.takeChip(robot.giveChipHigh());
            }
        }
    }

}
