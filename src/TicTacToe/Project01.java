package TicTacToe;

import java.util.Random;

public class Project01 {
	
	static final int BLANK = 0;
	static final int X = 1;
	static final int O = -1;
	
	
	public static void main(String[] args) {
		Board b = new Board();
		
		//b.showBoard();
		
		Random r = new Random();
		
		for (int turn = 0; turn <= 8; turn++) {
			int[] actions = b.emptySquares();
			
			int a = r.nextInt(actions.length);
			
			//for (int ac: actions) System.out.println(ac);
			
			int p = (int) Math.pow(-1, turn);
			int s = actions[a];
			
			boolean moved = b.move(p, s);
			
			//b.showBoard();
			
			int winner = b.whoWon();
			if (winner != 0) {
				//System.out.println("woo!");
				if (winner == X) { 	System.out.println("X Wins!\t Took: " + turn + " Moves.");
				} else 				System.out.println("O Wins!\t Took: " + turn + " Moves.");
				
				break;
			}
			if (turn == 8) {
				System.out.println("It's a draw!");
			}
		}
		
		b.showBoard();
		
	}
}
