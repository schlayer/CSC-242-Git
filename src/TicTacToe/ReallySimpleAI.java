package TicTacToe;

import java.util.Random;

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
	
	static int AImove = 1;
	static int curTurn = 0;
	
	// Greedy local search approach to TicTacToe
	public static void localSearch(int totalScore, Board curState, int lastMove) {
		if (curTurn == 0) {
			AImove = 1;
			return;
		}
	
		int[] possibleMoves = curState.emptySquares();
		Board[] possible = new Board[possibleMoves.length];
		int[] scores = new int[possibleMoves.length];
		
		int minmax = (int) Math.pow(-1, curState.movesMade); // minmax tells which player's side we're on
		int blockPos = curState.whereToBlock(minmax*-1);
		if (blockPos != 0) {
			AImove = blockPos;
			return;
		}
			
		System.out.println("Checking for " + minmax);
		
		// Check all possible moves
		int index = 0;
		for (int pos: possibleMoves) {
			System.out.println("test");
			possible[index] = curState.result(minmax, pos);
			if (possible[index].whoWon() == minmax) {
				AImove = pos;
				return;
			}
			
			index ++;
		}
		
		index = 0;
		for (Board b: possible) {
			int curScore = totalScore;
			if (b.whoWon() == -1*minmax) {
				curScore += LOSE;
			} 
			
			curScore += TWOS_VAL * b.numTwos(minmax);
			
			scores[index] = curScore;
			
			index ++;
		}
		
		// find best outcome...
		int maxInd = 0;
		int maxScore = -1000;
		for (int ind = 0; ind < possibleMoves.length; ind++) {
			if (scores[ind] > maxScore) {
				maxInd = ind;
				maxScore = scores[ind];
			}
		}

		AImove = possibleMoves[maxInd];
		return ;
	}
	
	
	public static void main(String[] args) {
		Board b = new Board();
		
		//b.showBoard();
		
		Random r = new Random();
		
		for (int turn = 0; turn <= 8; turn++) {
			System.out.println("Loop");
			int[] actions = b.emptySquares();
			curTurn = turn;
			
			int a = r.nextInt(actions.length);
			
			
			int player = (int) Math.pow(-1, turn);
			int movePos = actions[a];
			
			if (player == X) {
				Board c = b;
				localSearch(0, c, 0);
				movePos = AImove;
			} else {
				System.out.println("Player O moves randomly....");
			}
			
			boolean moved = b.move(player, movePos);
			if (!moved) { 
				System.out.println("Error!  " +  movePos);
				turn --;
				//b.clearSquare(movePos);
				//break;
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
