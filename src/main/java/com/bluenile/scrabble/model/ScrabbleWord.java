package com.bluenile.scrabble.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model Class for manipulating any word that comes from the data source.
 * This class is composed by the word and the score related with that word.
 *  
 * @version 1.0 30 December 2020
 * @author Oswaldo_Gomez
 */
public class ScrabbleWord {

	/** Word from the data source*/
    private String word;
    
    /** Score related with the word*/
    private int score;
    
    /**
     * Creates a scrabbleWord object composed by the word and its related score.
     * The model class will have a Json format handled by '\@JsonProperty 
     * annotation defined in the class constructor when setting its properties.
     * 
     * @param word 			word that comes from the data source.
     * @param score 		integer value to set the score to the word.
     */
    public ScrabbleWord(@JsonProperty("word") String word, 
    			@JsonProperty("score") int score) {
        this.word = word;
        this.score = score;
    }
    
    public String getWord() {
        return word;
    }
    
	public int getScore() {
		return score;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
