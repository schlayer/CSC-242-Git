package TicTacToe;
import java.util.Random;
import java.util.Scanner;

public class TwoPlayer {

	static final int BLANK = 0;
	static final int X = 1;
	static final int O = -1;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Board b = new Board();
		b.print = true;
		
		b.showBoard();
		
		Random r = new Random();
		
		for (int i = 0; i <= 8; i++) {
			
			System.err.println("Pick a spot to move. (1-9): ");
			int s = sc.nextInt();

			try {
				int p = (int) (Math.pow(-1, i)); // track whose turn it is. 1 for X, -1 for O.
				boolean moved = b.move(s);
				while (!moved) {
					System.err.println("Invalid! That's already taken. Try again. (1-9): ");
					s = sc.nextInt();
					moved = b.move(s);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

			b.showBoard();

			int winner = b.whoWon();
			if (winner != 0) {
				//System.err.println("woo!");
				if (winner == X) { 	System.err.println("X Wins!\t Took: " + i + " Moves.");
				} else 				System.err.println("O Wins!\t Took: " + i + " Moves.");
				
				break;
			}
			if (i == 8) {
				System.err.println("It's a draw!");
			}
		}
		
		b.showBoard();
		System.err.println("That's the game. Thanks for playing!");
		
	}
}
