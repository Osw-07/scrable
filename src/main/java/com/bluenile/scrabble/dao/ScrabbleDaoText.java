package com.bluenile.scrabble.dao;

import com.bluenile.scrabble.model.ScrabbleWord;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Class that implements ScrabbleDao interface to define its own data access,
 * responsible to read the list of words from a text file and generate a List
 * of ScrabbleWord objects.
 * The @Repository annotation indicates that ScrabbleDaoText is a repository
 * for encapsulating storage, retrieval and search behavior which creates a
 * collection of scrabbleWords objects.
 * 
 * @version 1.0 30 December 2020
 * @author Oswaldo_Gomez
 */
@Repository("text")
public class ScrabbleDaoText implements ScrabbleDao {
	
	/** 
	 * Takes the file path were the words.txt file is located from
	 * application.properties*/
	 
	@Value("${filePath}")
	private String filePath;
	//private String filePath = "../scrabble/src/main/resources/words.txt";

	/** 
	 * List of ScrabbleWords model class to load the list of words from the 
	 * file.
	 */
	private static List<ScrabbleWord> listOfWords = new ArrayList<>();
	
	/**
	 * The implementation of this method opens the file system text file, reads
	 * the file line by line and stores the line in the readWord variable then
	 * creates an unqualified ScrabbleWord object and sets the word property to
	 * readWord and initializes the score property at zero if:<br>
	 * readWord length is greater than zero and<br>
	 * readWord length is less than or equal to inputString length.<br>
	 * If the List already exists then it is cleared. <br>
	 * Finally closes the File and returns the ScrabbleWord objects List
	 * with the objects that accomplish the condition.
	 */
    @Override
    public List<ScrabbleWord> getWords(String inputString) {
        try {
        	
        	/**  Clears the list.*/
        	ScrabbleDaoText.listOfWords.clear();
        	
        	/** Opens the file from the file system.*/
        	File dictionaryFile = new File(filePath);
        	FileReader fileReader = new FileReader(dictionaryFile);
        	BufferedReader reader = new BufferedReader(fileReader);
        	
        	/**
        	 *  Declares and initializes the readWord variable used to get each
        	 *  line of the file.
        	 */     	
        	String readWord = null;
        	
        	/**
        	 * Scans the file line by line and creates the List with the words
        	 * that accomplish the condition.
        	 */
        	while ((readWord = reader.readLine()) != null) {
        		
        		/**
        		 * If the line is not empty - readWord.length() > 0 - and
        		 * its length is equal or less to the imputString length 
        		 * parameter then adds the line - readWord - to the list.
        		 */
        		if ((readWord.length() > 0) &&
        				(readWord.length() <= inputString.length())) {
        			listOfWords.add(new ScrabbleWord(readWord,0));
        		}
        	}
        	
        	/** Close the file.*/
        	reader.close();
        } catch(Exception ex) {
        	//ex.printStackTrace();
        	throw new RuntimeException("No empty string expected");
        }
        
        /** 
         * Returns the list with the ScrabbleWord objects that accomplish the
         * condition.
         */
		return listOfWords;
    }
}
