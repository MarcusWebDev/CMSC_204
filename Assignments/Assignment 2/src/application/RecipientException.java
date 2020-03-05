package application;
/**
 * Recipient exception class. To be thrown if the recipient line is empty or full
 * @author Marcus Brooks
 *
 */
public class RecipientException extends RuntimeException {
	/**
	 * Default constructor which prints a stack trace
	 */
	public RecipientException(){}
	/**
	 * Parameterized constructor which takes a custom error message
	 * @param message the custom error message string
	 */
	public RecipientException(String message) {
		super(message);
	}
}