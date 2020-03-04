package application;

public class VolunteerLine implements VolunteerLineInterface {
	MyQueue<Volunteer> queue;
	
	public VolunteerLine() {
		queue = new MyQueue<Volunteer>();
	}
	public VolunteerLine(int size) {
		queue = new MyQueue<Volunteer>(size);
	}
	public boolean addNewVolunteer(Volunteer v) throws VolunteerException {
		if (queue.isFull()) {
			throw new VolunteerException("Volunteer Queue is full");
		}
		return queue.enqueue(v);
	}
	public Volunteer volunteerTurn() throws VolunteerException {
		if (queue.isEmpty()) {
			throw new VolunteerException("Volunteer Queue is empty");
		}
		return queue.dequeue();
	}
	public boolean volunteerLineEmpty() {
		return queue.isEmpty();
	}
	public Volunteer[] toArrayVolunteer() {
		return queue.toArray();
	}
}