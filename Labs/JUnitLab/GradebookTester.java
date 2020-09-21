package Lab;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Marcus Brooks
 *
 */
public class GradebookTester {
	private GradeBook book1; 
	private GradeBook book2; 
	
	@Before
	public void setUp() {
		book1 = new GradeBook(5);
		book2 = new GradeBook(5);
		book1.addScore(456.788);
		book1.addScore(19.55);
		book2.addScore(16.81);
		book2.addScore(59.24);
		book2.addScore(98.5);
	}
	@After
	public void tearDown() {
		book1 = book2 = null;
	}
	@Test
	public void testAddScore() {
		assertTrue(book1.toString().equals("456.788 19.55 "));
		assertTrue(book2.toString().equals("16.81 59.24 98.5 "));
		
		assertEquals(book1.getScoreSize(), 2);
		assertEquals(book2.getScoreSize(), 3);
		
	}
	@Test
	public void testSum() {
		assertEquals(book1.sum(), 476.338, 0.1);
		assertEquals(book2.sum(), 174.55, 0.1);
	}
	@Test
	public void testMinimum() {
		assertEquals(book1.minimum(), 19.55, 0.1);
		assertEquals(book2.minimum(), 16.81, 0.1);
	}
	@Test
	public void testFinalScore() {
		assertEquals(book1.finalScore(), 456.788, 0.1);
		assertEquals(book2.finalScore(), 157.74, 0.1);
	}
	
}