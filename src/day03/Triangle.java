package day03;

public class Triangle {
	private int dimA, dimB, dimC;
	
	public Triangle() {
		
	}
	
	public Triangle(int dimA, int dimB, int dimC) {
		this.dimA = dimA;
		this.dimB = dimB;
		this.dimC = dimC;
	}
	
	public boolean isValid() {
		return dimA + dimB > dimC && dimB + dimC > dimA && dimC + dimA > dimB;
	}
	
	public void printDim() {
		System.out.println("A: "+dimA+" B: "+dimB+" C: "+dimC);
	}
	

}
