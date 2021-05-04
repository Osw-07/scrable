package com.bluenile.scrabble.dao;

import java.util.List;
import com.bluenile.scrabble.model.ScrabbleWord;

/**
 * Interface defined to declare the behaviors for manipulating information from
 * a data source such as database, file system or any other data storage.
 * This interface must be implemented accordingly the needs of data access in 
 * the solution's context.
 * 
 * @author Oswaldo_Gomez
 * @version 1.0 
 * @since 30/12/2020
 */
public interface ScrabbleDao {
	
	/**
	 * This method reads the words from the data source and returns a list of words
	 * that are equal or less length to input word length.
	 *
	 * @param inputString	this parameter represents the input string provided by 
	 * 						the client of the service and it will be used to filter
	 * 						the list of words from the data source based on its 
	 * 						length.
	 * @return 				returns a list of model word objects based on the words
	 * 						read from the data source that are equal or less length 
	 * 						to the input string length.
	 */
     List<ScrabbleWord> getWords(String inputString);
     
}