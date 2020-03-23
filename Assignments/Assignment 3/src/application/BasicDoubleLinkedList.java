package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * A basic doubly-linked linked list with a head and a tail reference.
 * @author Marcus Brooks
 *
 * @param <T> Generic Type
 */
public class BasicDoubleLinkedList<T> {
	protected Node<T> head = null;
	protected Node<T> tail = null;
	protected int length = 0;
	
	/**
	 * Adds the passed data at the end of the linked list
	 * @param data The data to be added
	 * @return The linked list
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		if (this.length == 0) {
			head = new Node<T>(data);
			head.setNext(head);
			head.setPrevious(head);
			tail = head;
		} else {
			tail.setNext(new Node<T>(data));
			tail.getNext().setPrevious(tail);
			tail = tail.getNext();
			tail.setNext(head);
			head.setPrevious(tail);
		}
		length++;
		return this;
	}
	/**
	 * Adds the passed data to the front of the linked list
	 * @param data The data to be added
	 * @return The linked list
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		if (this.length == 0) {
			head = new Node<T>(data);
			head.setNext(head);
			head.setPrevious(head);
			tail = head;
		} else {
			Node<T> newNode = new Node<T>(data);
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
			head.setPrevious(tail);
			tail.setNext(head);
		}
		length++;
		return this;
	}
	/**
	 * Returns the first element in the linked list
	 * @return The first element in the linked list
	 */
	public T getFirst() {
		return head.getData();
	}
	/**
	 * Returns the last element in the linked list
	 * @return The last element in the linked list
	 */
	public T getLast() {
		return tail.getData();
	}
	/**
	 * Returns the size of the linked list
	 * @return The size of the linked list
	 */
	public int getSize() {
		return this.length;
	}
	/**
	 * Returns a new iterator object
	 * @return A new iterator object
	 */
	public ListIterator<T> iterator() {
		return new Iterator();
	}
	/**
	 * Removes the specified target data from the linked list using the passed comparator
	 * @param targetData The data to be removed
	 * @param comparator The comparator to be used to make comparisons
	 * @return The linked list
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) { //double check the logic of this method
		if (head == null) {
			return this;
		}
		Node<T> currentNode = head;
		Node<T> nextNode = head.getNext();
		if (comparator.compare(currentNode.getData(), targetData) == 0) {
			head = nextNode;
			head.setPrevious(tail);
			tail.setNext(head);
			length--;
			if (length == 0) {
				tail = null;
				head = null;
			}
			return this;
		}
		while (nextNode != head) {
			if (comparator.compare(nextNode.getData(), targetData) == 0) {
				currentNode.setNext(nextNode.getNext());
				nextNode.getNext().setPrevious(currentNode);
				if (nextNode == tail) { //might need to use comparator here
					tail = currentNode;
				}
				length--;
				break;
			}
			currentNode = currentNode.getNext();
			nextNode = nextNode.getNext();
		}
		return this;
	}
	/**
	 * Removes and returns the data from the first element in the linked list
	 * @return The data within the removed first element in the linked list
	 */
	public T retrieveFirstElement() {
		if (head == null) {
			return null;
		}
		
		T firstElement = head.getData();
		
		if (tail == head) {
			tail = null;
			head = null;
			length--;
			return firstElement;
		}
		head = head.getNext();
		head.setPrevious(tail);
		tail.setNext(head);
		length--;
		
		return firstElement;
	}
	/**
	 * Removes and returns the data from the last element in the linked list
	 * @return The data within the removed last element in the linked list
	 */
	public T retrieveLastElement() {
		if (head == null) {
			return null;
		}
		T lastElement = tail.getData();
		if (head == tail) {
			head = null;
			tail = null;
			length--;
			return lastElement;
		}
		tail = tail.getPrevious();
		tail.setNext(head);
		head.setPrevious(tail);
		length--;
		
		return lastElement;
	}
	/**
	 * Creates an array list representation of the linked list
	 * @return An array list representation of the linked list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> resultList = new ArrayList<T>();
		if(head == null) {
			return resultList;
		}
		Node<T> currentNode = head;
		while (true) {
			resultList.add(currentNode.getData());
			if (currentNode == tail) {
				break;
			}
			currentNode = currentNode.getNext();
		}
		return resultList;
	}
	/**
	 * The Node inner class which contains references to the data it holds, the next node, and the previous node.
	 * @author Marcus Brooks
	 *
	 * @param <T> Generic Type
	 */
	protected class Node<T> {
		private T data;
		private Node<T> next;
		private Node<T> previous;
		
		/**
		 * Parameterized constructor which initializes the data field with the passed data
		 * @param data The data to be stored
		 */
		public Node(T data) {
			this.data = data;
		}
		/**
		 * Sets the next node in the linked list
		 * @param nextNode The next node in the linked list
		 */
		public void setNext(Node<T> nextNode) {
			this.next = nextNode;
		}
		/**
		 * Returns the next node in the linked list
		 * @return  The next node in the linked list
		 */
		public Node<T> getNext() {
			return this.next;
		}
		/**
		 * Sets the previous node in the linked list
		 * @param previousNode The previous node in the linked list
		 */
		public void setPrevious(Node<T> previousNode) {
			this.previous = previousNode;
		}
		/**
		 * Returns the previous node in the linked list
		 * @return The previous node in the linked list
		 */
		public Node<T> getPrevious() {
			return this.previous;
		}
		/**
		 * Returns the data stored in this node
		 * @return The data stored in this node
		 */
		public T getData() {
			return this.data;
		}
		
	}
	/**
	 * The Iterator inner class which allows traversal both forward and backward through the linked list it is created from.
	 * @author Marcus Brooks
	 *
	 */
	protected class Iterator implements ListIterator<T> {
		private Node<T> nextNode = head;
		private Node<T> previousNode = tail;
		private int position = 0;
		
		/**
		 * Moves the iterator forward one position in the linked list. Throws NoSuchElementException if there is no next node
		 * @return The data from the node passed by the iterator moving forward one position
		 */
		public T next() {
			T result = null;
			if(hasNext()) {
				result = nextNode.getData();
				nextNode = nextNode.getNext();
				previousNode = previousNode.getNext();
				position++;
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}
		/**
		 * Returns true if there is a next node in the linked list, false if there is not
		 * @return True if there is a next node in the linked list, false if there is not
		 */
		public boolean hasNext() {
			if (position < length) {
				return true;
			} else {
				return false;
			}
		}
		/**
		 * Returns true if there is a previous node in the linked list, false if there is not
		 * @return True if there is a previous node in the linked list, false if there is not
		 */
		public boolean hasPrevious() {
			if (position > 0) {
				return true;
			} else {
				return false;
			}
		}
		/**
		 * Moves the iterator backward one position in the linked list. Throws NoSuchElementException if there is no previous node
		 * @return The data from the node passed by the iterator moving backward one position
		 */
		public T previous() {
			T result = null;
			if (hasPrevious()) {
				result = previousNode.getData();
				previousNode = previousNode.getPrevious();
				nextNode = nextNode.getPrevious();
				position--;
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}
		/**
		 * Throws UnsupportedOperationException since this operation is not supported
		 */
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		/**
		 * Throws UnsupportedOperationException since this operation is not supported
		 */
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		/**
		 * Throws UnsupportedOperationException since this operation is not supported
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
		/**
		 * Throws UnsupportedOperationException since this operation is not supported
		 */
		public void set(T t) {
			throw new UnsupportedOperationException();
		}
		/**
		 * Throws UnsupportedOperationException since this operation is not supported
		 */
		public void add(T t) {
			throw new UnsupportedOperationException();
		}
 	}
}