package Lectures;

public class Lec11 {

	
	
	/*
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
	
	/**
	
	Model - possible world, set of sentences 
	Knowledge Base - the rules we are given?
	
	
			Past Propositional Logic
	Pros:	
		Declarative: Pl based on truth relations between sentences and possible worlds
		Expressive: represents partial information
		Compositional: meaning of a sentence is a simple function of the meaning of its parts
	Cons:
		Model checking takes exponential time - very inefficient
		Lacks the power to concisely describe environments with many objects
	
	
	Need to add something to actually represent general rules, since they need to be applied
	to an entire world.
	
	
	Logic 2.0: New language based on propositional logic that will allow us to say all these things
		Define entailment (what "follows from" what)
		Find inference rues allowing computation of consequences of knowledge
	
	
	'Rooms adjacent to pits are breezy.'
	'Socrates us a person. All people are mortals.'
	'Anybody's grandmother i either their mother's or their father's mother.'
	
	Objects, relations, functions. "Ontology" of things.
	
	Objects:
		nouns basically
	Relations:
		Unary relations/properties (breezy, mortal, red, round, prime... Adjectives
		n-ary: Brother to, bigger than, part of, inside, has color, owns, above
		Functions: "Single-valued" relations
	
	Block world:
		Objects: Blocks
		Relations: Being on, above, clear, on table
		Functions: Block above me
	
	===========================================================
	
	A Programming Language for Knowledge
	
	Constant Symbols denote/indicate objects (like letters/numbers)
	Predicate Symbols denote relations [Mortal(.), Above(.,.)]
	Function Symbols denote functions [mother(.), plus(.,.)]
		Arity - number of arguments
		
	
	Term 
	* Logical Expression that denotes an object
	* Constant symbol, or Function symbol and tuple of terms of correct arity
	
	
	Atomic Sentence
	* States a fact
	* Predicate symbol and tuple of terms of correct arity
	* Represents a FACT
	
	ex) On(a,b)  Brother(Seth, Michael)  Married(father(John), mother(Richard))
	
	Connectives - From propositional logic (all the logic operators)
	
	
	First-Order Predicate Logic: a model satisfies a sentence if it makes the sentence true
	Ontology:
	* Objects
	* Relations
	* Functions
	
	Interpretation
		Constant Symbols >> Objects (sigma) represent Omega_i
		Predicate Symbols >> Relation (pi_n)
		Function Symbols >> Function (phi_n)
	
	** Functions are typically One to One
	
	
	
	
	
	
	 */
}
