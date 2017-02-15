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
				System.err.println(pstr + " can win at " + winAt + "!");
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
			System.err.println("Term!");
			int winner = state.whoWon();
			if (winner == 0) { return DRAW; }
			else {
				if (winner == maxPlayer) { return WIN; }
				else { return LOSE; }
			}
		}
		
		int[] actions = state.emptySquares();
		
		//System.err.println("Actions: ");
		//for (int a: actions) { System.err.print(a + " "); }
		//System.err.println("\n");
		
		int optimalPos = actions[actions.length-1];
		
		if (actions == null) {
			System.err.println("No actions!");
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
			
			System.err.println("Evaluating position " + act + " for turn " + s2.getMovesMade());
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
			System.err.println("\n\t\tRoot: RETURNING POS\n");
			//System.err.println("Root!");
			return optimalPos;
		}
		 
		// Score is better for fewer moves to win
		score = score + 9 - state.getMovesMade();
		
		return score;
	}
	
	public static Scanner sc = new Scanner(System.in); 
	
	
	public static void main(String[] args) {
		boolean oIsHuman = false;
		
		while(true){
			Board b = new Board();
			b.print = true;

			b.showBoard();
			System.err.println("Would you like to be X or O?");
			String user = sc.next();
			if (user.equalsIgnoreCase("x")) {
				oIsHuman = false;
			} else {
				oIsHuman = true;
			}

			Random r = new Random();



			for (int turn = 0; turn <= 8; turn++) {
				int[] actions = b.emptySquares();
				curTurn = turn;

				int a = r.nextInt(actions.length);

				int player = b.getCurrentPlayer();
				int movePos = actions[a];

				first = true;

				//b.showBoard();
				if (!oIsHuman) {
					if (player == O) {
						System.err.println("Minimax eval...");
						movePos = Minimax(b, player);
						System.err.println("Minimax chose: " + movePos);
						System.out.println(movePos);
					} else {
						System.err.println("Enter a position (1-9)");
						movePos = sc.nextInt();
					}
				} else {
					if (player == X) {
						System.err.println("Minimax eval...");
						movePos = Minimax(b, player);
						System.err.println("Minimax chose: " + movePos);
						System.out.println(movePos);
					} else {
						System.err.println("Enter a position (1-9)");
						movePos = sc.nextInt();
					}
				}

				boolean moved = b.move(movePos);
				if (!moved) { 
					System.err.println("Error!  " +  movePos);
				}
				b.showBoard();

				int winner = b.whoWon();
				if (winner != 0) {
					if (winner == X) { 	System.err.println("X Wins!\t Took: " + turn + " Moves.");
					} else 				System.err.println("O Wins!\t Took: " + turn + " Moves.");

					break;
				}
				if (turn == 9) {
					System.err.println("It's a draw!");
				}

			}

			b.showBoard();
			System.err.println("That's the game. Thanks for playing!");
			b.clearBoard();
		}
	}

}
