package Lectures;

public class Lec05 {

	public static void main(String[] args) {
		
		System.out.println("Hello World!");
		
		
		/*
		Today: Games, Adversarial Search
		Games penalize inefficiency severely. Games force you to make a decision even when the optimal one is impossible. 
		
		Abstract Games:
		* Deterministic (no chance)
		* Non-Deterministic (chance games)
		Can have varied info
		* Perfect information - fully observable
		* Imperfect information - partially observable (cards cannot be seen, etc)
		* Zero-sum vs Arbitrary Utility
		
		Deterministic and perfect information: TicTacToe
			- How to find optimal moves, how to find good moves when time is limited
		
		minimax algorithm is impractical but calculates optimal move, exploring DFS all game states. 
		Will never lose but it will be kinda slow. Also, cannot really ever explore all states.
		
		
		H-Minimax - set a cutoff
			depth, time, 'Quiescence' 
			dynamic adjustment
			
			ch 5.4.2
		
			combine iterative deepening?
		
		time-quality tradeoff
		
		*/
		/**
		Games Cont. Adversarial Search
		Strategy - minimax algorithm. Turns knowledge about the end states into knowledge about the best moves at any state. 
		Hueristic Minimax - uses h(s) function at cutoff time, depth, etc
			Have to figure out how to evaluate the board.
		
		Summarizing Adversarial Search:
		 	Perfect info, Zero Sum games, can find optimal moves with minimax and with real life constraints,
		 	use a heuristic to actually find a good move without infinite time
		 
		 Monte-Carlo Tree Search - uses stats about what is a better strategy to determine the best moves to make (random search)
		 	Move Selection - Reduces branching factor (Policy)
			Position evaluation - reduces m (Value)
		AlphaGo used neural networks to learn all of this. 
		
		
		Try making minimax greedy! Look at lowest and highest utility seen thus far. A and B
			Assume that opponent will play the optimal move. 
			Thus we constrain the A value to this value (lowest). And we get B (highest among those).
			Allows an average of 4/3 improvement on depth reached
		
		===========================================================================================================================
		Non-Deterministic (Stochastic) Games: Moves depend on random elements
		Cannot build a tree since we don't know the possible future moves
			Incorporate Chance nodes - Max Chance Min Chance ... etc.
			
		Minimax can now try to base utility on the probabilities - averaging over possible outcomes.
		Expected Value: weighted average of possibilities
			sum of possible outcomes weighted by chance of occurrence
			what you expect to win in the long run
		
		Expecti-Minimax!
		But... O(b^m * n^m)
		
		Partially Observable - some is visible, some is not.
			Opponent state is hidden
			Battleship, Stratego
		
		Stochastic partial observability - random process and partial observability
			Poker, other card games
			
		Weighted Minimax - use a representative sample to approximate events from an underlying framework
		
			
		
		
		
		
		 */
		
		
		
	}

}
