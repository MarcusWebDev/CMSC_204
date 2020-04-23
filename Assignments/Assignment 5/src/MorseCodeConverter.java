package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for decrypting morse code from a file or a string. Contains a printTree() method for testing.
 * @author Marcus Brooks
 *
 */
public class MorseCodeConverter {
	
	private static MorseCodeTree tree = new MorseCodeTree();
	
	/**
	 * Converts a file containing morse code to English
	 * @param codeFile The file containing morse code
	 * @return The decrypted string
	 * @throws FileNotFoundException 
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(codeFile);
		String decryptedString = "";
		
		while (scanner.hasNext()) {
			String codeLetter = scanner.next();
			if (codeLetter.equals("/")) {
				decryptedString = decryptedString + " ";
			} else {
				decryptedString = decryptedString + tree.fetch(codeLetter);
			}
		}
		scanner.close();
		return decryptedString;
	}
	/**
	 * Converts a string containing morse code to English
	 * @param code A string containiing morse code
	 * @return The decrypted string
	 */
	public static String convertToEnglish(String code) {
		Scanner scanner = new Scanner(code);
		String decryptedString = "";
		
		while (scanner.hasNext()) {
			String codeLetter = scanner.next();
			if (codeLetter.equals("/")) {
				decryptedString = decryptedString + " ";
			} else {
				decryptedString = decryptedString + tree.fetch(codeLetter);
			}
		}
		scanner.close();
		return decryptedString;
	}
	/**
	 * Prints an inorder string representation of the morse code tree
	 * @return An inorder string representation of the morse code tree
	 */
	public static String printTree() {
		ArrayList<String> treeArrayList = tree.toArrayList();
		String result = "";
		for (int i = 0; i < treeArrayList.size(); i++) {
			result = result + " " + treeArrayList.get(i);
		}
		return result;
	}
}