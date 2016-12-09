package day08;

public class Display {

    private final Character PIXEL_OFF = '.';
    private final Character PIXEL_ON = '#';

    private Character[][] displayContent;

    public Display(int rows, int cols) {
        displayContent = new Character[rows][cols];
        this.setAll(PIXEL_OFF);
    }

    public void setColumn(int columnId, Character[] columnChars) {
        for (int i = 0; i < displayContent.length; i++) {
            displayContent[i][columnId] = columnChars[i];
        }
    }

    public Character[] getColumn(int columnId) {
        Character[] column = new Character[displayContent.length];
        for (int i = 0; i < displayContent.length; i++) {
            column[i] = displayContent[i][columnId];
        }

        return column;
    }

    public Character[] getRow(int rowId) {
        Character[] row = new Character[displayContent[rowId].length];
        for (int j = 0; j < displayContent[rowId].length; j++) {
            row[j] = displayContent[rowId][j];
        }

        return row;
    }

    public void setRow(int rowId, Character[] rowChars) {
        for (int j = 0; j < displayContent[rowId].length; j++) {
            displayContent[rowId][j] = rowChars[j];
        }
    }

    public void setAll(Character c) {
        for (int i = 0; i < displayContent.length; i++) {
            for (int j = 0; j < displayContent[i].length; j++) {
                displayContent[i][j] = c;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder retVal = new StringBuilder();

        for (Character[] row : displayContent) {
            for (Character col : row) {
                retVal.append(col);
            }
            retVal.append('\n');
        }

        return retVal.toString();
    }

    public void createRectangle(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                displayContent[i][j] = PIXEL_ON;
            }
        }
    }

    public void rotateColumn(int columnId, int amount) {
        Character[] columnValue = getColumn(columnId);
        RingBuffer rb = new RingBuffer(columnValue);

        for (int i = 1; i <= amount; i++) {
            rb.decOffset();
        }

        columnValue = rb.getChars();
        setColumn(columnId, columnValue);
    }

    public void rotateRow(int rowId, int amount) {
        Character[] rowValue = getRow(rowId);
        RingBuffer rb = new RingBuffer(rowValue);

        for (int i = 1; i <= amount; i++) {
            rb.decOffset();
        }

        rowValue = rb.getChars();
        setRow(rowId, rowValue);
    }
    
    public int getOnCount() {
        int onCount = 0;
        
        for(Character[] row:displayContent) {
            for(Character col:row) {
                if(col == PIXEL_ON) {
                    onCount++;
                }
            }
        }
        
        return onCount;
    }

}
