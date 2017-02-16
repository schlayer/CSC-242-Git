package Lectures;

public class Lec08 {

	/**
	
	States aren't just a sort of 'black box' as we've been treating them. 
		They require a lot of code! 
		No design process decided, lots of room for error. 
		Heuristics are problem-specific.
	
	
	
	Ways of representing 'State': Representation
		Impose a structure on the State
		successor generation and goal tests become problem and domain independent
		Even heuristics that may be independent of the problem.
	
	EX) Color a map so no two adjacent regions have the same color. 
	
	Color WA, NT, Q, NSW, V, SA, T
	enum Color = red, green, blue
	State: assignment of colors to regions
	Action: pick an unassigned region and color it
	Goal test: use if/else tree to determine if adjacent regions are the same

	n!*d^n possible States
	How to generalize this? 
	Constraint Satisfaction Problem generalization!
	
		X: Set of variables
			Problem solving state is ..
		D: set of Domains; each color in this case...
		C: set of Constraints; the constraints on possible states, those that are a goal state, etc. 
		Factored representation
			Splits a state into variables that can have values
			Factored states can be more or less similar
			Can also represent comparisons
	
	CSP terminology:
		Assignment assigns a value to a variable
		Consistent: doesn't violate any constraints of the goal
		Partial: some variables assigned
		Complete: every variable assigned
		Solution: consistent, complete assignment
	
	Constraints: 
		Unary constraint: affect one variable
		Binary: relationship between two variables (like no adjacent colors)
		"Global" constraint: more than two variables (like value v is between X and Y
			Globals can be reduced to binary constraints (maybe inefficient)
	
	Search for CSPs involves assigning unassigned variables and 
	checking whether or not constraints are met. Tree Size O(n!*d^n)
	However, the order of assignment shouldn't matter.
		
	CSPs are Commutative: can reach sane assignments without the same path.
	Need only consider assignment to one variable at a time in each node
	Now: size is just O(d^n)
	
	If we get to a a point where we cannot be consistent with constraints, 
	we will never get a consistent assignment. Thus, we prune the search!
		 No legal choice, empty domain, inconsistent partial assignment, can't extend to solution
		 
	Backtracking search: like DFS but when we reach a failure, we back up and try another assignment.
	
	 Heuristics for CSPs: 
	 min remaining values
	 degree heuristic
	 least constraining value
	
	Constraint Propagation: Take info that is implicit and make it explicit. 
		Reduces space of assignments left to search
	
	Node Consistency: every possible value of every variable is consistent with unary constraints
		Apply all unary constraints
		If problem is not inconsistent, we can always propagate unary constraints at start
		After that, we can ignore them
		Complexity: Each variable, each value, each unary constraint
	Arc Consistency: binary constraints
		Check if every arc connects two nodes which are satisfying binary constraints
		Make the arc consistent by checking its variables, assigning where possible/needed
			X with respect to Y, and Y with respect to X
			with n variables, domain size <= d, c constraints for arcs
			each arc can be inserted at most d times 
			checking ne takes O(d^2) so we have O(n*d^3)
		
	
	Inconsistency: 	
		cannot solve problem due to empty domains , etc
	
	Every time an assignment is made, we can propagate binary constraints
	
	Interleaving Search and inference
	
	
	
	 */
}
