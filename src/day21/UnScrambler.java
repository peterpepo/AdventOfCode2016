package day21;

public class UnScrambler extends Scrambler {

    public UnScrambler(String text) {
        super(text);
    }

    @Override
    public void rotateBasedOnCharPosition(char wantedChar) {
        int rotateTimes = 0;
        int firstWantedCharIndex = 0;
        boolean wantedCharacterFound = false;

        for (int i = 0; i < text.length; i++) {
            if (text[getOffsetIndex(i)] == wantedChar) {
                firstWantedCharIndex = i;
                wantedCharacterFound = true;
                break;
            }
        }

        switch (firstWantedCharIndex) {
            case 0:
                rotateTimes = 7;
                break;
            case 1:
                rotateTimes = 7;
                break;
            case 2:
                rotateTimes = 2;
                break;
            case 3:
                rotateTimes = 6;
                break;
            case 4:
                rotateTimes = 1;
                break;
            case 5:
                rotateTimes = 5;
                break;
            case 6:
                rotateTimes = 0;
                break;
            case 7:
                rotateTimes = 4;
                break;
        }

        if (wantedCharacterFound) {
            for (int i = 0; i < rotateTimes; i++) {
                rotateRight();
            }
        }

    }

    @Override
    public void rotateRight(int times) {
        for (int i = 0; i < times; i++) {
            super.rotateLeft();
        }
    }

    @Override
    public void rotateLeft(int times) {
        for (int i = 0; i < times; i++) {
            super.rotateRight();
        }
    }
    
    @Override
    public void movePosition(int oldPosition, int newPosition) {
        super.movePosition(newPosition, oldPosition);
    }

}
