package day15;

public class Disc {
	private Integer size;
	private Integer currentPosition;
	
	public Disc(Integer size, Integer currentPosition) {
		super();
		this.size = size;
		this.currentPosition = currentPosition;
	}
	
	public Disc getShallowCopy() {
		return new Disc(this.size, this.currentPosition);
	}

	public Integer getCurrentPosition() {
		return currentPosition;
	}
	
	public void rotate() {
		currentPosition = (currentPosition+1) % size; 
	}
	
	

}
