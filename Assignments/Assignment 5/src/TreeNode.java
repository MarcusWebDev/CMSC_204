package application;

/**
 * A node class for a binary tree.
 * @author Marcus Brooks
 *
 * @param <T> Generic parameter
 */
public class TreeNode<T> {
	private T data;
	private TreeNode<T> leftNode;
	private TreeNode<T> rightNode;
	
	/**
	 * Parameterized constructor that initializes the data stored in the node with the passed data and initializes the left and right child nodes to null
	 * @param dataNode The data to be stored in the node
	 */
	public TreeNode(T dataNode) {
		data = dataNode;
		leftNode = null;
		rightNode = null;
	}
	/**
	 * Parameterized constructor that creates a deep copy of the passed node
	 * @param node The node to create a deep copy of
	 */
	public TreeNode(TreeNode<T> node) {
		this.data = node.getData();
		this.leftNode = node.getLeftNode();
		this.rightNode = node.getRightNode();
	}
	/**
	 * Returns the data stored in this node
	 * @return The data stored in this node
	 */
	public T getData() {
		return this.data;
	}
	/**
	 * Returns the left child node of this node
	 * @return The left child node of this node
	 */
	public TreeNode<T> getLeftNode() {
		return this.leftNode;
	}
	/**
	 * Sets the left child node of this node
	 * @param leftNode The left child node of this node
	 */
	public void setLeftNode(TreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}
	/**
	 * Returns the right child node of this node
	 * @return The right child node of this node
	 */
	public TreeNode<T> getRightNode() {
		return this.rightNode;
	}
	/**
	 * Sets the right child node of this node
	 * @param rightNode The right child node of this node
	 */
	public void setRightNode(TreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}
}