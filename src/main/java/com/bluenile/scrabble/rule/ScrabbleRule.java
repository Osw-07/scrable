package com.bluenile.scrabble.rule;

/**
 * This class is responsible to define the set of scores applied to the
 * Scrabble game.
 * 
 * @version 1.0 30 December 2020
 * @author Oswaldo_Gomez
 */
public class ScrabbleRule {

	/** 
	 * Declares a ScrabbleScore enum to set the scores for each char of the
	 * words.
	 */
	enum ScrabbleScore {
		
		/** Defines strings made up of scored chars*/
		aeilnorstu(1),dg(2),bcmp(3),fhvwy(4),k(5),jx(8),qz(10);
		
		/** Declares the score property*/
		private int score;
		
		/** Set the score property in the constructor*/
		ScrabbleScore(int score) {this.score=score;}
		
		public int getScore() { return this.score;}
	}
	
	/**
	 * This method takes a char to score it based on the scores defined in the
	 * ScrabbleSocre enum.
	 * 
	 * @param inputChar		char to be scored
	 * @return				returns the score integer value related to the
	 * 						input char
	 */
	public static int getScore(char inputChar) {
		
		/** Declares and initializes the score variable*/
		int score = 0;
		
		/** 
		 * Validates if the char is within any of the string defined in the
		 * enum, if so the score will take the defined score for that string.
		 * */
		if (ScrabbleScore.aeilnorstu.toString().indexOf(inputChar) > -1) {
			score = ScrabbleScore.aeilnorstu.getScore();
		}else if(ScrabbleScore.dg.toString().indexOf(inputChar) > -1) {
			score = ScrabbleScore.dg.getScore();
		}else if(ScrabbleScore.bcmp.toString().indexOf(inputChar) > -1) {
			score = ScrabbleScore.bcmp.getScore();
		}else if(ScrabbleScore.fhvwy.toString().indexOf(inputChar) > -1) {
			score = ScrabbleScore.fhvwy.getScore();
		}else if(ScrabbleScore.k.toString().indexOf(inputChar) > -1) {
			score = ScrabbleScore.k.getScore();
		}else if(ScrabbleScore.jx.toString().indexOf(inputChar) > -1) {
			score = ScrabbleScore.jx.getScore();
		}else if(ScrabbleScore.qz.toString().indexOf(inputChar) > -1) {
			score = ScrabbleScore.qz.getScore();
		}
		
		/** Returns the score value defined for that input char.*/
		return score;
	}
}
