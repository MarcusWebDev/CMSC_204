package application;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<Integer> sorted;
	IntegerComparator comparator;
	ArrayList<Integer> arrayList;
	final Integer a = 15;
	final Integer b = 10;
	final Integer c = 20;
	final Integer d = 5;
	final Integer e = 30;
	final Integer f = 25;
	
	@Before
	public void setUp() {
		comparator = new IntegerComparator();
		sorted = new SortedDoubleLinkedList(comparator);
	}
	@After
	public void tearDown() {
		sorted = null;
		arrayList = null;
		comparator = null;
	}
	@Test
	public void testAdd() {
		sorted.add(a);
		sorted.add(b);
		sorted.add(c);
		sorted.add(d);
		sorted.add(e);
		sorted.add(f);
		arrayList = sorted.toArrayList();
		assertEquals(d, arrayList.get(0));
		assertEquals(b, arrayList.get(1));
		assertEquals(a, arrayList.get(2));
		assertEquals(c, arrayList.get(3));
		assertEquals(f, arrayList.get(4));
		assertEquals(e, arrayList.get(5));
	}
	private class IntegerComparator implements Comparator<Integer>
	{

		@Override
		public int compare(Integer arg0, Integer arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}