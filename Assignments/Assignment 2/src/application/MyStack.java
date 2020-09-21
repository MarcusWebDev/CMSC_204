package application;
/**
 * Stack data structure. Implements StackInterface
 * @author Marcus Brooks
 *
 */
public class MyStack<T> implements StackInterface<T> {
	private T[] baseArray;
	private int currentSize;
	private final int DEFAULT_CAPACITY = 5;
	/**
	 * Default constructor which initializes the base array with the default capacity
	 */
	public MyStack() {
		T[] tempStack = (T[]) new Object[DEFAULT_CAPACITY];
		baseArray = tempStack;
	}
	/**
	 * Parameterized constructor which initializes the base array with a custom capacity
	 * @param size
	 */
	public MyStack(int size) {
		T[] tempStack = (T[]) new Object[size];
		baseArray = tempStack;
	}
	/**
	 * Returns a boolean representing if the stack is empty
	 * @return a boolean representing if the stack is empty
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}
	/**
	 * Returns a boolean representing if the stack is full
	 * @return a boolean representing if the stack is full
	 */
	public boolean isFull() {
		return currentSize == baseArray.length;
	}
	/**
	 * Removes a piece of data from the top of the stack
	 * @return the data removed from the top of the stack
	 */
	public T pop() {
		T removedData = null;
		if (!isEmpty()) {
			removedData = baseArray[currentSize - 1];
			baseArray[currentSize] = null;
			currentSize--;
		}
		return removedData;
	}
	/**
	 * Returns the size of the stack
	 * @return the size of the stack
	 */
	public int size() {
		return this.currentSize;
	}
	/**
	 * Adds a piece of data to the top of the stack
	 * @param e the piece of data to be added to the top of the stack
	 * @return a boolean representing whether or not the data was added to the stack
	 */
	public boolean push(T e) {
		if (!isFull()) {
			baseArray[currentSize] = e;
			currentSize++;
			return true;
		}
		return false;
	}
	/**
	 * Returns an array representation of the stack
	 * @return an array representation of the stack
	 */
	public T[] toArray() {
		T[] newArray = ((T[]) new Object[currentSize]);
		for (int i = 0; i < currentSize; i++) {
			newArray[i] = baseArray[i];
			//This is a shallow copy, change if needed.
		}
		return newArray;
	}
}