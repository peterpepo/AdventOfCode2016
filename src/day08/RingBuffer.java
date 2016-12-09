package day08;

public class RingBuffer {

    private Character[] buffer;
    private int offset;

    public RingBuffer(Character[] source) {
        buffer = source;
        offset = 0;
    }

    public Character[] getChars() {
        Character[] retArray = new Character[buffer.length];

        for (int i = 0; i < buffer.length; i++) {
            int calcPos;
            calcPos = (i + offset) % buffer.length;
            if (calcPos < 0) {
                calcPos += buffer.length;
            }
            
            retArray[i] = buffer[calcPos];
        }

        return retArray;
    }

    public void incOffset() {
        offset++;
    }

    public void decOffset() {
        offset--;
    }

}
