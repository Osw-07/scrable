package com.bluenile.scrabble;

import java.util.ArrayList;
import java.util.List;

import com.bluenile.scrabble.dao.ScrabbleDao;
import com.bluenile.scrabble.model.ScrabbleWord;

public class TestScrabbleDaoText implements ScrabbleDao {
	private static List<ScrabbleWord> listOfWords = new ArrayList<>();
	private static List<String> MockWords = new ArrayList<>();
	
	@Override
	public List<ScrabbleWord> getWords(String inputString) {
		try { 
			getMockWords();
			for (String mokOfWord : MockWords) {
				if ((mokOfWord.length() > 0) &&
						(mokOfWord.length() <= inputString.length())) {
					listOfWords.add(new ScrabbleWord(mokOfWord,0));
				}
			}
		}catch (Exception e){
			throw new RuntimeException("No empty string expected");
		}
		return listOfWords;
	}
	
	 public List<String> getMockWords() {
		MockWords.add("a");
		MockWords.add("aah");
		MockWords.add("aahed");
		MockWords.add("aahing");
		MockWords.add("aahs");
		MockWords.add("aardvark");
		MockWords.add("aardvarks");
		MockWords.add("aardwolf");
		MockWords.add("ab");
		MockWords.add("abaci");
		MockWords.add("aback");
		MockWords.add("abacus");
		MockWords.add("abacuses");
		MockWords.add("abaft");
		MockWords.add("abalone");
		MockWords.add("abalones");
		MockWords.add("abandon");
		MockWords.add("abandoned");
		MockWords.add("abandonedly");
		MockWords.add("abandonee");
		MockWords.add("abandoner");
		MockWords.add("abandoners");
		MockWords.add("abandoning");
		MockWords.add("abandonment");
		MockWords.add("abandonments");
		MockWords.add("abandons");
		MockWords.add("abase");
		MockWords.add("abased");
		MockWords.add("abasedly");
		MockWords.add("abasement");
		MockWords.add("abaser");
		MockWords.add("abasers");
		MockWords.add("abases");
		MockWords.add("abash");
		MockWords.add("abashed");
		MockWords.add("abashedly");
		MockWords.add("hat");
		
		return MockWords;
	}
}
