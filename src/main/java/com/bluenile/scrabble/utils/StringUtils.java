package com.bluenile.scrabble.utils;

 public class StringUtils {
	
	/**
	 * Private method to count the repetitions of character parameter within
	 * the string parameter.
	 * 
	 * @param inputChar		this char is used to search within the string any
	 * 						repetition of itself.
	 * @param inputStr		this string is used to find out the char repetition
	 * 						within it.
	 * @return				returns an integer indicating the times the char is
	 * 						repeated within the string.
	 */
	public static int countCharInString(String inputStr, char inputChar) {
		int position = 0;
		int count = 0;

		try{     
			/** Looks for the first char.*/
			position = inputStr.indexOf(inputChar);
		
			/** While the char exists within the string.*/
			while (position > -1) {
				/** Counts the times the char is within the string.*/
				count ++;
				/** Continues searching for the next character.*/
				position = inputStr.indexOf(inputChar, position + 1);
			}
		
			/** Returns the times the character is within the string.*/
			return count;
		}catch(Exception e) {
			throw new RuntimeException("No empty string expected");
		}
	}
}
