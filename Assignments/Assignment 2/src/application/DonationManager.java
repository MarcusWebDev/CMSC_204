package application;

public class DonationManager implements DonationManagerInterface {
	Container container;
	VolunteerLine volunteerLine;
	RecipientLine recipientLine;
	DonationManager(){
		container = new Container();
		volunteerLine = new VolunteerLine();
		recipientLine = new RecipientLine();
	}
	DonationManager(int size) {
		container = new Container(size);
		volunteerLine = new VolunteerLine(size);
		recipientLine = new RecipientLine(size);
	}
	
	public boolean managerLoadContainer(DonationPackage dPackage) {
		return container.loadContainer(dPackage);
	}
	public boolean managerQueueVolunteer(Volunteer v) {
		return volunteerLine.addNewVolunteer(v);
	}
	public boolean managerQueueRecipient(Recipient r) {
		return recipientLine.addNewRecipient(r);
	}
	public int donatePackage() throws ContainerException, VolunteerException, RecipientException {
		if (volunteerLine.volunteerLineEmpty()) {
			return 1;
		} 
		if (recipientLine.recipientLineEmpty()) {
			return 2;
		}
		
		container.removePackageFromContainer();
		managerQueueVolunteer(volunteerLine.volunteerTurn());
		recipientLine.recipientTurn();
		return 0;
		
	}
	public DonationPackage[] managerArrayPackage() {
		return container.toArrayPackage();
	}
	public Recipient[] managerArrayRecipient() {
		return recipientLine.toArrayRecipient();
	}
	public Volunteer[] managerArrayVolunteer() {
		return volunteerLine.toArrayVolunteer();
	}
}