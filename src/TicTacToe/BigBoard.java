package TicTacToe;

public class BigBoard { // for 9-Board
	public final int BLANK = 0;
	public final int X = 1;
	public final int O = -1;
	private int movesMade = 0;
	private Board[] boards;
	
	public int getMovesMade() {
		return movesMade;
	}
	
	public int getCurrentPlayer() {
		int player = (int) Math.pow(-1, movesMade);
		return player;
	}
	
	public boolean move(int smallBoard, int position) { // plays an X or O in the designated square
		int player = getCurrentPlayer();
		assert (smallBoard <= 9 && smallBoard > 0);
		
		
		
		int bindex = smallBoard -1;
		Board b = boards[bindex];
		System.out.println("In board " + smallBoard);

		boolean moved = b.move9B(position, player);
		if (moved) { movesMade ++; }
		else { System.out.println("Something went wrong!"); }
		
		return moved;
	}
	
	public BigBoard() {
		this.boards = new Board[9];
		for (int i = 1; i <= 9; i++) {
			boards[i-1] = new Board();
		}
		this.movesMade = 0;
		System.out.println("This board has been created.");
	}
	
	public void showAllSmall() {
		int bNum = 1;
		for (Board b: boards) {
			System.out.println("Board " + bNum + ":");
			b.showBoard();
			bNum ++;
		}
	}
	
	public Board getBoard(int smallBoard) {
		assert (smallBoard <= 9 && smallBoard > 0);
		
		int bindex = smallBoard -1;
		Board b = boards[bindex];
		return b;
	}
	
	public void printBigBoard() {
		System.out.println(this.toString());
	}
	
	public boolean isTerminal() { // checks each small board to see if in a terminal state
		for (Board b: boards) {
			boolean bTerm = b.isTerminal();
			if (bTerm) return bTerm;
		}
		
		return false;
	}
	
	public String toString() {
		String str = "";
		str += "9-Board TicTacToe";
		for (int num = 1; num <= 7; num += 3) {
			Board sm1 = getBoard(num);
			Board sm2 = getBoard(num+1);
			Board sm3 = getBoard(num+2);

			for (int i = 1; i <= 9; i++) {
				int sq = ((i+2) % 3); // 0-1-2 0-1-2 0-1-2

				if (i == 1) str += "\n||";
				if (i == 4 || i == 7) str += "|";
				if (i <= 3) str += sm1.squares[sq].print() + "|"; 	
				else if (i <= 6) str += sm2.squares[sq].print() + "|";
				else if (i <= 9) str += sm3.squares[sq].print() + "|";
				if (i == 9) str += "|";
			}
			
			
			for (int i = 1; i <= 9; i++) {
				int sq = ((i+2) % 3) + 3; // 3-4-5 3-4-5 3-4-5

				if (i == 1) str += "\n||";
				if (i == 4 || i == 7) str += "|";
				if (i <= 3) str += sm1.squares[sq].print() + "|"; 	
				else if (i <= 6) str += sm2.squares[sq].print() + "|";
				else if (i <= 9) str += sm3.squares[sq].print() + "|";
				if (i == 9) str += "|";
			}
			
			
			for (int i = 1; i <= 9; i++) {
				int sq = ((i+2) % 3) + 6; // 6-7-8 6-7-8 6-7-8

				if (i == 1) str += "\n||";
				if (i == 4 || i == 7) str += "|";
				if (i <= 3) str += sm1.squares[sq].print() + "|"; 	
				else if (i <= 6) str += sm2.squares[sq].print() + "|";
				else if (i <= 9) str += sm3.squares[sq].print() + "|";
				if (i == 9) str += "|";
			}
			
			if (num != 7) str += "\n=======================";
		}
		
		return str + "\n";
	
	}

}
