package Lectures;

public class Lec06 {
	/*
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
	
	// For TTT: Change code to add score each time we go deeper, and return the total score from all levels explored
	// ONLY return when at a terminal state
	
	/**
	
	All previously discussed: Systematic search. 
		Enumerates paths from initial state
		Records what alternatives have been explored at each point in the path
		Returns optimal solution
	
	Local Search: when the only important thing is reaching a goal state; no need to keep track of the path. 
	
	8-Queens Problem - as state space search
		State - matrix of board positions
		Actions - placing a queen on a position or moving a queen somewhere else
		Initial - empty board
		Goal State - test to see if we've solved
		Step costs
		
	Local search:
		Start in initial state, select an action, then apply it and update
		Repeat until we reach a goal state
	
	*	Evaluates and modifies a small number of states,
	*	no recorded history of search
			Pro: Basically no memory usage
			Con: Very unlikely to get optimal solutions
				 Slow, large time cost, may never explore all possibilities (incomplete)
		
	Hill-Climbing Search
		Heuristic defines a 'landscape' so we have an idea of how good or bad the states are. 
		Your program tries to 'climb the highest mountain'
		Always tries to do what's best. (Greedy local search)	
		Can get stuck in a state where it cannot 'win' (finds solution 14% of the time)
			Looks for a global max but easily gets stuck in a local max
			(min if minimizing cost)
	
	Random Restart Strategy
		Just try random crap until we are at a solution
			Not very effective...
		OR: try an intelligent algorithm until it works, which is actually legit
		(Only works if you can use any initial state)
		
		Could try randomly picking actions that are 'better' than current state (stochastic hill climbing)
		
		
		
	 
	**/
}
