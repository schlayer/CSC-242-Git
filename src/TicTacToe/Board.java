package TicTacToe;

public class Board {
	public final int BLANK = 0;
	public final int X = 1;
	public final int O = -1;
	private int movesMade = 0;
	
	public int getMovesMade() {
		return movesMade;
	}

	private int[] played = new int[9];
	private Square[] squares = new Square[9];
	
	public Board() {
		for (int p: played) { p = 0; }
		for (int i = 1; i <= 9; i++) {
			squares[i-1] = new Square(i);
		}
		this.movesMade = 0;
		System.out.println("This board has been created.");
		
	}
	
	public void clearSquare(int position) {
		squares[position-1].clear();
	}
	
	public void clearBoard() {
		for (int p: played) { p = 0; }
		for (int i = 1; i <= 9; i++) {
			squares[i-1] = new Square(i);
		}
		this.movesMade = 0;
		System.out.println("This board has been cleared.");
	}
	
	public boolean move(int player, int position) { // plays an X or O in the designated square
		assert (player == X || player == O);
		
		int index = position-1;
		//System.out.println("INDEX: " + index);
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
		
		movesMade ++;
		return true;
	}
	
	public Board result(int player, int position) { // plays an X or O in the designated square (minimax)
		assert (player == X || player == O);
		
		int index = position-1;
		Square sq = squares[index];
		assert sq.getState() == BLANK;
		
		/*
		if (sq.getState() != BLANK) {
			System.out.println("That square is occupied!");
			return null;
		}
		*/
		
		if (player == X) {
			sq.playX();
			//System.out.println("X in square " + position);
		}
		if (player == O) {
			sq.playO();
			//System.out.println("O in square " + position);
		}
		
		movesMade ++;
		return this;
	}
	
	public int[] getAllStates() { // Returns an array of all spaces' (0-8) contents 
		int[] states = new int[9];
		for (int i = 0; i < 9; i++) {
			int st = squares[i].getState();
			assert st == X || st == O || st == BLANK; // Ensure win conditions will work
			states[i] = st;
		}
		
		return states;
	}
	
	public void reconstruct(int[] allStates) {
		assert allStates.length == 9;
		clearBoard();
		int pos = 1;
		for (int state: allStates) {
			if (state != BLANK) { // If blank, leave it
				move(state, pos); // Otherwise, place the needed mark there
			}
			pos ++;
		}
	}
	
	public int[] emptySquares() { // Returns an array of all empty positions (possible actions)
		int empty = 9 - movesMade;
		if (empty == 0) return null;
		
		int[] blanks = new int[empty];
		int index = 0;
		for (Square sq: squares) {
			if (sq.getState() == BLANK) {
				blanks[index] = sq.getPosition();
				index ++;
			}
		}
		
		return blanks;
	}
	
	public String toString() { // Create a string to represent the whole board
		String str = "";
		//str += "TicTacToe";
		for (int i = 1; i <= 9; i++) {
			int sq = i-1;
			if (i == 1 || i == 4 || i == 7) str += "\n|" + squares[sq].print() + "|";
			else str += squares[sq].print() + "|"; 	
		}
		
		return str + "\n";
	}
	
	public void showBoard() { // Print the whole board
		System.out.println(this.toString());
	}
	
	public boolean isTerminal() {
		if (movesMade == 9) {
			return true;
		}
		if (whoWon() != 0) {
			return true;
		}
		return false;
	}
	
	public int whoWon() { // Returns O if O won, X if X won, or BLANK is nobody has won
        // All sets of three: ROWS 0,1,2; 3,4,5; 6,7,8; COLUMNS 0,3,6; 1,4,7; 2,5,8; DIAGONALS 0,4,8; 2,4,6
        // Adding the lines of three will give 3 if X wins, -3 if O wins
       
        int[] T = getAllStates();
        int center = T[4];
       
        if (center != BLANK) { // Check diagonals
            if (center + T[0] + T[8] == 3)      { return X; }   // NW to SE
            if (center + T[0] + T[8] == -3)     { return O; }
            if (center + T[2] + T[6] == 3)      { return X; }   // NE to SW
            if (center + T[2] + T[6] == -3)     { return O; }
        }
       
        for (int s = 0; s <= 6; s += 3) {
            if (T[s] + T[s+1] + T[s+2] == 3)    { return X; }   // Horizontal
            if (T[s] + T[s+1] + T[s+2] == -3)   { return O; }
        }
       
        for (int s = 0; s <= 2; s ++) {
            if (T[s] + T[s+3] + T[s+6] == 3)    { return X; }   // Vertical
            if (T[s] + T[s+3] + T[s+6] == -3)   { return O; }
        }
       
        System.out.println("Nobody has won yet...");
        return BLANK;
    }
	
