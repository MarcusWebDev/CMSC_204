package application;
/**
 * 
 * @author Marcus Brooks
 *
 */
public class ArraySum {
	public int sumOfArray(Integer[] array, int endIndex) {
		if (endIndex < 0) {
			return 0;
		}
		return array[endIndex] + sumOfArray(array, endIndex - 1);
	}
}