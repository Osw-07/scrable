package com.bluenile.scrabble.service;

import com.bluenile.scrabble.dao.ScrabbleDao;
import com.bluenile.scrabble.model.ScrabbleWord;

import com.bluenile.scrabble.rule.ScrabbleRule;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.bluenile.scrabble.utils.StringUtils;

/**
 * This class is responsible to generate all Scrabble words based on the input
 * string provided by the client of the service. ScrabbleService has a
 * scrabbleDao property to delegate it the data manipulation. The @Service 
 * annotation is used in this class because provides the Scrabble business
 * functionality.
 *
 * @version 1.0 30 December 2020
 * @author Oswaldo_Gomez
 */
@Service
public class ScrabbleService {
		
	private final ScrabbleDao wordDao;
	/**
	 * Creates a scrableService and sets the wordDao property, so that the
	 * Service can delegate the data manipulation to ScrabbleDao.
	 * @Autowired annotation allows to resolve and inject collaborating
	 * dependency wordDao instance to scrabbleService constructor.
	 * 
	 * @param wordDao 		this property is created to have data access to
	 *  					the word list.
	 */
	@Autowired
	public ScrabbleService(@Qualifier("text")ScrabbleDao wordDao) {
		super();
		this.wordDao = wordDao;
	}

	/**
	 * Generates the scrabble words list based on the inputString's chars.
	 * getWords() method is invoked to get access to the list of words, then
	 * gets the ones that match with the chars within the input string and set
	 * the score to each word. Finally the method returns the filtered scrabble
	 * word list with their respective scores to the client.
	 * 
	 * @param inputString	this parameter takes the input string provided by
	 * 						the client of the service. Empty value and special
	 * 						characters are validated, also the string is
	 * 						changed to lower case.
	 * @returns				this method returns the scrabble words list that
	 * 						match with the inputString's characters.
	 */
	public List<ScrabbleWord> getScrabbleWords(String inputString) {
		
		 /**
		  * Declares the scrabbleWords result list for the objects that
		  * accomplish with the rules. This result list will be send to the
		  * client.
		  */
		List<ScrabbleWord> scrabbleWords = new ArrayList<>();
		
		/** Clears the scrabbleWords list*/
		scrabbleWords.clear();
		
		if (inputString != null) {
			
			/** 
			 * Changes inputString to lower case, clears space characters at the
			 * beginning and the end of the inputString:
			 */
			inputString= inputString.toLowerCase().trim();
			
			/** 
			* Gets the word list from Dao this list is already filtered
			* by the ones that are no empty and their length is less or equal to
			* the inputString length.
			*/
			List<ScrabbleWord> WordList = wordDao.getWords(inputString);

			/** 
			* Declares and initializes isGood flag to mark the chars that
			* accomplished the Scrabble conditions.
			*/
			boolean isGood = false;
			
			/** Scans the Words list word by word.*/
			for (ScrabbleWord word : WordList) {
				
				/** 
				* Declares and initializes the totalScore to score the words
				* marked as isGood = true.
				*/
				int totalScore = 0;
				
				/** Scans each word in the Word list character by character.*/
				for (char Wordchar : word.getWord().toCharArray()) {
					
				/**
				* Validates if: 
				* the word's char is contained in the inputString 
				* the word's char is not repeated more than in the inputString.
				* the word has special characters.
				*/
				if (StringUtils.countCharInString(inputString, Wordchar) > 0 &&
						StringUtils.countCharInString(inputString, Wordchar) >= 
						StringUtils.countCharInString(word.getWord(), Wordchar) &&
						inputString.matches("[a-z]+")) {
						
					/** Marks the char as isGood = true*/
					isGood = true;
						
					/** 
					* Scores the char by invoking the getScore() static 
					* method from ScrabbleRule class then sums it to the
					* total score.
					*/
					totalScore = totalScore + ScrabbleRule.getScore(Wordchar);
				}else {
						
					/** 
					* The char is rejected, marked as isGood = false and
					* continues with the next word - break sentence-.
					*/
					isGood = false;
					break;
				}
			}
				
			/** If all chars are good in the word*/
			if (isGood) {
					
				/** Set the total score to the word*/
				word.setScore(totalScore);
					
				/** Add the word to the result list*/
				scrabbleWords.add(word);
			}
		}
	}
		/**
		 * If the special and empty characters conditions fails: 
		 * Returns an empty list
		 * If not:
		 * Returns the scrabble words list.
		 */
		return scrabbleWords;
	}	
}