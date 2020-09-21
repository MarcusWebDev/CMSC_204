package application;

/**
 * An error to be thrown if a passed password contains more than 2 of the same character in a row.
 * @author Marcus Brooks
 *
 */
public class InvalidSequenceException extends RuntimeException {
	
	/**
	 * Default constructor that uses the default message.
	 */
	public InvalidSequenceException() {
		this("The password cannot contain more than two of the same character in sequence.");
	}
	/**
	 * Constructor which accepts a customizable error message string.
	 * @param message Error Message
	 */
	public InvalidSequenceException(String message) {
		super(message);
	}
}