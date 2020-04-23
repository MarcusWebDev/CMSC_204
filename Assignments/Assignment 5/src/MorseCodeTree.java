package application;

import java.util.ArrayList;
/**
 * The MorseCodeTree class contains methods for creating and utilizing a tree of nodes that contain the english translation of morse code.
 * @author Marcus Brooks
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	
	private TreeNode<String> rootNode;
	
	/**
	 * No arg constructor that initializes the morse code tree by calling the buildTree() method
	 */
	public MorseCodeTree() {
		this.buildTree();
	}
	/**
	 * Adds a node to the morse code tree
	 * @param root The root node of the tree
	 * @param code The morse code that determines where in the tree the letter gets added
	 * @param letter The English letter to be added to the morse code tree
	 */
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length() == 0) {
			System.out.println("No morse code supplied");
			return;
		}
		if (letter.length() == 0) {
			System.out.println("No letter supplied");
			return;
		}
		
		if (code.length() > 1) {
			if (code.charAt(0) == '.') {
				addNode(root.getLeftNode(), code.substring(1, code.length()), letter);
			} else if (code.charAt(0) == '-') {
				addNode(root.getRightNode(), code.substring(1, code.length()), letter);
			} else {
				System.out.println("Invalid character: '" + code.charAt(0) + "'. The code can only contain the characters '.' or '-'.");
			}
		} else if (code.charAt(0) == '.') {
			root.setLeftNode(new TreeNode<String>(letter));
		} else if (code.charAt(0) == '-') {
			root.setRightNode(new TreeNode<String>(letter));
		} else {
			System.out.println("Invalid character: '" + code.charAt(0) + "'. The code can only contain the characters '.' or '-'.");
		}
	}
	/**
	 * Builds the morse code tree by utilizing the addNode() method with the morse code representation of each letter in the English alphabet.
	 */
	public void buildTree() {
		this.rootNode = new TreeNode<String>("");
		this.addNode(this.rootNode, ".", "e");
		this.addNode(this.rootNode, "-", "t");
		this.addNode(this.rootNode, "..", "i");
		this.addNode(this.rootNode, ".-", "a");
		this.addNode(this.rootNode, "-.", "n");
		this.addNode(this.rootNode, "--", "m");
		this.addNode(this.rootNode, "...", "s");
		this.addNode(this.rootNode, "..-", "u");
		this.addNode(this.rootNode, ".-.", "r");
		this.addNode(this.rootNode, ".--", "w");
		this.addNode(this.rootNode, "-..", "d");
		this.addNode(this.rootNode, "-.-", "k");
		this.addNode(this.rootNode, "--.", "g");
		this.addNode(this.rootNode, "---", "o");
		this.addNode(this.rootNode, "....", "h");
		this.addNode(this.rootNode, "...-", "v");
		this.addNode(this.rootNode, "..-.", "f");
		this.addNode(this.rootNode, ".-..", "l");
		this.addNode(this.rootNode, ".--.", "p");
		this.addNode(this.rootNode, ".---", "j");
		this.addNode(this.rootNode, "-...", "b");
		this.addNode(this.rootNode, "-..-", "x");
		this.addNode(this.rootNode, "-.-.", "c");
		this.addNode(this.rootNode, "-.--", "y");
		this.addNode(this.rootNode, "--..", "z");
		this.addNode(this.rootNode, "--.-", "q");
	}
	/**
	 * Throws the UnsupportedOperationException because this operation is unsupported
	 * @throws UnsupportedOperationException
	 * @return The morse code tree
	 */
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	/**
	 * Utilizes the fetchNode method to retrieve the English representation of the passed morse code
	 * @param code The morse code to be decrypted
	 * @return The English letter representation of the passed morse code
	 */
	public String fetch(String code) {
		return this.fetchNode(this.rootNode, code);
	}
	/**
	 * Retrieves the English representation of the passed morse code
	 * @param root The root node to begin traversing from
	 * @param code The morse code to be decrypted
	 * @return The English letter representation of the passed morse code, or an error string stating something went wrong
	 */
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.length() == 0) {
			return "No morse code supplied";
		}
		if (code.length() > 1) {
			if (code.charAt(0) == '.') {
				return fetchNode(root.getLeftNode(), code.substring(1, code.length()));
			} else if (code.charAt(0) =='-') {
				return fetchNode(root.getRightNode(), code.substring(1, code.length()));
			} else {
				return "Invalid character. The code can only contain the characters '.' or '-'.";
			}
		} else if (code.length() == 1) {
			if (code.charAt(0) == '.') {
				return root.getLeftNode().getData();
			} else if (code.charAt(0) == '-') {
				return root.getRightNode().getData();
			} else {
				return "Invalid character. The code can only contain the characters '.' or '-'.";
			}
		} else {
			return root.getData();
		}
	}
	/**
	 * Adds the passed letter to the morse code tree by using the passed code to determine it's position
	 * @param code The morse code representation of the passed letter
	 * @param letter The letter representation of the passed morse code
	 * @return The morse code tree
	 */
	public MorseCodeTree insert(String code, String letter) {
		addNode(this.rootNode, code, letter);
		return this;
	}
	/**
	 * Adds the morse code tree's nodes to the passed array list inorder.
	 */
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root == null) {
			return;
		}
		if (root.getLeftNode() != null) {
			LNRoutputTraversal(root.getLeftNode(), list);
		}
		list.add(root.getData());
		if (root.getRightNode() != null) {
			LNRoutputTraversal(root.getRightNode(), list);
		}
	}
	/**
	 * Returns the root node of the morse code tree
	 * @return The root node of the morse code tree
	 */
	public TreeNode<String> getRoot() {
		return this.rootNode;
	}
	/**
	 * Sets the root node of the morse code tree
	 * @param newNode The node to set as the root node of the morse code tree
	 */
	public void setRoot(TreeNode<String> newNode) {
		this.rootNode = newNode;
	}
	/**
	 * Creates an in-order array list representation of the morse code tree
	 * @return An in-order array list representation of the morse code tree
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> inOrderTreeArrayList = new ArrayList<String>();
		LNRoutputTraversal(this.rootNode, inOrderTreeArrayList);
		return inOrderTreeArrayList;
	}
	/**
	 * Throws the UnsupportedOpertionException because this operation is unsupported
	 * @throws UnsupportedOperationException
	 * @return The morse code tree
	 */
	public MorseCodeTree update() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
}