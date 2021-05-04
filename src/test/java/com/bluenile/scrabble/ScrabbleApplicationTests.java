package com.bluenile.scrabble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bluenile.scrabble.dao.ScrabbleDao;
import com.bluenile.scrabble.model.ScrabbleWord;
import com.bluenile.scrabble.rule.ScrabbleRule;
import com.bluenile.scrabble.service.ScrabbleService;
import com.bluenile.scrabble.utils.StringUtils;

@SpringBootTest
class ScrabbleApplicationTests {

	@DisplayName("Test count char in string")
	@Test
	void testcountCharInString() {
		int countchar;
		countchar = StringUtils.countCharInString("abaa", 'a');
		assertEquals(3,countchar);
		countchar = StringUtils.countCharInString("abaa", ' ');
		assertEquals(0,countchar);
		countchar = StringUtils.countCharInString(" ", ' ');
		assertEquals(1,countchar);
		countchar = StringUtils.countCharInString("abaaccc", 'd');
		assertEquals(0,countchar);
		countchar = StringUtils.countCharInString("abaaccc", '2');
		assertEquals(0,countchar);
		countchar = StringUtils.countCharInString("aba accc 123", '2');
		assertEquals(1,countchar);
		countchar = StringUtils.countCharInString("abaa", '\u0000');
		assertEquals(0,countchar);
	}
	
	@DisplayName("Test count char string null value")
	@Test
	void assertThrowsExceptionCountCharInString() {
		Throwable exception = assertThrows(RuntimeException.class, () ->{
			StringUtils.countCharInString(null, 'a');
		});
		
		assertEquals(exception.getMessage(),"No empty string expected");
	}

	@DisplayName("Test char scores")
	@Test
	void testScrableRule() {		
		char[] charScore = {'a','l','d','g','b','m','f','v','k','j','x','q','z'};
		
		int charNull = ScrabbleRule.getScore('\u0000');
		assertEquals(0,charNull);
		
		assertAll(
			() -> assertEquals(1,ScrabbleRule.getScore(charScore[0])),
			() -> assertEquals(1,ScrabbleRule.getScore(charScore[1])),
			() -> assertEquals(2,ScrabbleRule.getScore(charScore[2])),
			() -> assertEquals(2,ScrabbleRule.getScore(charScore[3])),
			() -> assertEquals(3,ScrabbleRule.getScore(charScore[4])),
			() -> assertEquals(3,ScrabbleRule.getScore(charScore[5])),
			() -> assertEquals(4,ScrabbleRule.getScore(charScore[6])),
			() -> assertEquals(4,ScrabbleRule.getScore(charScore[7])),
			() -> assertEquals(5,ScrabbleRule.getScore(charScore[8])),
			() -> assertEquals(8,ScrabbleRule.getScore(charScore[9])),
			() -> assertEquals(8,ScrabbleRule.getScore(charScore[10])),
			() -> assertEquals(10,ScrabbleRule.getScore(charScore[11])),
			() -> assertEquals(10,ScrabbleRule.getScore(charScore[12]))			
		);

		assertAll(
			() -> assertNotEquals(10,ScrabbleRule.getScore(charScore[0])),
			() -> assertNotEquals(7,ScrabbleRule.getScore(charScore[1])),
			() -> assertNotEquals(10,ScrabbleRule.getScore(charScore[2])),
			() -> assertNotEquals(11,ScrabbleRule.getScore(charScore[3])),
			() -> assertNotEquals(50,ScrabbleRule.getScore(charScore[4])),
			() -> assertNotEquals(1,ScrabbleRule.getScore(charScore[5])),
			() -> assertNotEquals(3,ScrabbleRule.getScore(charScore[6])),
			() -> assertNotEquals(2,ScrabbleRule.getScore(charScore[7])),
			() -> assertNotEquals(8,ScrabbleRule.getScore(charScore[8])),
			() -> assertNotEquals(5,ScrabbleRule.getScore(charScore[9])),
			() -> assertNotEquals(9,ScrabbleRule.getScore(charScore[10])),
			() -> assertNotEquals(6,ScrabbleRule.getScore(charScore[11])),
			() -> assertNotEquals(15,ScrabbleRule.getScore(charScore[12]))			
		);
	}