	public int numTwos(int player) { // Returns the number of twos on the board for the given player
		// All sets of three: ROWS 0,1,2; 3,4,5; 6,7,8; COLUMNS 0,3,6; 1,4,7; 2,5,8; DIAGONALS 0,4,8; 2,4,6
		// Adding the lines of three will give 2 if X has two, -2 if O has two
		assert (player == X || player == O);
		if (movesMade < 3) { return 0; } // No twos can exist unless 3 moves were made.

		int twos = 0;
		int want = 2*player; // -2 for O, 2 for X

		int[] T = getAllStates();

		// Check diagonals
		if (T[4] + T[0] + T[8] == want)     { twos ++; }    // NW to SE
		if (T[4] + T[2] + T[6] == want)     { twos ++; }    // NE to SW

		for (int s = 0; s <= 6; s += 3) {
			if (T[s] + T[s+1] + T[s+2] == want)     { twos ++; }    // Horizontal
		}

		for (int s = 0; s <= 2; s ++) {
			if (T[s] + T[s+3] + T[s+6] == want)     { twos ++; }    // Vertical
		}

		String pstr = (player == X) ? "X" : "O";
		System.out.println(pstr + " has " + twos + " twos.");
		return twos;
	}

	public int whereToBlock(int opponent) { // Returns 0 if enemy has no twos, or a position to block them.
		// All sets of three: ROWS 0,1,2; 3,4,5; 6,7,8; COLUMNS 0,3,6; 1,4,7; 2,5,8; DIAGONALS 0,4,8; 2,4,6
		// Adding the lines of three will give 2 if X has two, -2 if O has two
		assert (opponent == X || opponent == O);
		if (movesMade < 3) { return 0; } // No twos can exist unless 3 moves were made.

		int enemyTwos = 0;
		int want = 2*opponent; // 2 for O, -2 for X
		int blockPos = 0;

		int[] T = getAllStates();

		// Check diagonals
		if (T[0] + T[4] + T[8] == want) { // NW to SE
			enemyTwos ++;
			for (int k = 0; k <= 8; k += 4) { // find the blank, set blockPos to that position
				if (T[k] == BLANK) { blockPos = k+1; break; }
			}
		}  
		if (T[2] + T[4] + T[6] == want) { // NE to SW
			enemyTwos ++;
			for (int k = 0; k <= 6; k += 2) { // find the blank, set blockPos to that position
				if (T[k] == BLANK) { blockPos = k+1; break; }
			}
		}  

		for (int s = 0; s <= 6; s += 3) { // Horizontal
			if (T[s] + T[s+1] + T[s+2] == want) {
				enemyTwos ++;
				for (int k = s; k <= s+2; k += 1) { // find the blank, set blockPos to that position
					if (T[k] == BLANK) { blockPos = k+1; break; }
				}
			}  
		}

		for (int s = 0; s <= 2; s ++) { // Vertical
			if (T[s] + T[s+3] + T[s+6] == want) {
				enemyTwos ++;
				for (int k = s; k <= s+6; k += 3) { // find the blank, set blockPos to that position
					if (T[k] == BLANK) { blockPos = k+1; break; }
				}
			}  
		}

		String pstr = (opponent == X) ? "X" : "O";
		System.out.println(pstr + " has " + enemyTwos + " twos.");
		if (enemyTwos < 1) { return 0; }
		else {
			System.out.println("Must block at position " + blockPos + "!");
		}

		return blockPos;
	}
	
	
}
