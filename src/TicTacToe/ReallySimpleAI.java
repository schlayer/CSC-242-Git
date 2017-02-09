package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class ReallySimpleAI {

	static final int BLANK = 0;
	static final int X = 1;
	static final int O = -1;
	static final int MAX = 1;
	static final int MIN = -1;
	// Points
	static final int WIN = 100;
	static final int LOSE = -100;
	static final int DRAW = 0;
	static final int TWOS_VAL = 1;
	static final int BLOCK_VAL = 5;
	
	//static int AImove = 1;
	static int curTurn = 0;
	static boolean first = false;
	

	public static int Minimax(Board state, int maxPlayer) {
		//int score = lastScore;
		boolean root = false;
		if (first) { 
			root = true; 
			first = false;
			
			// First turn always results in this move, so this saves a ton of time
			if (state.getMovesMade() == 0 && maxPlayer == X) {
				return 1;
			}
			
		}
		
		int score = 0;
		if (state.isTerminal()) { // if terminal, return the score
			System.out.println("Term!");
			int winner = state.whoWon();
			if (winner == 0) { return DRAW; }
			else {
				if (winner == maxPlayer) { return WIN; }
				else { return LOSE; }
			}
		}
		
		int[] actions = state.emptySquares();
		
		//System.out.println("Actions: ");
		//for (int a: actions) { System.out.print(a + " "); }
		//System.out.println("\n");
		
		int optimalPos = actions[actions.length-1];
		
		if (actions == null) {
			System.out.println("No actions!");
			return score;
		}
		
		int minPos = actions[actions.length-1];
		int maxPos = actions[actions.length-1];
		int minScore = 1000;
		int maxScore = -1000;
		int curScore = 0;
		
		for (int act: actions) { // Evaluate for all possible actions
			Board s2 = state;
			s2 = s2.result(act);
			if (s2 == null) {
				continue;
			}
			
			System.out.println("Evaluating position " + act + " for turn " + s2.getMovesMade());
			curScore = Minimax(s2, maxPlayer);
			//s2.showBoard();
			if (curScore > maxScore) { // Update max
				maxPos = act;
				maxScore = curScore;
			}
			if (curScore < minScore) { // Update min
				minPos = act;
				minScore = curScore;
			}
			s2.clearSquare(act);
		}

		if (state.getCurrentPlayer() == maxPlayer) {
			score = maxScore;
			optimalPos = maxPos;
		} else { 
			score = minScore; 
			optimalPos = minPos;
		}

		if (root) { // if at the 'root' of recursive tree
			System.out.println("\n\t\tRoot: RETURNING POS\n");
			//System.out.println("Root!");
			return optimalPos;
		}
		 
		// Score is better for fewer moves to win
		score = score + 9 - state.getMovesMade();
		
		return score;
	}
	
	
	
	public static Scanner sc = new Scanner(System.in); 
	public static void main(String[] args) {
	
		Board b = new Board();
		
		//b.showBoard();
		
		Random r = new Random();
		
		for (int turn = 0; turn <= 8; turn++) {
			int[] actions = b.emptySquares();
			curTurn = turn;
			
			int a = r.nextInt(actions.length);
			
			
			int player = b.getCurrentPlayer();
			int movePos = actions[a];
			
			if (player == X) {
				//Board c = b;
				first = true;
				
				//b.showBoard();
				movePos = Minimax(b, player);
				System.out.println("Minimax eval...");
				b.showBoard();
				System.out.println("Minimax chose: " + movePos);
				
			} else {
				System.out.println("Player O chooses....");
				movePos = sc.nextInt();
			}
			
			boolean moved = b.move(movePos);
			if (!moved) { 
				System.out.println("Error!  " +  movePos);
				
			}
			b.showBoard();
			
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
