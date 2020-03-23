package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> basic;
	ArrayList<String> arrayList;
	StringComparator comparator;
	final String a = "Marcus";
	final String b = "Brooks";
	final String c = "Sarita";
	final String d = "Searing";
	final String e = "Tom Nook";
	final String f = "Isabelle";
	
	
	@Before
	public void setUp() {
		basic = new BasicDoubleLinkedList<String>();
		comparator = new StringComparator();
	}
	@After
	public void tearDown() {
		basic = null;
		arrayList = null;
		comparator = null;
	}
	@Test
	public void testAddToEnd() {
		basic.addToEnd(c);
		basic.addToEnd(d);
		arrayList = basic.toArrayList();
		assertEquals(d, arrayList.get(arrayList.size() - 1));
	}
	@Test
	public void testAddToFront() {
		basic.addToFront(c);
		basic.addToFront(d);
		arrayList = basic.toArrayList();
		assertEquals(d, arrayList.get(0));
	}
	@Test
	public void testGetFirst() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		assertEquals(e, basic.getFirst());
	}
	@Test
	public void testGetLast() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		assertEquals(a, basic.getLast());
	}
	@Test
	public void testGetSize() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		assertEquals(4, basic.getSize());
	}
	@Test
	public void testRemove() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		basic.remove(c, comparator);
		arrayList = basic.toArrayList();
		assertEquals(e, arrayList.get(0));
		assertEquals(d, arrayList.get(1));
		assertEquals(a, arrayList.get(2));
	}
	@Test
	public void testRetrieveFirstElement() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		assertEquals(e, basic.retrieveFirstElement());
		arrayList = basic.toArrayList();
		assertEquals(c, arrayList.get(0));
	}
	@Test
	public void testRetrieveLastElement() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		assertEquals(a, basic.retrieveLastElement());
		arrayList = basic.toArrayList();
		assertEquals(d, arrayList.get(arrayList.size() - 1));
	}
	@Test
	public void testToArrayList() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		arrayList = basic.toArrayList();
		assertEquals(e, arrayList.get(0));
		assertEquals(c, arrayList.get(1));
		assertEquals(d, arrayList.get(2));
		assertEquals(a, arrayList.get(3));
	}
	@Test
	public void testIteratorHasNext() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		ListIterator<String> iterator = basic.iterator();
		assertEquals(true, iterator.hasNext());
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		assertEquals(false, iterator.hasNext());
	}
	@Test
	public void testIteratorHasPrevious() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		ListIterator<String> iterator = basic.iterator();
		assertEquals(false, iterator.hasPrevious());
		iterator.next();
		assertEquals(true, iterator.hasPrevious());
	}
	@Test
	public void testIteratorNext() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		ListIterator<String> iterator = basic.iterator();
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(a, iterator.next());
		try {
			iterator.next();
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	@Test
	public void testIteratorPrevious() {
		basic.addToFront(a);
		basic.addToFront(d);
		basic.addToFront(c);
		basic.addToFront(e);
		ListIterator<String> iterator = basic.iterator();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		assertEquals(a, iterator.previous());
		assertEquals(d, iterator.previous());
		assertEquals(c, iterator.previous());
		assertEquals(e, iterator.previous());
		try {
			iterator.previous();
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}