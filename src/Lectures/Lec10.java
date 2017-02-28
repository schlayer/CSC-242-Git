package Lectures;

public class Lec10 {

	
	/*
	Hunt the Wumpus: in a cave with no knowledge of the layout, 
	pits in some rooms, wumpus in one room, gold in one room. 
	Must get back to start after killing wumpus
	
	Representation: NxN array of rooms 
		For each room: has pit? has gold: has wumpus? (booleans)
		
		Boolean [][][] env = [n][n][3];
		
		final int WUMPUS = 2;
		final int PIT = 1;
		final int GOLD = 0;
		
		Boolean can be null, so we have true or false or null for unknown
		
		Represent location of agent (two ints position?)
		Orientation can be (N, S, E, W)
		
		Sensors (percepts)
			* stench, breeze, glitter, bump, scream
		Need to store those too
		
		
		This is able to be formulated as a Boolean CSP
		
		A Programming Language for Knowledge
			Syntax: what counts as a well-formed statement, formula, sentence, or program
			Semantics: what these statements, formulas, or programs mean	
			* !, &&, ||, if/then, iff logic rules
		
		Propositional Logic

	 */
	/**
	 Propositional Logic - Take what is implicit in what we know and make it explicit.
	 	Truth preserving: rewrite sentences to preserve truth. 
	 
	 Modus Ponens: if A implies B and A is given, then B can be inferred
	 Sound Inference Rule: If A logically implies B, A entails B
	 Completeness: If A entails B, B is implied by A (not always true!)
	 	Derives logically entailed sentences
	 
	 Following inference rules, we are guaranteed to find only things that are true. 
	 
	 
	 Propositional Inference as Search - use inference rules
	 I.E. if a or b or c or d is true, but we know c is not, then we know either a or b or d is true.
	 
	 >> Read book... <<
	 
	 
	 
	 */
}
