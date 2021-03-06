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
	static final int BLOCK = 15;
	static final int TWOS = 15;
	static final int ADVANTAGE = 6;

	static int curTurn = 0;
	static boolean first = false;
	static BigBoard bb;
	
	static Timer time = new Timer("Calc");
	static long startTime = System.currentTimeMillis();
	
	public static Scanner sc = new Scanner(System.in);

	static boolean timeOut() { // Says if time limit was reached
		long timeLimit = 3000; // Limit in milliseconds 
		//System.err.println((System.currentTimeMillis() - startTime));
		return ((System.currentTimeMillis() - startTime) > timeLimit);
	}
	
	// Save memory
	static int[] corners = new int[] {9,7,3,1};
	static int[] other = new int[] {2,4,5,6,8};
	
	// Heuristics
	public static int Minimax(Board state, int maxPlayer, int turnNum) {
		int score = 0;
		boolean root = false;
		if (first) {
			if (pick) {
				Random r = new Random();
				int rB = r.nextInt(9) + 1;
				return rB; 
			}
			
			startTime = System.currentTimeMillis();
			turnNum = curTurn;
			root = true; 
			first = false;
			// See if a single move will lead to a win for maxPlayer
			int winAt = state.whereToWin(maxPlayer);
			if (winAt != 0) {
				String pstr = (maxPlayer == X) ? "X" : "O";
				System.err.println(pstr + " can win at " + winAt + "!");
				return winAt;
			}
		}

		// If in a board with no moves, make opponent move in it
		if (state.getMovesMade() == 0) {
			int pos = state.getPosition();
			return pos;
		
		}

		if (state.isTerminal() || turnNum > 80) { // if terminal, return the score
			//System.err.println("Term!");
			int winner = state.whoWon();
			if (winner == 0) { return DRAW; }
			else {
				if (winner == maxPlayer) { return WIN; }
				else { return LOSE; }
			}
		}

		int[] actions = state.emptySquares();

		if (actions == null) {
			System.err.println("No actions!");
			return score;
		}
		
		int optimalPos = actions[actions.length-1];

		int minPos = actions[actions.length-1];
		int maxPos = actions[actions.length-1];
		int minScore = 0;
		int maxScore = 0;
		int curScore = score;

		for (int act: actions) { // Evaluate for all possible actions
			Board s1 = state; // this mini board
			s1 = s1.result(act);
			if (s1 == null) {
				continue;
			}

			//System.err.println("Evaluating position " + act + " for turn " + turnNum);
			
			// Stop going deeper after some number of turns past curTurn
			// Turn 1: 5. Turn 6: 9. Turn 21: 19. Turn 46+: to the endgame
			if (turnNum - curTurn <= 5 + Math.floor(turnNum / 1.5)) {
				Board s2 = bb.getBoard(act); // next mini board
				
				curScore = Minimax(s2, maxPlayer, ++ turnNum);
				curScore += s2.playerTotal(maxPlayer) * ADVANTAGE; // add points if we have more, remove if they do
				if (s2.whereToBlock(-1*maxPlayer) != 0) { // if we could lose on next board, factor this in
					curScore += LOSE;
				}
				if (s2.whereToWin(maxPlayer) != 0) { // if we could win on next board, factor this in
					curScore -= BLOCK;
				}
			} 

			curScore += s1.numTwos(maxPlayer) * TWOS; // add points for each two on this board
			
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
				System.err.println("Time is up!");
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
			System.err.println("\n\tRoot: " + score + "\n");
			System.err.println("Evaluation time: " + (System.currentTimeMillis() - startTime) + " ms");
			return optimalPos;
		}
		
		actions = null; // save memory
		return score;
	}
	
	/** ====================================================================================== **/
	/** ===================================== MAIN =========================================== **/
	/** ====================================================================================== **/

	public static void main(String[] args) {
		boolean oIsHuman = false;
		
		while(true) {

			bb = new BigBoard();
			boolean AIvsHuman = true;
			boolean AIvsAI = !AIvsHuman;


			System.err.println("Would you like to be X or O?");
			String user = sc.next();
			if (user.equalsIgnoreCase("x")) {
				oIsHuman = false;
			} else {
				oIsHuman = true;
			}

			for (int turn = 0; turn <= 81; turn++) {
				curTurn = turn;
				int p = (int) (Math.pow(-1, turn)); // track whose turn it is. 1 for X, -1 for O.

				first = true;

				if (nextBoard == 0 || pick) {
					System.err.println("Pick a board to move in. (1-9): ");
					if (AIvsAI || oIsHuman) nextBoard = Minimax(new Board(), p, curTurn);
					else nextBoard = sc.nextInt();
					pick = false;
				}

				Board b = bb.getBoard(nextBoard);
				while (b.isTerminal()) {
					System.err.println("That board is filled. Try again! (1-9): ");
					nextBoard = sc.nextInt();
					b = bb.getBoard(nextBoard);
				}
				String pstr = (p == X) ? "X" : "O";
				System.err.println(pstr + "'s turn, playing in board " + nextBoard);

				int s = 1;

				if (AIvsAI) {
					boolean m1vm2 = true;
					if (m1vm2) {
						s = (p == X) ? Minimax(b, p, curTurn) : Minimax(b, p, curTurn);
					}
					else {
						s = Minimax(b, p, curTurn);
					}
					System.err.println("Minimax chose: " + s);
				}
				else {
					if (oIsHuman) {
						if (p == X) {
							s = Minimax(b, p, curTurn);
							System.err.println("Minimax chose: " + s);
						}
						else {
							System.err.println("Pick a spot to move. (1-9): ");
							s = sc.nextInt();
							if (s != nextBoard) {
								System.err.println("Must play in board " + nextBoard);
								System.err.println("Enter which position you want to play in: ");
							}
							s = sc.nextInt();
						}
					} else {
						if (p == O) {
							s = Minimax(b, p, curTurn);
							System.err.println("Minimax chose: " + s);
						}
						else {
							System.err.println("Pick a spot to move. (1-9): ");
							s = sc.nextInt();
							if (s != nextBoard) {
								System.err.println("Must play in board " + nextBoard);
								System.err.println("Enter which position you want to play in: ");
							}
							s = sc.nextInt();
						}
					}
				}


				try {
					//int p = (int) (Math.pow(-1, turn)); // track whose turn it is. 1 for X, -1 for O.
					boolean moved = bb.move(nextBoard, s);
					while (!moved) {
						System.err.println("Invalid! That's already taken. Try again. (1-9): ");
						s = sc.nextInt();
						moved = bb.move(nextBoard, s);
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}

				int winner = b.whoWon();
				if (winner != 0) {
					//System.err.println("woo!");
					if (winner == X) { 	System.err.println("\nX Wins!\t Took: " + turn + " Moves.");
					} else 				System.err.println("\nO Wins!\t Took: " + turn + " Moves.");

					break;
				}
				if (b.isTerminal()) {
					System.err.println("Board " + nextBoard + " is now in a draw!");
					pick = true;
				}
				if (turn == 80) {
					System.err.println("It's a draw!");
				}

				bb.printBigBoard();

				nextBoard = s;

			}

			bb.printBigBoard();;
			System.err.println("That's the game. Thanks for playing!");
		
		}
		
		//System.exit(0); // Prevents the VM from holding onto all its memory
		// Failing this, running my program consumes up to 500 MB of memory every time

	}

}
