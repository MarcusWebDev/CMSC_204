package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * Hash table that stores ConcordanceDataElements
 * @author Marcus Brooks
 *
 */
public class ConcordanceDataStructure implements ConcordanceDataStructureInterface {
	
	private LinkedList<ConcordanceDataElement>[] baseArray;
	private ArrayList<Integer> wordLocations;
	
	/**
	 * Initializes the hash table using the estimated number of words in the document and a load factor of 1.5
	 * @param num The estimated number of words in the document
	 */
	public ConcordanceDataStructure(int num) {
		int prime = fourKPlusThree(num, 1.5);
		baseArray = new LinkedList[prime];
		for (int i = 0; i < baseArray.length; i++) {
			baseArray[i] = new LinkedList<ConcordanceDataElement>();
		}
		wordLocations = new ArrayList<Integer>();
	}
	/**
	 * Initializes the hash table with the passed size
	 * @param test A string used for testing
	 * @param size The size of the hash table
	 */
	public ConcordanceDataStructure(String test, int size) {
		baseArray = new LinkedList[size];
		for (int i = 0; i < baseArray.length; i++) {
			baseArray[i] = new LinkedList<ConcordanceDataElement>();
		}
		wordLocations = new ArrayList<Integer>();
	}
	/**
	 * Adds the word to the hash table and adds the line number to the word's linked list if it isn't there already
	 * @param term The word to be added
	 * @param lineNum The line number the word is on
	 */
	public void add(String term, int lineNum) {
		int index = Math.abs(term.hashCode() % baseArray.length);
		System.out.println(term + ": " + index);
		boolean locationExists = false;
		for (int i = 0; i < wordLocations.size(); i++) {
			if (wordLocations.get(i) == index) {
				locationExists = true;
				break;
			}
		}
		if (locationExists == false) {
			wordLocations.add(index);
		}
		ListIterator<ConcordanceDataElement> hashIterator = baseArray[index].listIterator();
		boolean hasWord = false;
		while (hashIterator.hasNext()) {
			ConcordanceDataElement currentElement = hashIterator.next();
			if (currentElement.getWord().equals(term)) {
				hasWord = true;
				ListIterator<Integer> elementIterator = currentElement.getList().listIterator();
				boolean hasLineNum = false;
				while (elementIterator.hasNext()) {
					int currentLineNum = elementIterator.next();
					if (lineNum == currentLineNum) {
						hasLineNum = true;
					}
				}
				if(hasLineNum == false) {
					currentElement.getList().add(lineNum);
				}
			}
		}
		if (hasWord == false) {
			ConcordanceDataElement newElement = new ConcordanceDataElement(term);
			newElement.getList().add(lineNum);
			baseArray[index].add(newElement);
		}
	}
	/**
	 * Returns an array list of linked lists of line numbers at the specified index of the hash table. Takes the bucket at the index and returns each word in the bucket's line numbers
	 * @param index The index of the bucket to retrieve line numbers from
	 * @return the line numbers of the words in the bucket
	 */
	public ArrayList<LinkedList<Integer>> getPageNumbers(int index) {
		ListIterator<ConcordanceDataElement> iterator = baseArray[index].listIterator();
		ArrayList<LinkedList<Integer>> bucket = new ArrayList<LinkedList<Integer>>();
		while (iterator.hasNext()) {
			bucket.add(iterator.next().getList());
		}
		return bucket;
	}
	/**
	 * returns the table size of the hash table
	 * @return the table size of the hash table
	 */
	public int getTableSize() {
		return baseArray.length;
	}
	/**
	 * returns the words in the bucket at the specified index
	 * @return the words in the bucket at the specified index
	 */
	public ArrayList<String> getWords(int index) {
		ListIterator<ConcordanceDataElement> iterator = baseArray[index].listIterator();
		ArrayList<String> bucket = new ArrayList<String>();
		while (iterator.hasNext()) {
			bucket.add(iterator.next().getWord());
		}
		return bucket;
	}
	/**
	 * Returns an array list of strings that represent each word in the hash table and their line numbers in alphabetical order
	 * @return an array list of strings that represent each word in the hash table and their line numbers in alphabetical order
	 */
	public ArrayList<String> showAll() {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < wordLocations.size(); i++) {
			ListIterator<ConcordanceDataElement> iterator = baseArray[wordLocations.get(i)].listIterator();
			while (iterator.hasNext()) {
				ConcordanceDataElement currentElement = iterator.next();
				ListIterator<Integer> lineNumberIterator = currentElement.getList().listIterator();
				String word = currentElement.getWord();
				word = word + ": ";
				int index = 0;
				while (lineNumberIterator.hasNext()) {
					if(index == 0) {
						word = word + lineNumberIterator.next() ;
					} else {
						word =  word + ", " + lineNumberIterator.next();
					}
					index++;
				} 
				word = word + "\n";
				words.add(word);
			}
		}
		Collections.sort(words);
		return words;
	}
	/**
	 * Calculates the 4k + 3 prime to use for the hash table
	 * @param n the estimated amount of words in the document
	 * @param loadFactor the loading factor 
	 * @return The 4k+3 prime
	 */
	public int fourKPlusThree(int n, double loadFactor) {
		boolean isFourKPlusThree = false;
		boolean aPrime = false;
		int prime, highDivisor, d;
		prime = (int)(n/loadFactor);
		if (prime % 2 == 0) {
			prime = prime + 1;
		}
		while (isFourKPlusThree == false) {
			while (aPrime == false) {
				highDivisor = (int)(Math.sqrt(prime) + 0.5);
				for(d = highDivisor; d > 1; d--) {
					if (prime % d == 0) {
						break;
					}
				}
				if (d != 1) {
					prime = prime + 2;
				} else {
					aPrime = true;
				}
			}
			if ((prime - 3) % 4 == 0) {
				isFourKPlusThree = true;
			} else {
				prime = prime + 2;
				aPrime = false;
			}
		}
		return prime;
	}
}