package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class NineBoardTwoP{
	static final int BLANK = 0;
	static final int X = 1;
	static final int O = -1;
	static int nextBoard = 0;
	static boolean pick = true;
	public static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		BigBoard bb = new BigBoard();
		
		Random r = new Random();
		
		for (int turn = 0; turn <= 81; turn++) {
			int p = (int) (Math.pow(-1, turn)); // track whose turn it is. 1 for X, -1 for O.
			bb.printBigBoard();
			
			if (nextBoard == 0 || pick) {
				System.err.println("Pick a board to move in. (1-9): ");
				nextBoard = sc.nextInt();
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
			System.err.println("Pick a spot to move. (1-9): ");
			int s = sc.nextInt();
			
			try {
				
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

			//b.showBoard();

			int winner = b.whoWon();
			if (winner != 0) {
				//System.err.println("woo!");
				if (winner == X) { 	System.err.println("X Wins!\t Took: " + turn + " Moves.");
				} else 				System.err.println("O Wins!\t Took: " + turn + " Moves.");
				
				break;
			}
			if (b.isTerminal()) {
				System.err.println("Board " + nextBoard + " is now in a draw!");
				pick = true;
			}
			if (turn == 80) {
				System.err.println("It's a draw!");
			}
			
			nextBoard = s;
		}
		
		bb.printBigBoard();;
		System.err.println("That's the game. Thanks for playing!");

	}

}
