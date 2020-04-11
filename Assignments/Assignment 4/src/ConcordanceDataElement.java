package application;

/**
 * The ConcordanceDataElement stores a word and the line numbers it appears in the document
 * @author Marcus Brooks
 */
import java.util.LinkedList;
import java.util.ListIterator;

public class ConcordanceDataElement implements Comparable<ConcordanceDataElement>{
	private String word;
	private LinkedList<Integer> lineNumbers;
	
	/**
	 * Parameterized constructor which initializes the word with the passed word.
	 * @param word
	 */
	public ConcordanceDataElement(String word) {
		lineNumbers = new LinkedList<Integer>();
		this.word = word;
	}
	/**
	 * Adds the line number to the line number linked list if it doesn't exist there already
	 * @param lineNum the line number the word appears on
	 */
	public void addPage(int lineNum) {
		if(!lineNumbers.contains(lineNum)) {
			lineNumbers.add(lineNum);
		}
	}
	/**
	 * Compares the word in this concordance data element with the word in the passed concordance data element. Returns 1 if this word is greater, 0 if they're equal, and -1 if this word is lesser
	 * @return number representing whether or not this word is greater than the passed word
	 */
	public int compareTo(ConcordanceDataElement arg0) {
		if (this.getWord().compareTo(arg0.getWord()) > 0) {
			return 1;
		} else if (this.getWord().compareTo(arg0.getWord()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	/**
	 * Returns the line numbers this word appears on
	 * @return the line numbers this word appears on
	 */
	public LinkedList<Integer> getList() {
		return lineNumbers;
	}
	/**
	 * Returns the word stored in this data element
	 * @return the word stored in this data element
	 */
	public String getWord() {
		return this.word;
	}
	/**
	 * returns the hash code for the data element
	 * @return the hash code for the data element
	 */
	public int hashCode() {
		return word.hashCode();
	}
	/**
	 * returns the word followed by the line numbers it appears on
	 * @return the word followed by the line numbers it appears on
	 */
	public String toString() {
		ListIterator<Integer> iterator = lineNumbers.listIterator();
		String result = word + ": ";
		while (iterator.hasNext()) {
			result = result + iterator.next();
		}
		return result;
	}
}