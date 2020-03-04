package application;

public class MyQueue<T> implements QueueInterface<T> {
	
	private T[] baseArray;
	private int DEFAULT_CAPACITY = 25;
	private int currentSize;
	
	public MyQueue() {
		baseArray = (T[]) new Object[DEFAULT_CAPACITY];
	}
	public MyQueue(int size) {
		baseArray = (T[]) new Object[size];
	}
	
	public boolean isEmpty() {
		return this.currentSize == 0;
	}
	public boolean isFull() {
		return this.currentSize == baseArray.length;
	}
	public T dequeue() {
		T removedData = null;
		if(!isEmpty()) {
			removedData = baseArray[currentSize];
			baseArray[currentSize] = null;
			currentSize--;
		}
		return removedData;
	}
	public boolean enqueue(T e) {
		if(!isFull()) {
			baseArray[currentSize] = e;
			currentSize++;
			return true;
		}
		return false;
	}
	public T[] toArray() {
		T[] newArray = ((T[]) new Object[currentSize]);
		for (int i = 0; i < currentSize; i++) {
			newArray[i] = baseArray[i];
			//This is a shallow copy, change if needed
		}
		return newArray;
	}
	public int size() {
		return this.currentSize;
	}
}