	@DisplayName("Test get words string null value")
	@Test
	void assertThrowsExceptionGetWords() {
		TestScrabbleDaoText scrabbleDaoText = new TestScrabbleDaoText();
		
		Throwable exception = assertThrows(RuntimeException.class, () ->{
			scrabbleDaoText.getWords(null);
		});
		
		assertEquals(exception.getMessage(),"No empty string expected");
	}
	
	@DisplayName("Test get words")
	@Test
	void testGetWords () {
		ScrabbleDao scrabbleDaoText = new TestScrabbleDaoText();
		
		assertTrue(scrabbleDaoText.getWords("").isEmpty());
		
		assertFalse(scrabbleDaoText.getWords("a").isEmpty());
		
		for (ScrabbleWord word : scrabbleDaoText.getWords("a")) {
			assertTrue((word.getWord().length() <= "a".length()) && (word.getWord().length() > 0));
		}
		for (ScrabbleWord word : scrabbleDaoText.getWords("12")) {
			assertTrue(word.getWord().length() <= "12".length() && word.getWord().length() > 0);
		}
		for (ScrabbleWord word : scrabbleDaoText.getWords("!,.")) {
			assertTrue(word.getWord().length() <= "!,.".length() && word.getWord().length() > 0);
		}
		for (ScrabbleWord word : scrabbleDaoText.getWords("   	")) {
			assertTrue(word.getWord().length() <= "   	".length() && word.getWord().length() > 0);
		}
		
		for (ScrabbleWord word : scrabbleDaoText.getWords("a")) {
			assertFalse((word.getWord().length() <= "a".length()) && (word.getWord().length() == 0));
		}
		int c = 0;
		for (ScrabbleWord word : scrabbleDaoText.getWords("12")) {
			if (word.getWord().length() > "12".length()) {
			c = c++;
			}
			assertEquals(0,c);
		}
	}

	@DisplayName("Test getScrabbleWords null string value and null")
	@Test
	void testGetScrabbleWords() {
		ScrabbleDao wordDao = new TestScrabbleDaoText();
		ScrabbleService scrabbleService = new ScrabbleService(wordDao);
		
		/*
		 * Test null and special characters
		 */

		assertTrue(scrabbleService.getScrabbleWords(null).isEmpty());
		assertTrue(scrabbleService.getScrabbleWords("#").isEmpty());
		assertTrue(scrabbleService.getScrabbleWords("    ").isEmpty());
		assertTrue(scrabbleService.getScrabbleWords("\"").isEmpty());
		assertTrue(scrabbleService.getScrabbleWords("h.a.t.").isEmpty());
		assertTrue(scrabbleService.getScrabbleWords("#$%&GDF\"").isEmpty());
		assertTrue(scrabbleService.getScrabbleWords("-c.o:d=E").isEmpty());

		/*
		 * Test Capital letters
		 */
		assertFalse(scrabbleService.getScrabbleWords("HAT").isEmpty());
		assertFalse(scrabbleService.getScrabbleWords("Hat").isEmpty());	
		assertFalse(scrabbleService.getScrabbleWords("haT").isEmpty());	
		/*
		 * Test Repeated chars
		 */
		assertFalse(scrabbleService.getScrabbleWords("abalone").isEmpty());
		for (ScrabbleWord scrabbleWord : scrabbleService.getScrabbleWords("abalone")) {
			assertTrue(StringUtils.countCharInString(scrabbleWord.getWord().toString(),'a') <= 
					StringUtils.countCharInString("abalone", 'a'));
		}
		
		assertFalse(scrabbleService.getScrabbleWords("abandon").isEmpty());
		for (ScrabbleWord scrabbleWord : scrabbleService.getScrabbleWords("abandon")) {
			assertFalse(scrabbleWord.getWord() == "abandoned");
		}
		
		assertFalse(scrabbleService.getScrabbleWords("abandoned").isEmpty());
		for (ScrabbleWord scrabbleWord : scrabbleService.getScrabbleWords("abandoned")) {
			assertFalse(scrabbleWord.getWord() == "abandonee");
		}
	}
}
