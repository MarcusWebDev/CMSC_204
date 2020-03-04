package application;

public class RecipientLine implements RecipientLineInterface {
	MyQueue<Recipient> queue;
	
	public RecipientLine() {
		queue = new MyQueue<Recipient>();
	}
	public RecipientLine(int size) {
		queue = new MyQueue<Recipient>(size);
	}
	public boolean addNewRecipient(Recipient rc) throws RecipientException {
		if (queue.isFull()) {
			throw new RecipientException("Recipient Queue is full");
		}
		return queue.enqueue(rc);
	}
	public Recipient recipientTurn() throws RecipientException {
		if (queue.isEmpty()) {
			throw new RecipientException("Recicient Queue is empty");
		}
		return queue.dequeue();
	}
	public boolean recipientLineEmpty() {
		return queue.isEmpty();
	}
	public Recipient[] toArrayRecipient() {
		return queue.toArray();
	}
}