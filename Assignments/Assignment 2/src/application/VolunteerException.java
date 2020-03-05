package application;
/**
 * Volunteer exception class. To be thrown if the volunteer line is empty or full
 * @author Marcus Brooks
 *
 */
public class VolunteerException extends RuntimeException {
	/**
	 * Default constructor which prints a stack trace
	 */
	public VolunteerException() {}
	/**
	 * Parameterized constructor which takes a custom error message
	 * @param message the custom error message string
	 */
	public VolunteerException(String message) {
		super(message);
	}
}