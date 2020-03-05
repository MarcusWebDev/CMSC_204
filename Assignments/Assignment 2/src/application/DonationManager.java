package application;
/**
 * Manages the donation of donation packages. 
 * @author Marcus Brooks
 *
 */
public class DonationManager implements DonationManagerInterface {
	Container container;
	VolunteerLine volunteerLine;
	RecipientLine recipientLine;
	
	Recipient removedRecipient;
	Volunteer removedVolunteer;
	DonationPackage removedDonationPackage;
	/**
	 * Default constructor which initializes all of the data structures used with the default capacity
	 */
	DonationManager(){
		container = new Container();
		volunteerLine = new VolunteerLine();
		recipientLine = new RecipientLine();
	}
	/**
	 * Parameterized constructor which initializes all of the data structures used with a custom size
	 * @param size the size of the container, volunteer line, and recipient line
	 */
	DonationManager(int size) {
		container = new Container(size);
		volunteerLine = new VolunteerLine(size);
		recipientLine = new RecipientLine(size);
	}
	/**
	 * Loads the container with a donation package
	 * @param dPackage the donation package to load to the container
	 * @return boolean representing whether or not the donation package was added to the container
	 */
	public boolean managerLoadContainer(DonationPackage dPackage) {
		return container.loadContainer(dPackage);
	}
	/**
	 * Enqueues a volunteer to the volunteer line
	 * @param v the volunteer to be added to the volunteer line
	 * @return boolean representing whether or not the volunteer was enqueued to the volunteer line
	 */
	public boolean managerQueueVolunteer(Volunteer v) {
		return volunteerLine.addNewVolunteer(v);
	}
	/**
	 * Enqueues a recipient to the recipient line
	 * @param r the recipient to be enqueued to the recipient line
	 * @return a boolean representing whether or not the recipient was enqueued to the recipient line
	 */
	public boolean managerQueueRecipient(Recipient r) {
		return recipientLine.addNewRecipient(r);
	}
	/**
	 * Simulates the donation of a donation package. Removes a package from the container, removes a volunteer from the front of the volunteer line and adds them to the back of the volunteer line,
	 * removes a recipient from the recipient line.
	 * @return an integer representing whether the package donation was successful. If not, returns 1 if volunteer line is empty, 2 if recipient line is empty, and 0 if it was successful.
	 * @throws ContainerException Thrown if the the container is empty
	 * @throws VolunteerException Thrown if the volunteer line is empty
	 * @throws RecipientException Thrown if the recipient line is empty
	 */
	public int donatePackage() throws ContainerException, VolunteerException, RecipientException {
		if (!volunteerLine.volunteerLineEmpty() && !recipientLine.recipientLineEmpty()) {
			removedDonationPackage = container.removePackageFromContainer();
			managerQueueVolunteer(removedVolunteer = volunteerLine.volunteerTurn());
			removedRecipient = recipientLine.recipientTurn();;
		} else if(volunteerLine.volunteerLineEmpty()) {
			throw new VolunteerException();
		} else if(recipientLine.recipientLineEmpty()) {
			throw new RecipientException();
		}

		return 0;
	}
	/**
	 * Returns an array representation of the container
	 * @return an array representation of the container
	 */
	public DonationPackage[] managerArrayPackage() {
		return container.toArrayPackage();
	}
	/**
	 * Returns an array representation of the recipient line
	 * @return an array representation of the recipient line
	 */
	public Recipient[] managerArrayRecipient() {
		return recipientLine.toArrayRecipient();
	}
	/**
	 * Returns an array representation of the volunteer line
	 * @return an array representation of the volunteer line
	 */
	public Volunteer[] managerArrayVolunteer() {
		return volunteerLine.toArrayVolunteer();
	}
	public String toString() {
		return removedVolunteer + " delievered " + removedDonationPackage + " to " + removedRecipient;
	}
}