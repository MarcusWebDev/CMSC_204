package application;
/**
 * Simulates a line of volunteers. 
 * @author Marcus Brooks
 *
 */
public class VolunteerLine implements VolunteerLineInterface {
	MyQueue<Volunteer> queue;
	/**
	 * Default constructor which initializes the volunteer line with a default capacity
	 */
	public VolunteerLine() {
		queue = new MyQueue<Volunteer>();
	}
	/**
	 * Parameterized constructor which initializes the volunteer line with a custom capacity
	 * @param size the capacity of the volunteer line
	 */
	public VolunteerLine(int size) {
		queue = new MyQueue<Volunteer>(size);
	}
	/**
	 * Enqueues a volunteer to the volunteer line
	 * @param v the volunteer to be enqueued to the volunteer line
	 * @return a boolean representing if the volunteer was added to the volunteer line
	 * @throws VolunteerException Thrown if the volunteer line is full
	 */
	public boolean addNewVolunteer(Volunteer v) throws VolunteerException {
		if (queue.isFull()) {
			throw new VolunteerException("Volunteer Queue is full");
		}
		return queue.enqueue(v);
	}
	/**
	 * Removes a volunteer from the front of the volunteer line
	 * @return the volunteer removed from the volunteer line
	 * @throws VolunteerException Thrown if the volunteer line is empty
	 */
	public Volunteer volunteerTurn() throws VolunteerException {
		if (queue.isEmpty()) {
			throw new VolunteerException("Volunteer Queue is empty");
		}
		return queue.dequeue();
	}
	/**
	 * Returns a boolean representing whether or not the volunteer line is empty
	 * @return a boolean representing whether or not the volunteer line is empty
	 */
	public boolean volunteerLineEmpty() {
		System.out.println(queue.isEmpty());
		return queue.isEmpty();
	}
	/**
	 * Returns an array representation of the volunteer line
	 * @return an array representation of the volunteer line
	 */
	public Volunteer[] toArrayVolunteer() {
		Object[] test = queue.toArray();
		Volunteer[] test2 =  new Volunteer[queue.size()];
		for (int i = 0; i < test2.length; i++) {
			test2[i] = (Volunteer) test[i];
		}
		return test2;
	}
}