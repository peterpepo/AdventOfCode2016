package day02;

public class ButtonInstructionReader {
	private Keyboard buttonPush;
	
	public ButtonInstructionReader(Keyboard newKeyBoard) {
		buttonPush = newKeyBoard;
	}
	
	public String getButtonByLine(String rowOfInstructions) {
		
		for(char instrunction:rowOfInstructions.toCharArray()) {
			
			switch(instrunction) {
			case 'U':
				buttonPush.moveUp();
				break;
			case 'D':
				buttonPush.moveDown();
				break;
			case 'L':
				buttonPush.moveLeft();
				break;
			case 'R':
				buttonPush.moveRight();
				break;
			}
		}
		
		return buttonPush.getKey();
	}

}
