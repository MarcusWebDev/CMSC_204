package application;
/**
 * Recipient class. Just contains a name
 * @author Marcus Brooks
 *
 */
public class Recipient {
	private String name;
	/**
	 * Parameterized constructor which sets the name of the recipient
	 * @param name the name of the recipient
	 */
	public Recipient(String name) {
		this.name = name;
	}
	/**
	 * Returns the name of the recipient
	 * @return the name of the recipient
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets the name of the recipient
	 * @param name the name of the recipient
	 */
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
}