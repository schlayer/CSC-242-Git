package TicTacToe;

public class Square {
	public final int BLANK = 0;
	public final int X = 1;
	public final int O = -1;
	
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
	
	public int getPosition() {
		return position;
	}

	public String print() { // for printing the board
		switch (state) {
		case BLANK:
			return " ";
		case X:
			return "X";
		case O:
			return "O";
		}
		
		return " ";
	}
}
