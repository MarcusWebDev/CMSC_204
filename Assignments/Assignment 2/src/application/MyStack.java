package application;

public class MyStack<T> implements StackInterface<T> {
	private T[] baseArray;
	private int currentSize;
	private int DEFAULT_CAPACITY;
	
	public MyStack() {
		baseArray = (T[]) new Object[DEFAULT_CAPACITY];
	}
	public MyStack(int size) {
		baseArray = (T[]) new Object[size];
	}
	public boolean isEmpty() {
		return currentSize == 0;
	}
	public boolean isFull() {
		return currentSize == baseArray.length;
	}
	public T pop() {
		T removedData = null;
		if (!isEmpty()) {
			removedData = baseArray[currentSize];
			baseArray[currentSize] = null;
			currentSize--;
		}
		return removedData;
	}
	public int size() {
		return this.currentSize;
	}
	public boolean push(T e) {
		if (!isFull()) {
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
			//This is a shallow copy, change if needed.
		}
		return newArray;
	}
}