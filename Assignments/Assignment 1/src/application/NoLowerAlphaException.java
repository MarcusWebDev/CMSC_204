package application;
/**
 * An error to be thrown if a password does not contain a lowercase character.
 * @author Marcus Brooks
 *
 */
public class NoLowerAlphaException extends RuntimeException {
	/**
	 * Default constructor that uses the default message.
	 */
	public NoLowerAlphaException() {
		this("The password must contain at least one lowercase alphabetic character");
	}
	/**
	 * Constructor which accepts a customizable error message string.
	 * @param message Error Message
	 */
	public NoLowerAlphaException(String message) {
		super(message);
	}
}