package day21;

public class Scrambler {

    protected char[] text;
    int offset = 0;

    public Scrambler(String text) {
        this.text = text.toCharArray();
    }

    private Scrambler(char[] text) {
        this.text = text;
    }

    public int[] getOffsetIndexes() {
        int[] indexes = new int[text.length];

        for (int i = 0; i < text.length; i++) {
            int calcPos = (i + offset) % text.length;
            if (calcPos < 0) {
                calcPos += text.length;
            }

            indexes[i] = calcPos;
        }

        return indexes;
    }

    private char[] getText() {
        return getText(0, text.length - 1);
    }

    private char[] getText(int start, int end) {
        char[] newText = new char[end - start + 1];

        for (int i = 0; i < newText.length; i++) {
            newText[i] = text[getOffsetIndex(start + i)];
        }

        return newText;
    }

    protected int getOffsetIndex(int refIndex) {
        int newIndex = (refIndex + offset) % text.length;

        if (newIndex < 0) {
            newIndex += text.length;
        }

        return newIndex;
    }

    public void rotateLeft(int times) {
        for (int i = 0; i < times; i++) {
            rotateLeft();
        }
    }

    protected void rotateLeft() {
        this.offset++;
    }

    public void rotateRight(int times) {
        for (int i = 0; i < times; i++) {
            rotateRight();
        }
    }

    protected void rotateRight() {
        this.offset--;
    }

    public void swapByIndex(int indexA, int indexB) {
        char temp = text[getOffsetIndex(indexA)];
        text[getOffsetIndex(indexA)] = text[getOffsetIndex(indexB)];
        text[getOffsetIndex(indexB)] = temp;
    }

    public void swapByChar(char oldChar, char newChar) {
        for (int i = 0; i < text.length; i++) {
            if (text[i] == oldChar) {
                text[i] = newChar;
            } else if (text[i] == newChar) {
                text[i] = oldChar;
            }
        }
    }

    public void rotateBasedOnCharPosition(char wantedChar) {
        int rotateTimes = 1;
        int firstWantedCharIndex = 0;
        boolean wantedCharacterFound = false;

        for (int i = 0; i < text.length; i++) {
            if (text[getOffsetIndex(i)] == wantedChar) {
                firstWantedCharIndex = i;
                wantedCharacterFound = true;
                break;
            }
        }

        if (wantedCharacterFound) {
            rotateTimes += firstWantedCharIndex;

            if (firstWantedCharIndex >= 4) {
                rotateTimes += 1;
            }

            for (int i = 0; i < rotateTimes; i++) {
                rotateRight();
            }
        }
    }

    private void replacePart(int startPosition, char[] newPart) {
        int newPartIndex = 0;

        for (int i = startPosition; i < startPosition + newPart.length; i++) {
            text[getOffsetIndex(i)] = newPart[newPartIndex++];
        }
    }

    public void movePosition(int oldPosition, int newPosition) {
        int start = Math.min(oldPosition, newPosition);
        int end = Math.max(oldPosition, newPosition);
        Scrambler textToRotate = new Scrambler(this.getText(start, end));

        if (oldPosition < newPosition) {
            textToRotate.rotateLeft();
        } else {
            textToRotate.rotateRight();
        }

        char[] rotatedTextPart = textToRotate.getText();

        replacePart(start, rotatedTextPart);
    }

    public void reversePart(int start, int end) {
        for (int i = 0; i < Math.floorDiv(end - start + 1, 2); i++) {
            swapByIndex(start+i, end - i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length; i++) {
            sb.append(text[getOffsetIndex(i)]);
        }
        return sb.toString();
    }

    public int getLength() {
        return text.length;
    }

}
