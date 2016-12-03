package day02;

public class NineDigitKeyboard implements Keyboard {
	private int[][] keys = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

	private int positionX;
	private int positionY;

	public NineDigitKeyboard() {
		this.positionX = 1;
		this.positionY = 1;
	}

	public NineDigitKeyboard(int startX, int startY) {
		this.positionX = startX;
		this.positionY = startY;
	}

	public void moveLeft() {
		if (positionX >= 1)
			positionX--;
	}

	public void moveRight() {
		if (positionX <= 1)
			positionX++;
	}

	public void moveUp() {
		if (positionY >= 1)
			positionY--;
	}

	public void moveDown() {
		if (positionY <= 1)
			positionY++;
	}

	public String getKey() {
		return Integer.toString(keys[positionY][positionX]);
	}

}
