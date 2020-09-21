package application;

import java.util.ArrayList;
/**
 * This class contains methods for checking whether or not a password is a valid password, whether or not a password is a weak password, and returning a list of bad passwords given a list of passwords.
 * @author Marcus Brooks
 * 
 */
public class PasswordCheckerUtility {
	
	/**
	 * Checks if a passed password is valid. 
	 * @param passwordString The password being checked for validity.
	 * @return A boolean declaring whether the passed password is valid or not.
	 * @throws LengthException Thrown if length of password is less than 6 digits.
	 * @throws NoDigitException Thrown if there is no numeric digit in the password.
	 * @throws NoUpperAlphaException Thrown if there is no uppercase character in the password.
	 * @throws NoLowerAlphaException Thrown if there is no lowercase character in the password.
	 * @throws InvalidSequenceException Thrown if there are more than two of the same digit in a row found within the password.
	 */
	public static boolean isValidPassword(String passwordString) throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException {
		boolean containsDigit = false;
		boolean containsInvalidSequence = false;
		char currentChar = passwordString.charAt(0);
		int currentSequenceCount = 1;
		for (int i = 0; i < passwordString.length(); i++) {
			if (passwordString.charAt(i) == '0' || passwordString.charAt(i) == '1' || passwordString.charAt(i) == '2' || passwordString.charAt(i) == '3' || passwordString.charAt(i) == '4' || passwordString.charAt(i) == '5' || passwordString.charAt(i) == '6' || passwordString.charAt(i) == '7' || passwordString.charAt(i) == '8' || passwordString.charAt(i) == '9') {
				containsDigit = true;
			} 
			if (passwordString.charAt(i) == currentChar && i > 0) {
				currentSequenceCount++;
			} else {
				currentSequenceCount = 1;
				currentChar = passwordString.charAt(i);
			}
			if (currentSequenceCount > 2) {
				containsInvalidSequence = true;
			}
		}
		if (passwordString.length() < 6) {
			throw new LengthException();
		} else if (containsDigit == false) {
			throw new NoDigitException();
		} else if (passwordString.equals(passwordString.toLowerCase())) {
			throw new NoUpperAlphaException();
		} else if (passwordString.equals(passwordString.toUpperCase())) {
			throw new NoLowerAlphaException();
		} else if (containsInvalidSequence == true) {
			throw new InvalidSequenceException();
		} else {
			return true;
		}
	}
	/**
	 * Checks if the passed password is weak.
	 * @param passwordString The password being checked for weakness.
	 * @return True if the password is between 6 and 9 digits in length. False otherwise.
	 */
	public static boolean isWeakPassword(String passwordString) {
		isValidPassword(passwordString);
		if (passwordString.length() >= 6 && passwordString.length() <= 9) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Returns a list of invalid passwords (does not contain weak passwords). The method is improperly named on purpose due to the test cases that will be run on this code using the incorrect method name (the name this method currently uses) per class discussion with professor.
	 * @param passwords The list of passwords to be checked for validity.
	 * @return A list of invalid passwords.
	 */
	public static ArrayList<String> validPasswords(ArrayList<String> passwords) {
		ArrayList<String> badPasswords = new ArrayList<String>();
		for (int i = 0; i < passwords.size(); i++) {
			try {
				PasswordCheckerUtility.isValidPassword(passwords.get(i));
			}
			catch (LengthException e) {
				badPasswords.add(passwords.get(i) + " " + e.getMessage() + ".");
			}
			catch (NoDigitException e) {
				badPasswords.add(passwords.get(i) + " " + e.getMessage() + ".");
			}
			catch (NoUpperAlphaException e) {
				badPasswords.add(passwords.get(i) + " " + e.getMessage() + ".");
			}
			catch (NoLowerAlphaException e) {
				badPasswords.add(passwords.get(i) + " " + e.getMessage() + ".");
			}
			catch (InvalidSequenceException e) {
				badPasswords.add(passwords.get(i) + " " + e.getMessage());
			}
		}
		return badPasswords;
	}
}