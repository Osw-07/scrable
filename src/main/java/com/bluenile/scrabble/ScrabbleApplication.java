package com.bluenile.scrabble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Scrabble Spring Boot application that implements an API-REST service
 * listening on http://localhost:8080/words/<inputString>. Reads an inputString
 * parameter provided by the client with GET Method. Then analyzes the chars
 * within the inputString to select from a list words text file, the words that
 * contains only the chars within the inputString to put them into the scrabble
 * words list to the client.
 * 
 * @version 1.0 30 December 2020
 * @author Oswaldo_Gomez
 */
@SpringBootApplication
public class ScrabbleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScrabbleApplication.class, args);
	}
}
