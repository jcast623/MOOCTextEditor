/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> longerList2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		longerList2 = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList2.add(LONG_LIST_LENGTH - i);
		}
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		//test remove at end
		int b = longerList.remove(LONG_LIST_LENGTH-1);
		assertEquals("Remove: check b is correct ", 9, b);
		assertEquals("Remove: check size is correct ", 9, longerList.size());
		assertEquals("Remove: check new last element is correct ", (Integer)8, longerList.get(longerList.size() - 1));
		
		//test remove middle
		int mid = longerList2.size()/2;
		int c = longerList2.remove(mid);
		assertEquals("Remove: check c is correct ", 5, c);
		assertEquals("Remove: check size is correct ", 9, longerList2.size());
		assertEquals("Remove: check new element at mid is correct ", (Integer)4, longerList2.get(mid));
		
		//Out of bounds
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			shortList.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		//Test add to empty list
		try{
			emptyList.add(null);
			fail("Check null pointer exception");
		}catch(NullPointerException e){
			
		}
		emptyList.add(7);
		assertEquals("Add: check element added to empty list ", (Integer)7,emptyList.get(emptyList.size -1));
		
		//Test add to existing list
		longerList.add(138);
		assertEquals("Add: check element added to existing list ", (Integer)138,longerList.get(longerList.size -1));
		
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		//Test empty list size
		assertEquals("Size: check empty list size", 0, emptyList.size());
				
		//Test populated list size
		assertEquals("Size: check empty list size", LONG_LIST_LENGTH, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try{
			list1.add(0,null);
			fail("Check null pointer exception");
		}catch(NullPointerException e){
			
		}
		
		//Test add to start
		list1.add(0, 138);
		assertEquals("Add: check element added to beginning of existing list ", (Integer)138, list1.get(0));
		
		//Test add to end
		int end = list1.size();
		list1.add(end, 28);
		assertEquals("Add: check element added to end of existing list ", (Integer)28, list1.get(end));
		
		//Test add to 0 < i < end
		int mid = list1.size()/2;
		list1.add(mid, 86);
		assertEquals("Add: check element added to middle of existing list ", (Integer)86, list1.get(mid));
		
		//Test add to empty list
		emptyList.add(0, 40);
		assertEquals("Add: check element added to empty list ", (Integer)40, emptyList.get(0));
		
		//Out of bounds tests
		try{
			list1.add(-1, 99);
			fail("Check out of bounds");
		}catch(IndexOutOfBoundsException e){
			
		}
		
		try{
			list1.add(list1.size()+1, 99);
			fail("Check out of bounds");
		}catch(IndexOutOfBoundsException e){
			
		}
		
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try{
			shortList.set(0, null);
			fail("check null pointer exception");
		}catch(NullPointerException e){
			
		}
	    //Set at start
		String a = shortList.set(0, "AA");
	    assertEquals("Set: check 'A' was removed ", "A", a);
	    assertEquals("Set: check 'AA' was inserted at position 0", "AA", shortList.get(0));
	    assertEquals("Set: check size unchanged ", 2, shortList.size());
	    
	    //Set at end
	    String b = shortList.set(1, "BB");
	    assertEquals("Set: check 'B' was removed ", "B", b);
	    assertEquals("Set: check 'BB' was inserted at position 1 ", "BB", shortList.get(1));
	    assertEquals("Set: check 'AA' index unchanged ", "AA", shortList.get(0));
	    assertEquals("Set: check size unchanged ", 2, shortList.size());
	    
	    //Set in middle
	    int c = longerList.set(5, 55);
	    assertEquals("Set: check 5 was removed ", 5, c);
	    assertEquals("Set: check 55 was inserted at position 5 ", (Integer)55, longerList.get(5));
	    assertEquals("Set: check index of 4 unchanged ", (Integer)4, longerList.get(4));
	    assertEquals("Set: check index of 6 unchanged ", (Integer)6, longerList.get(6));
	    assertEquals("Set: check size unchanged ", LONG_LIST_LENGTH, longerList.size());
	    
	    //Out of bounds
	    try {
			longerList.set(-1, 99);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	    
	    try {
			longerList.set(LONG_LIST_LENGTH, 99);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
