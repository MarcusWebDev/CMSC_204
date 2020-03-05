package application;
/**
 * Container exception class. To be thrown if the container is empty or full
 * @author Marcus Brooks
 *
 */
public class ContainerException extends RuntimeException {
	/**
	 * Default constructor which prints a stack trace
	 */
	public ContainerException(){}
	/**
	 * Parameterized constructor which takes a custom error message
	 * @param message the custom error message string
	 */
	public ContainerException(String message) {
		super(message);
	}
}