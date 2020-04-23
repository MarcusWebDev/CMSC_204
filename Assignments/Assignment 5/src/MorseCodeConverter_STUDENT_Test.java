package application;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the convertToEnglish method using a string and for the convertToEnglish method using a file
 * @author Marcus Brooks
 *
 */
public class MorseCodeConverter_STUDENT_Test {
	private File inputFile;
	
	/**
	 * There is no setup necessary for these tests
	 */
	@Before
	public void setUp() {
		
	}
	/**
	 * Resets inputFile to null
	 */
	@After
	public void tearDown() {
		inputFile = null;
	}
	/**
	 * Tests for the string version of the convertToEnglish method
	 */
	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish(".. / -. . . -.. / - --- / -.-. --- -. - .. -. ..- . / - --- / --. . - / --- -. . / .... ..- -. -.. .-. . -.. ... / --- -. / .- .-.. .-.. / --- ..-. / -- -.-- / .-.. .- -... ... / .- -. -.. / .- ... ... .. --. -. -- . -. - ... / .- ... / .-- . .-.. .-.. / .- ... / --. . - / .- - / .-.. . .- ... - / .- -. / . .. --. .... - -.-- / ... . ...- . -. / --- -. / -- -.-- / ..-. .. -. .- .-.. / - --- / --. . - / .- -. / .- / .. -. / - .... . / -.-. .-.. .- ... ...");
		assertEquals("i need to continue to get one hundreds on all of my labs and assignments as well as get at least an eighty seven on my final to get an a in the class",converter1);

		String converter2 = MorseCodeConverter.convertToEnglish(".. -- / - .- -.- .. -. --. / ..-. --- ..- .-. / -.-. .-.. .- ... ... . ... / - .... .. ... / ... . -- . ... - . .-. / .- -. -.. / - .... . / ... .-- .. - -.-. .... / - --- / --- -. .-.. .. -. . / ...- . .-. ... .. --- -. ... / --- ..-. / . .- -.-. .... / -.-. .-.. .- ... ... / .... .- ... / -... . . -. / ... .. --. -. .. ..-. .. -.-. .- -. - .-.. -.-- / -- --- .-. . / -.. .. ..-. ..-. .. -.-. ..- .-.. - / - .... .- -. / .-- .... . -. / - .... .. -. --. ... / .-- . .-. . / .. -. / .--. . .-. ... --- -.");
		assertEquals("im taking four classes this semester and the switch to online versions of each class has been significantly more difficult than when things were in person", converter2);
	}
	/**
	 * Tests for the file version of the convertToEnglish method
	 * @throws FileNotFoundException
	 */
	@Test
	public void testConvertToEnglishFile() throws FileNotFoundException {
		inputFile = new File("D:\\Users\\Searing Arrow\\eclipse-workspace\\CMSC 204 Assignment 5\\src\\application\\MorseCodeTest_STUDENT.txt");
		String test1="this morse code is for testing purposes";		

		String converter1 = MorseCodeConverter.convertToEnglish(inputFile);
		assertEquals(test1,converter1);
		
		inputFile = new File("D:\\Users\\Searing Arrow\\eclipse-workspace\\CMSC 204 Assignment 5\\src\\application\\MorseCodeTest2_STUDENT.txt");
		String test2="im concerned about my grades";		
		String converter2 = MorseCodeConverter.convertToEnglish(inputFile);
		assertEquals(test2,converter2);

	}
}