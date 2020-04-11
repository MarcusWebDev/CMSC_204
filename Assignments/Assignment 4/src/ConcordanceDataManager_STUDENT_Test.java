package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcordanceDataManager_STUDENT_Test {
	private ConcordanceDataManagerInterface concordanceManager = new ConcordanceDataManager();
	private File inputFile, outputFile;
	private String text;

	/**
	 * Create an instance of ConcordanceDataManager
	 * Create a string for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		concordanceManager = new ConcordanceDataManager();
		text = "I'm testing this thing. \n This assignment is hard,"+
				" and it's a lot of work work.";
	}

	/**
	 * Set concordanceManager reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		concordanceManager = null;
	}
	@Test
	public void testCreateConcordanceArray() {
		ArrayList<String> words = concordanceManager.createConcordanceArray(text);
		for (int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}
		assertEquals(words.get(0),"assignment: 2\n");
		assertEquals(words.get(1), "hard: 2\n");
		assertEquals(words.get(2),"i'm: 1\n");
		assertEquals(words.get(3),"it's: 2\n");
		assertEquals(words.get(4),"lot: 2\n");
		assertEquals(words.get(5),"testing: 1\n");
		assertEquals(words.get(6),"thing: 1\n");
		assertEquals(words.get(7),"this: 1, 2\n");
		assertEquals(words.get(8),"work: 2\n");
	}
	
	
	/**
	 * Test for createConcordanceFile method
	 * This is intended to be used with the test file:
	 * Now_is_the_time.txt
	 */
	@Test
	public void testCreateConcordanceFileA() {
		ArrayList<String> words = new ArrayList<String>();
		try {
			inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("Please work. Need this to \n work.");
			
			inFile.close();
			outputFile = new File("Test1Out.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
			}

			scan.close();
			outFile.close();
		 
			assertEquals(words.get(0),"need: 1");
			assertEquals(words.get(1), "please: 1");
			assertEquals(words.get(2),"this: 1");
			assertEquals(words.get(3),"work: 1, 2");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an FileNotFoundException");
		} 
	}
}