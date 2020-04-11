package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a concordance array list or file using a passed string or input file
 * @author Marcus Brooks
 *
 */
public class ConcordanceDataManager implements ConcordanceDataManagerInterface {
	ConcordanceDataStructure hashTable;
	
	/**
	 * Initializes the hash table with the default estimated size
	 */
	public ConcordanceDataManager() {
		hashTable = new ConcordanceDataStructure(500);
	}
	/**
	 * creates an array list of strings representing each word and it's line numbers in alphabetical order
	 * @return an array list of strings representing each word and it's line numbers in alphabetical order
	 */
	public ArrayList<String> createConcordanceArray(String input) {
		Scanner scanner = new Scanner(input);
		int lineNum = 0;
		while (scanner.hasNextLine()) {
			Scanner lineScanner = new Scanner(scanner.nextLine());
			lineNum++;
			while (lineScanner.hasNext()) {
				String word = lineScanner.next();
				word = word.replaceAll("[^a-zA-Z0-9'\\s]+","").toLowerCase();
				if (word.length() >= 3 && word.equals("the") == false && word.equals("and") == false) {
					hashTable.add(word, lineNum);
				}
			}
		}
		ArrayList<String> words = hashTable.showAll();
		return hashTable.showAll();
	}
	/**
	 * Creates a concordance using the input file and writes it to the output file
	 * @return boolean representing if the task was successful or not
	 */
	public boolean createConcordanceFile(File input, File output) throws FileNotFoundException {
		Scanner scanner = new Scanner(input);
		PrintWriter writer = new PrintWriter(output);
		int lineNum = 0;
		while (scanner.hasNextLine()) {
			lineNum++;
			Scanner lineScanner = new Scanner(scanner.nextLine());
			while (lineScanner.hasNext()) {
				String word = lineScanner.next().replaceAll("[^a-zA-Z0-9'\\s]+","").toLowerCase();
				if (word.length() >= 3 && word.equals("the") == false && word.equals("and") == false) {
					hashTable.add(word, lineNum);
				}
			}
		}
		ArrayList<String> words = hashTable.showAll();
		for (int i = 0; i < words.size(); i++) {
			writer.print(words.get(i));
		}
		scanner.close();
		writer.close();

		return true;
	}
}