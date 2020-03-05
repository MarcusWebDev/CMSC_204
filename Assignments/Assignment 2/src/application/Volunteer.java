package application;
/**
 * Volunteer class. Volunteers just have names
 * @author Marcus Brooks
 *
 */
public class Volunteer {
	private String name;
	/**
	 * Parameterized constructor which sets the name of the volunteer
	 * @param name
	 */
	public Volunteer(String name) {
		this.name = name;
	}
	/**
	 * Returns the name of the volunteer
	 * @return the name of the volunteer
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets the name of the volunteer
	 * @param name the name of the volunteer
	 */
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
}