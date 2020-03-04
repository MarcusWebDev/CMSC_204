package application;

public class DonationPackage {
	private String description;
	private double weight;
	
	DonationPackage(String description, double weight) {
		this.description = description;
		this.weight = weight;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getWeight() {
		return this.weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
}