package TicTacToe;

public class Board {
	public final int BLANK = 0;
	public final int X = 1;
	public final int O = 2;
	
	private int[] played = new int[9];
	private Square[] squares = new Square[9];
	public Board() {
		for (int p: played) { p = 0; }
		for (int i = 1; i <= 9; i++) {
			squares[i-1] = new Square(i);
		}

		System.out.println("This board has been created.");
		
	}
	
	public boolean move(int player, int position) {
		assert (player == X || player == O);
		int index = position-1;
		Square sq = squares[index];
		if (sq.getState() != BLANK) {
			System.out.println("That square is occupied!");
			return false;
		}
		
		if (player == X) {
			sq.playX();
			System.out.println("X in square " + position);
		}
		if (player == O) {
			sq.playO();
			System.out.println("O in square " + position);
		}
		
		return true;
	}
	
	
	
}
