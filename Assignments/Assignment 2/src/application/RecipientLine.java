package application;
/**
 * Simulates a line of recipients. 
 * @author Marcus Brooks
 *
 */
public class RecipientLine implements RecipientLineInterface {
	MyQueue<Recipient> queue;
	/**
	 * Default constructor which initializes the recipient line with the default capacity
	 */
	public RecipientLine() {
		queue = new MyQueue<Recipient>();
	}
	/**
	 * Parameterized constructor which initializes the recipient line with a custom capacity
	 * @param size the size of the recipient line
	 */
	public RecipientLine(int size) {
		queue = new MyQueue<Recipient>(size);
	}
	/**
	 * Enqueues a recipient to the recipient line
	 * @param rc The recipient to be enqueued
	 * @return a boolean representing whether or not the recipient was added to the queue
	 * @throws RecipientException Thrown if the recipient line is full
	 */
	public boolean addNewRecipient(Recipient rc) throws RecipientException {
		if (queue.isFull()) {
			throw new RecipientException("Recipient Queue is full");
		}
		return queue.enqueue(rc);
	}
	/**
	 * Removes a recipient from the front of the recipient line
	 * @return The recipient removed from the recipient line
	 * @throws RecipientException Thrown if the recipient line is empty
	 */
	public Recipient recipientTurn() throws RecipientException {
		if (queue.isEmpty()) {
			throw new RecipientException("Recicient Queue is empty");
		}
		return queue.dequeue();
	}
	/**
	 * Returns a boolean representing whether or not the recipient line is empty
	 * @return a boolean representing whether or not the recipient line is empty
	 */
	public boolean recipientLineEmpty() {
		return queue.isEmpty();
	}
	/**
	 * Returns an array representation of the recipient line
	 * @return an array representation of the recipient line
	 */
	public Recipient[] toArrayRecipient() {
		Object[] test = queue.toArray();
		Recipient[] test2 =  new Recipient[queue.size()];
		for (int i = 0; i < test2.length; i++) {
			test2[i] = (Recipient) test[i];
		}
		return test2;
	}
}