package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Marcus Brooks
 *
 */
public class PasswordCheckerSTUDENT_Test {
	ArrayList<String> passwords;
	@Before
	public void setUp() throws Exception {
		String[] p = {"PIKACHU1", "Pikachu1", "Piiikachu1", "pikachu1", "Pikachu", "OwOWhatsThis", "Pika1" };
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("Pika1"));
			PasswordCheckerUtility.isValidPassword("Pika1");
			assertTrue("Did not throw lengthException",false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{

		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("pikachu1"));
			PasswordCheckerUtility.isValidPassword("pikachu1");
			assertTrue("Did not throw NoUpperAlphaException",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("PIKACHU1"));
			PasswordCheckerUtility.isValidPassword("PIKACHU1");
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{

			assertEquals(true,PasswordCheckerUtility.isValidPassword("Pikachu1"));
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("Pikachu1");
			assertTrue(weakPwd);
			assertEquals(true,PasswordCheckerUtility.isValidPassword("PikachuTheElectricMousePokemon1"));
			weakPwd = PasswordCheckerUtility.isWeakPassword("PikachuTheElectricMousePokemon1");
			assertFalse(weakPwd);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception: " + e.getMessage(),false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("Piiikachu1"));
			PasswordCheckerUtility.isValidPassword("Piiikachu1");
			assertTrue("Did not throw an InvalidSequenceException",false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("Pikachu"));
			PasswordCheckerUtility.isValidPassword("Pikachu");
			assertTrue("Did not throw a NoDigitException", false);
		}
		catch (NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException",true);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides a NoDigitException",false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("Mercy1"));
			PasswordCheckerUtility.isValidPassword("Mercy1");
		}
		catch(Exception e)
		{
			assertTrue("Threw some exception: " + e.getMessage(),false);
		}
	}
	
	/**
	 * Test the validPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	
	// {"PIKACHU1", "Pikachu1", "Piiikachu1", "pikachu1", "Pikachu", "OwOWhatsThis", "Pika1" };
	@Test
	public void testValidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.validPasswords(passwords);
		Scanner scan = new Scanner(results.get(0)); //
		assertEquals(scan.next(), "PIKACHU1");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		//assertEquals(scan.nextLine(), " The password must contain at least one lowercase alphabetic character.");
		scan = new Scanner(results.get(1)); //
		assertEquals(scan.next(), "Piiikachu1");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("more than two"));
		//assertEquals(scan.nextLine(), " The password cannot contain more than two of the same character in sequence.");
		scan = new Scanner(results.get(2)); //
		assertEquals(scan.next(), "pikachu1");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		//assertEquals(scan.nextLine(), " The password must contain at least one uppercase alphabetic character.");
		scan = new Scanner(results.get(3)); //
		assertEquals(scan.next(), "Pikachu");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));
		//assertEquals(scan.nextLine(), " The password must contain at least one digit.");
		scan = new Scanner(results.get(4)); //a
		assertEquals(scan.next(), "OwOWhatsThis");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));
		//assertEquals(scan.nextLine(), " The password must contain at least one digit.");
		scan = new Scanner(results.get(5)); //a
		assertEquals(scan.next(), "Pika1");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("6 characters"));
		//assertEquals(scan.nextLine(), " The password must be at least 6 characters long");
	}
	
}