package day02;

public class StarKeyboard implements Keyboard {
	private final char BLANK = 'x';

	private char[][] keys = { { 'x', 'x', '1', 'x', 'x' }, { 'x', '2', '3', '4', 'x' }, { '5', '6', '7', '8', '9' },
			{ 'x', 'A', 'B', 'C', 'x' }, { 'x', 'x', 'D', 'x', 'x' } };

	private int positionX;
	private int positionY;

	public StarKeyboard() {
		this.positionX = 0;
		this.positionY = 2;
	}

	public StarKeyboard(int startX, int startY) {
		this.positionX = startX;
		this.positionY = startY;
	}

	public void moveLeft() {
		if (positionX - 1 >= 0 && keys[positionY][positionX - 1] != BLANK)
			positionX--;
	}

	public void moveRight() {
		if (positionX + 1 < keys[positionY].length && keys[positionY][positionX + 1] != BLANK)
			positionX++;
	}

	public void moveUp() {
		if (positionY - 1 >= 0 && keys[positionY - 1][positionX] != BLANK)
			positionY--;
	}

	public void moveDown() {
		if (positionY + 1 < keys.length && keys[positionY + 1][positionX] != BLANK)
			positionY++;
	}

	public String getKey() {
		
		return Character.toString(keys[positionY][positionX]);

	}

}
