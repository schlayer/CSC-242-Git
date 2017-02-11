package TicTacToe;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

public class NineBoardAI{
	static int nextBoard = 0;
	static boolean pick = true;
	
	static final int BLANK = 0;
	static final int X = 1;
	static final int O = -1;
	static final int MAX = 1;
	static final int MIN = -1;
	// Points
	static final int WIN = 100;
	static final int LOSE = -100;
	static final int DRAW = 0;
	static final int BLOCK = 25;
	static final int TWOS = 5;
	static final int ADVANTAGE = 4;

	static int curTurn = 0;
	static boolean first = false;
	static BigBoard bb;
	
	static Timer time = new Timer("Calc");
	static long startTime = System.currentTimeMillis();
	
	public static Scanner sc = new Scanner(System.in);

	public static boolean timeOut() { // Says if time limit was reached
		long timeLimit = 10000; // Limit in milliseconds 
		return ((System.currentTimeMillis() - startTime) > timeLimit);
	}
	
	public static int Minimax(Board state, int maxPlayer, int turnNum) {
		
		int score = 0;
		boolean root = false;
		if (first) {
			startTime = System.currentTimeMillis();
			turnNum = curTurn;
			root = true; 
			first = false;
			// See if a single move will lead to a win for maxPlayer
			int winAt = state.whereToWin(maxPlayer);
			if (winAt != 0) {
				String pstr = (maxPlayer == X) ? "X" : "O";
				System.out.println(pstr + " can win at " + winAt + "!");
				return winAt;
			}
		}
		
		if (state.getMovesMade() == 0) {
			int[] corners = new int[] {9,7,3,1};
			for (int c: corners) {
				Board k = bb.getBoard(c);
				if (k.position == c) { continue; }
				if (k.getMovesMade() == 0) {
					return c;
				}
			}
			int[] other = new int[] {2,4,5,6,8};
			for (int o: other) {
				Board k = bb.getBoard(o);
				if (k.position == o) { continue; }
				if (k.getMovesMade() == 0) {
					return o;
				}
			}
		}
		
		if (state.isTerminal() || turnNum >= 80) { // if terminal, return the score
			System.out.println("Term!");
			int winner = state.whoWon();
			if (winner == 0) { return DRAW; }
			else {
				if (winner == maxPlayer) { return WIN; }
				else { return LOSE; }
			}
		}

		int[] actions = state.emptySquares();

		int optimalPos = actions[actions.length-1];

		if (actions == null) {
			System.out.println("No actions!");
			return score;
		}

		int minPos = actions[actions.length-1];
		int maxPos = actions[actions.length-1];
		int minScore = 1000;
		int maxScore = -1000;
		int curScore = score;

		for (int act: actions) { // Evaluate for all possible actions
			Board s1 = state; // this mini board
			Board s2 = bb.getBoard(act); // next mini board
			s1 = s1.result(act);
			if (s1 == null) {
				continue;
			}

			System.out.println("Evaluating position " + act + " for turn " + s1.getMovesMade());
			
			curScore = Minimax(s1, maxPlayer, ++ turnNum);
			
			
			curScore += s1.numTwos(maxPlayer) * TWOS; // add points for each two on this board
			curScore += s2.playerTotal(maxPlayer) * ADVANTAGE; // add points if we have more, remove if they do
			if (s2.whereToBlock(-1*maxPlayer) != 0) { // if we could lose on next board, factor this in
				curScore += LOSE;
			}
			if (s2.whereToWin(maxPlayer) != 0) { // if we could win on next board, factor this in
				curScore -= BLOCK;
			}
			
			
			//s2.showBoard();
			if (curScore > maxScore) { // Update max
				maxPos = act;
				maxScore = curScore;
			}
			if (curScore < minScore) { // Update min
				minPos = act;
				minScore = curScore;
			}
			
			s1.clearSquare(act);
			
			if (timeOut()) {
				System.out.println("Time is up!");
				break;
			}
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
			System.out.println("Evaluation time: " + (System.currentTimeMillis() - startTime) + " ms");
			return optimalPos;
		}

		// Score is better for fewer moves to win
		//score = score + (9 - state.getMovesMade());

		return score;
	}

	/** ====================================================================================== **/

	public static void main(String[] args) {
		bb = new BigBoard();
		boolean AIvsHuman = false;
		boolean AIvsAI = !AIvsHuman;
		
		//bb.showAllSmall();
		//bb.printBigBoard();
		
		Random r = new Random();
		
		for (int turn = 0; turn <= 81; turn++) {
			curTurn = turn;
			int p = (int) (Math.pow(-1, turn)); // track whose turn it is. 1 for X, -1 for O.
			//bb.printBigBoard();
			first = true;
			
			if (nextBoard == 0 || pick) {
				System.out.println("Pick a board to move in. (1-9): ");
				nextBoard = sc.nextInt();
				pick = false;
			}
			
			Board b = bb.getBoard(nextBoard);
			while (b.isTerminal()) {
				System.out.println("That board is filled. Try again! (1-9): ");
				nextBoard = sc.nextInt();
				b = bb.getBoard(nextBoard);
			}
			String pstr = (p == X) ? "X" : "O";
			System.out.println(pstr + "'s turn, playing in board " + nextBoard);
			System.out.println("Pick a spot to move. (1-9): ");
			int s = 1;

			if (AIvsAI) {
				s = Minimax(b, p, curTurn);
			}
			else {
				if (p == O) {
					s = Minimax(b, p, curTurn);
				}
				else {
					System.out.println("Enter a move");
					s = sc.nextInt();
				}
			}
			
			
			try {
				//int p = (int) (Math.pow(-1, turn)); // track whose turn it is. 1 for X, -1 for O.
				boolean moved = bb.move(nextBoard, s);
				while (!moved) {
					System.out.println("Invalid! That's already taken. Try again. (1-9): ");
					s = sc.nextInt();
					moved = bb.move(nextBoard, s);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			int winner = b.whoWon();
			if (winner != 0) {
				//System.out.println("woo!");
				if (winner == X) { 	System.out.println("X Wins!\t Took: " + turn + " Moves.");
				} else 				System.out.println("O Wins!\t Took: " + turn + " Moves.");
				
				break;
			}
			if (b.isTerminal()) {
				System.out.println("Board " + nextBoard + " is now in a draw!");
				pick = true;
			}
			if (turn == 80) {
				System.out.println("It's a draw!");
			}
			
			bb.printBigBoard();
			
			nextBoard = s;
			
			boolean wait = false;
			if (wait) {
				// wait
				System.out.println("Waiting for a signal...");
				String go = sc.next();
			}
		}
		
		bb.printBigBoard();;
		System.out.println("That's the game. Thanks for playing!");

	}

}
