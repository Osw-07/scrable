package com.bluenile.scrabble.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluenile.scrabble.model.ScrabbleWord;
import com.bluenile.scrabble.service.ScrabbleService;

/**
 * This controller class is responsible for exposing the API methods for 
 * clients, this class is a RESTful web service to receive the request as a GET
 * method then takes the input string parameter and creates a list of words 
 * based on the characters contained in the input string. After that, scores to
 * the created words and finally sends the list of words and their respective
 * scores to the client.
 * <p>
 * The controller class is related to the service class by a "HAS-A"
 * relationship to "delegate" the Scrabble process to the service, so that the 
 * controller only focuses on exposing the functionality, getting the request
 * and respond to the client by declaring a ScreabbleService property.
 * <p>
 * This class is listening on the endpoint http://localhost:8080/words/ and
 * receive the input string parameter by a GET method, for example: 
 * http://localhost:8080/words/abc; where -abc- is the input string parameter
 * received by the GET method.
 * 
 * @version 1.0 30 December 2020
 * @author Oswaldo_Gomez
 */
@RequestMapping("words")
@RestController
public class ScrabbleController {

	private final ScrabbleService scrabbleService;
	
	/**
	 * Creates a scrableController and sets the scrableService property, so
	 * that the controller can delegate the Scrabble process to service.
	 * @Autowired annotation allows to resolve and inject collaborating
	 * dependency scrabbleService instance to ScrabbleController constructor.
	 * 
	 * @param scrabbleService	object of the ScrabbleService class to be set.
	 * 						
	 */
	@Autowired
	public ScrabbleController(ScrabbleService scrabbleService) {
		super();
		this.scrabbleService = scrabbleService;
	}
	
	/**
	 * Takes the inputString parameter and invokes the Service's 
	 * getScrableWords() method to build a scored list of words creating by the
	 * characters contained in the inputString.
	 * 
	 * @param inputString 		this parameter is passed to the invoked
	 * 							getScrableWords() service method to build a
	 * 							scored list of words creating by the characters
	 * 							contained in the input string.
	 * 							The @GetMapping annotation ensures that the
	 * 							HTTP GET request to /words are mapped to the
	 * 							getScrableWords() method. The @PathVariable
	 * 							annotation is used to handle the template
	 * 							inputString variable in the request URI mapping
	 * 							and use it as a inputString method parameter.
	 * @return					this method returns a list of ScrableWord objects
	 * 							that is list of the words created based on the 
	 * 							inputString's characters and their respective 
	 * 							scores in a Json format.
	 */
	@GetMapping (path = "{inputString}")
	public List<ScrabbleWord> getScrableWords(@PathVariable("inputString")String inputString) {
		return scrabbleService.getScrabbleWords(inputString);
	}
}
