package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class GoodAI {

	static final int BLANK = 0;
	static final int X = 1;
	static final int O = -1;
	static final int MAX = 1;
	static final int MIN = -1;
	// Points
	static final int WIN = 100;
	static final int LOSE = -100;
	static final int DRAW = 0;

	static int curTurn = 0;
	static boolean first = false;
	
	// Minimax, plus a few situational improvements
	public static int Minimax(Board state, int maxPlayer) {
		
		boolean root = false;
		if (first) { 
			root = true; 
			first = false;
			if (state.getMovesMade() == 0 && maxPlayer == X) {
				return 1;
			}
			// See if a single move will lead to a win for maxPlayer
			int winAt = state.whereToWin(maxPlayer);
			if (winAt != 0) {
				String pstr = (maxPlayer == X) ? "X" : "O";
				System.out.println(pstr + " can win at " + winAt + "!");
				return winAt;
			}
			// See if a single move could lead to a win for the opponent, and block it
			int blockAt = state.whereToBlock(maxPlayer*-1);
			if (blockAt != 0) {
				return blockAt;
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

			
			
			first = true;

			//b.showBoard();
			if (player == O) {
				movePos = Minimax(b, player);
			} else {
				System.out.println("Enter a position (1-9)");
				movePos = sc.nextInt();
			}
			System.out.println("Minimax eval...");
			b.showBoard();
			System.out.println("Minimax chose: " + movePos);

			boolean moved = b.move(movePos);
			if (!moved) { 
				System.out.println("Error!  " +  movePos);
			}
			b.showBoard();
			
			
			
			int winner = b.whoWon();
			if (winner != 0) {
				if (winner == X) { 	System.out.println("X Wins!\t Took: " + turn + " Moves.");
				} else 				System.out.println("O Wins!\t Took: " + turn + " Moves.");
				
				break;
			}
			if (turn == 9) {
				System.out.println("It's a draw!");
			}
			
			// wait
			//System.out.println("Waiting for a signal...");
			//String go = sc.next();
		}
		
		b.showBoard();
		
	}

}
