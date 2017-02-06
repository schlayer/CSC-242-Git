package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUI extends JFrame {

	JFrame frame = new JFrame("Tic Tac Toe");                    //Global frame and grid button variables 
	Board gameBoard = new Board();
	
	int moveCounter = 9;
	boolean gameWon = false;


	public GUI() {
		super();
		frame.setSize(350, 450);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);        //Setting dimension of Jframe and setting parameters
		frame.setVisible(true);
		frame.setResizable(false);

	}

	private void init() {            //Initialize tic tac toe game board
	
		JPanel mainPanel = new JPanel(new BorderLayout());         //create main panel container to put layer others on top
		JPanel gameBoard = new JPanel(new GridLayout(3,3));        //Create two more panels with layouts for buttons

		frame.add(mainPanel);                                         //add main container panel to frame

		mainPanel.setPreferredSize(new Dimension(325,425));
		gameBoard.setPreferredSize(new Dimension(300,300));

		//mainPanel.add(menu, BorderLayout.NORTH);                   //Add two panels to the main container panel             
		mainPanel.add(gameBoard, BorderLayout.SOUTH);

		for(int i = 0; i < 3; i++) {                    //Create grid for tic tac toe game

			for(int j = 0; j < 3; j++) {
				
				


			
			}
			
		}

	}

	public static void main(String[] args) {
		GUI g = new GUI();         //main method and instantiating tic tac object and calling initialize function
		g.init();
	}

}