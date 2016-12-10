package day10;

import java.util.Objects;

public class WorkArenaRule {

    private Robot sourceRobot;
    private ChipCompare chipIdentifier;

    public WorkArenaRule(Robot sourceRobot, ChipCompare chipIdentifier) {
        this.sourceRobot = sourceRobot;
        this.chipIdentifier = chipIdentifier;
    }

    public Robot getSourceRobot() {
        return sourceRobot;
    }

    public ChipCompare getChipIdentifier() {
        return chipIdentifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorkArenaRule other = (WorkArenaRule) obj;
        if (!Objects.equals(this.sourceRobot, other.sourceRobot)) {
            return false;
        }
        if (this.chipIdentifier != other.chipIdentifier) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.sourceRobot);
        hash = 53 * hash + Objects.hashCode(this.chipIdentifier);
        return hash;
    }

}
