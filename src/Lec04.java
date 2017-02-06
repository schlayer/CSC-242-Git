
public class Lec04 {

	public static void main(String[] args) {
		
		System.out.println("Hello World!");
		
		
		/*
		Strategies for performing the search!!!
		
		Graph search:
		We wish to choose a frontier node each time we go through the loop, so the question is: which? 
		We ignore all repeats of previous states...
		
		Uninformed Strategies:
			Breadth-First Search is one option - expand shallowest unexpanded node. Use First In First Out (FIFO) queue
			
			We need to focus on not just time/space complexity, but completeness and optimality. 
			(Whether it gets to a solution, and whether it finds a best-cost solution)
			
			BFS will always get the shallowest solution but maybe not the lowest cost. However, it is decently close for many cases. 
			
			Time complexity and space complexity is O(b^d) where b is branching factor and d is the depth. This is awful.		
			This is awful...
			
			
			Depth-First Search is another option - expand deepest unexpanded. Use a stack (LIFO)
			With infinite search-space, it may not even complete the problem unless using graph search. 
			It definitely will not likely find the shallowest solution
			Time complexity and space is O(b^m) for graph search version, but tree search version is O(bm) which is better
			
			
			Iterative Deepening: Preferred for large search space with unknown depth
			do DFS to depth d until a goal is found, start from scratch on next branch if none found.
			This is time cost O(b^d) now, with the completeness and optimality of BFS, and space complexity of DFS.
			
		Informed Strategies: (heuristic)
			Not guaranteed to work but we can show that it's better than nothing. 
			Cost of cheapest path from n to a goal node is f(n) - not guaranteed to be successful at all. 
			This is a Greedy Best-First Search.
			
			Now we need to account for variations from cheapest path. 
			A* search. If h(n) is admissible - never overestimates a true cost - it is going to be complete and optimal.
			However, still gives exponential time complexity and space complexity (though there is O^be where e depends on heuristic)
			Heuristic functions are a 'black art'. Really hard to find a great one. 
			
		
		*/
		/**
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
		
		Terminal value is the node value.
		
		**/
		
		
		
	}

}
