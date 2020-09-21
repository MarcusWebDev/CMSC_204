package application;

/**
 * An error to be thrown if the length of a password is less than 6 characters long.
 * @author Marcus Brooks
 *
 */
public class LengthException extends RuntimeException {
	/**
	 * Default constructor that uses the default message.
	 */
	public LengthException() {
		this("The password must be at least 6 characters long");
	}
	/**
	 * Constructor which accepts a customizable error message string.
	 * @param message Error Message
	 */
	public LengthException(String message) {
		super(message);
	}
}