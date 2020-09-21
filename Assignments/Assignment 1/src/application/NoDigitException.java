package application;

/**
 * An error to be thrown if a password does not contain a numeric digit.
 * @author Marcus Brooks
 *
 */
public class NoDigitException extends RuntimeException {
	/**
	 * Default constructor that uses the default message.
	 */
	public NoDigitException() {
		this("The password must contain at least one digit");
	}
	/**
	 * Constructor which accepts a customizable error message string.
	 * @param message Error Message
	 */
	public NoDigitException(String message) {
		super(message);
	}
}