package TicTacToe;

public class Square {
	public final int BLANK = 0;
	public final int X = 1;
	public final int O = 2;
	
	private int position = 0;
	private int state = BLANK;
	

	public Square(int position) {
		this.position = position;
	}
	
	public void playX() {
		state = X;
	}
	
	public void playO() {
		state = O;
	}
	
	public void clear() {
		state = BLANK;
	}
	
	public int getState() {
		return state;
	}
}
