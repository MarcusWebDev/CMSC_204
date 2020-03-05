package application;
/**
 * Queue data structure. Implements the queue interface
 * @author Marcus Brooks
 *
 */
public class MyQueue<T> implements QueueInterface<T> {
	
	private T[] baseArray;
	private int DEFAULT_CAPACITY = 25;
	private int currentSize;
	/**
	 * Default constructor which initializes the base array with the default capacity
	 */
	public MyQueue() {
		baseArray = (T[]) new Object[DEFAULT_CAPACITY];
	}
	/**
	 * Parameterized constructor that initializes the base array with a custom size
	 * @param size
	 */
	public MyQueue(int size) {
		baseArray = (T[]) new Object[size];
	}
	/**
	 * Returns a boolean representing whether or not the queue is empty
	 * @return a boolean representing whether or not the queue is empty
	 */
	public boolean isEmpty() {
		return this.currentSize == 0;
	}
	/**
	 * Returns a boolean representing whether or not the queue is full
	 * @return a boolean representing whether or not the queue is full
	 */
	public boolean isFull() {
		return this.currentSize == baseArray.length;
	}
	/**
	 * Removes a piece of data from the front of the queue
	 * @return the piece of data removed from the front of the queue
	 */
	public T dequeue() {
		T removedData = null;
		if(!isEmpty()) {
			removedData = baseArray[0];
			for (int i = 0; i < currentSize; i++) {
				if (i != baseArray.length - 1) {
					baseArray[i] = baseArray[i+1];
				}
			}
			baseArray[currentSize - 1] = null;
			currentSize--;
		}
		return removedData;
	}
	/**
	 * Adds a piece of data to the back of the queue
	 * @param e the piece of data being added
	 * @return a boolean representing whether or not the data was added to the queue
	 */
	public boolean enqueue(T e) {
		if(!isFull()) {
			baseArray[currentSize] = e;
			currentSize++;
			return true;
		}
		return false;
	}
	/**
	 * Returns an array representation of the queue
	 * @return an array representation of the queue
	 */
	public T[] toArray() {
		T[] newArray = ((T[]) new Object[currentSize]);
		for (int i = 0; i < currentSize; i++) {
			newArray[i] = baseArray[i];
			//This is a shallow copy, change if needed
		}
		return newArray;
	}
	/**
	 * Returns the size of the queue
	 * @return the size of the queue
	 */
	public int size() {
		return this.currentSize;
	}
}