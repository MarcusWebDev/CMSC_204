package application;

import java.util.Comparator;

/**
 * A sorted doubly-linked linked list which contains references to the head and tail of the linked list.
 * @author Marcus Brooks
 *
 * @param <T> Generic Type
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	
	private Comparator<T> comparator;
	/**
	 * Parameterized constructor which takes a comparator to be used for determining where to add data to the linked list in ascending order
	 * @param comparator The comparator to be used for determining where to add data to the linked list in ascending order
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	/**
	 * Throws UnsupportedOperationException as this operation is unsupported
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * Throws UnsupportedOperationException as this operation is unsupported
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * Adds the data to the linked list in ascending order
	 * @param data The data to be added to the linked list
	 * @return The linked list
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		if (head == null) {
			head = new Node<T>(data);
			head.setNext(head);
			head.setPrevious(head); 
			tail = head;
			length++;
			return this;
		}
		Node<T> currentNode = head;
		while (currentNode != tail) {
			if (comparator.compare(currentNode.getData(), data) >= 0) { //If the current node is greater-than-or-equal-to the data to be added
				Node<T> newNode = new Node<T>(data);
				if (currentNode == head) { //If the current node is the head, add the data at the start of the linked list and make the newly added data the head
					newNode.setNext(head);
					newNode.setPrevious(tail);
					head.setPrevious(newNode);
					head = newNode;
					tail.setNext(head);
					length++;
					return this;
				}
				//If the current node is neither the head nor the tail, add the data behind the current node
				newNode.setNext(currentNode);
				newNode.setPrevious(currentNode.getPrevious());
				currentNode.getPrevious().setNext(newNode);
				currentNode.setPrevious(newNode);
				length++;
				return this;
			}
			currentNode = currentNode.getNext();
		}
		if (comparator.compare(tail.getData(), data) >= 0) { //If the tail is greater-than-or-equal-to the data to be added, add the data behind the tail
			Node<T> newNode = new Node<T>(data);
			newNode.setNext(tail);
			newNode.setPrevious(tail.getPrevious());
			tail.getPrevious().setNext(newNode);
			tail.setPrevious(newNode);
			if (length == 1) {
				head = newNode;
			}
			length++;
		} else { //If the data to be added is greater-than the tail, add the data at the end of the linked list and make the newly added data the tail
			Node<T> newNode = new Node<T>(data);
			newNode.setNext(head);
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			head.setPrevious(newNode);
			tail = newNode;
			length++;
		}
		return this;
	}
}