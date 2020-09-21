package application;
/**
 * An error to be thrown if a password typed and a retyped password do not match.
 * @author Marcus Brooks
 *
 */
public class UnmatchedException extends RuntimeException {
	/**
	 * Default constructor that uses the default message.
	 */
	public UnmatchedException() {
		this("The passwords do not match");
	}
	/**
	 * Constructor which accepts a customizable error message string.
	 * @param message Error Message
	 */
	public UnmatchedException(String message) {
		super(message);
	}
}