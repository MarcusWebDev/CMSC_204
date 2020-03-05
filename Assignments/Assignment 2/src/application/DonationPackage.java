package application;
/**
 * A donation package. Has a description and weight
 * @author Marcus Brooks
 *
 */
public class DonationPackage {
	private String description;
	private double weight;
	/**
	 * Parameterized constructor setting description and weight
	 * @param description description of what the donation package is
	 * @param weight the weight of the donation package
	 */
	DonationPackage(String description, double weight) {
		this.description = description;
		this.weight = weight;
	}
	/**
	 * Returns the description of the donation package
	 * @return the description of the donation package
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * Sets the description of the donation package
	 * @param description the description of the donation package
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Returns the weight of the donation package
	 * @return the weight of the donation package
	 */
	public double getWeight() {
		return this.weight;
	}
	/**
	 * Sets the weight of the donation package
	 * @param weight the weight of the donation package
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public boolean isHeavy() {
		return weight > 19;
	}
	public String toString() {
		return description;
	}
}