package application;
/**
 * An error to be thrown if a password does not contain an uppercase letter.
 * @author Marcus Brooks
 *
 */
public class NoUpperAlphaException extends RuntimeException {
	/**
	 * Default constructor that uses the default message.
	 */
	public NoUpperAlphaException() {
		this("The password must contain at least one uppercase alphabetic character");
	}
	/**
	 * Constructor which accepts a customizable error message string.
	 * @param message Error Message
	 */
	public NoUpperAlphaException(String message) {
		super(message);
	}
}