package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcordanceDataStructure_STUDENT_Test {
	ConcordanceDataStructureInterface concordanceDataStructure, testStructure;
	String text;
	ArrayList<String> array;

	@Before
	public void setUp() throws Exception {
		concordanceDataStructure = new ConcordanceDataStructure(6000);
		testStructure = new ConcordanceDataStructure("Testing", 2);
	}

	@After
	public void tearDown() throws Exception {
		concordanceDataStructure = testStructure = null;
	}
	@Test
	public void testAdd() {
		testStructure.add("skyscraper", 2);
		testStructure.add("dunes", 3);
		ArrayList<String> testArray = testStructure.getWords(1);
		String testString = "";
		for (int i = 0; i < testArray.size(); i++) {
			testString = testString + testArray.get(i);
		}
		assertEquals(testString, "skyscraperdunes");
		
	}
	@Test
	public void testGetTableSize()
	{
		assertEquals(4003, concordanceDataStructure.getTableSize());
		assertEquals(2, testStructure.getTableSize());		
	}
	@Test
	public void testShowAll() {
		testStructure.add("wee", 12);
		testStructure.add("having", 19);
		testStructure.add("fun", 4);
		testStructure.add("now", 16);
		testStructure.add("yahoo", 5);
		array = testStructure.showAll();
		assertTrue(array.get(0).contains("fun: 4"));
		assertTrue(array.get(1).contains("having: 19"));
		assertTrue(array.get(2).contains("now: 1"));
		assertTrue(array.get(3).contains("wee: 12"));
		assertTrue(array.get(4).contains("yahoo: 5"));
	}
}