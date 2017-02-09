package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class Game {
	public static Scanner sc = new Scanner(System.in);
	
	static final int BLANK = 0;
	static final int X = 1;
	static final int O = -1;
	
	static final int MAX = 1;
	static final int MIN = -1;
	
	static final int WIN = 100;
	static final int LOSE = -100;
	static final int DRAW = 0;
	
	static final int START = 1010011; // S in binary
	static final int END = 1000101; // E in binary
	
	
	static Board b;
	static int curTurn;
	static int turnType;
	
	// Does all the computation to determine an optimal move
	public static int[] Minimax(Board state, int mySide, int lastMove) { // mySide is X or O
		// Return will be [score, optimalMove]
		
		turnType = (mySide == (int) Math.pow(-1, state.getMovesMade())) ? MAX : MIN; // if playing an even turn: MAX, else MIN
		//System.out.println(turnType);
		int score = 0;
		if (state.isTerminal()) {
			System.out.println("\n\n Term \n\n");
			
			int winner = state.whoWon();
			if (winner != BLANK) { // if there's a winner
				if (mySide == winner) {
					score = WIN;
				} else { score = LOSE; }

				return new int[] {score, lastMove};
			}
			else { // If it's a draw
				return new int[] {DRAW, lastMove}; 
			}
		}
		
		if (curTurn == state.getMovesMade()) { lastMove = 1; }
		
		
		int optimal = 1;
		if (curTurn == 0) {
			System.out.println("\tFirst...");
			return new int[] {0, optimal};
		}
		
		//System.out.println("\n OK \n");
		int[] actions = state.emptySquares();
		Board[] nextLevel = new Board[actions.length];
		Board cur = null;
	
		for (int a = 0; a < actions.length; a ++) {
			System.out.println("\n loop \n");
			
			int act = actions[a];
			cur = nextLevel[a] = state.result(act); // Create a array of results from possible actions
			//cur.showBoard();
			int[] res = Minimax(cur, -1*mySide, act);
			
			score = 0;
			if (cur.isTerminal()) {
				System.out.println("\n Terminal scoring \n");
				int winner = cur.whoWon();
				if (winner != BLANK) { // if there's a winner
					if (turnType == winner) {
						score = WIN;
					} else { score = LOSE; }

					return new int[] {score, lastMove};
				}
				else { // If it's a draw
					return new int[] {DRAW, lastMove}; 
				}
			}
			if (res[0] == WIN && mySide == X) return res;
			if (res[0] == LOSE && mySide == O) return res;
		}
		
		return new int[] {0, optimal};
	}
	
	
	public static void main(String[] args) {
		b = new Board();
		
		//b.showBoard();
		
		Random r = new Random();
		
		for (int turn = 0; turn <= 8; turn++) {
			curTurn = turn;
			
			int[] actions = b.emptySquares();
			int a = r.nextInt(actions.length);
			
			//for (int ac: actions) System.out.println(ac);
			
			int player = (int) Math.pow(-1, turn);
			int act = 0;
			
			if (player == O) {
				System.out.println("Play a thing, human!");
				act = sc.nextInt(); 
			}
			else {
				act = Minimax(b, X, START)[1];
			}
			
			boolean moved = b.move(act);
			int c = 1;
			while (!moved) {
				System.out.println("Invalid! That's already taken. Try again.");
				Board other = b;
				act = Minimax(other, X, c)[1];
				System.out.println("Minimax says: " + act);
				
				moved = b.move(act);
				c++;
				if (c > 9) {
					System.out.println("Overflow!!!!!!!!!!!!!!!!");
					break;
				}
			}
			
			b.showBoard();
			
			int winner = b.whoWon();
			if (winner != 0) {
				
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
