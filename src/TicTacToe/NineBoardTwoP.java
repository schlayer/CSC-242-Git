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
		
		//bb.showAllSmall();
		//bb.printBigBoard();
		
		Random r = new Random();
		
		for (int turn = 0; turn <= 81; turn++) {
			int p = (int) (Math.pow(-1, turn)); // track whose turn it is. 1 for X, -1 for O.
			bb.printBigBoard();
			
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
			int s = sc.nextInt();
			
			try {
				
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

			//b.showBoard();

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
			
			nextBoard = s;
		}
		
		bb.printBigBoard();;
		System.out.println("That's the game. Thanks for playing!");

	}

}
