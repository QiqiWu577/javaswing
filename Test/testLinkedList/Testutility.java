/**
 * 
 */
package testLinkedList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utility.List;
import utility.SLL;

/**
 * @author 745319
 *
 */
class Testutility {

	List list;
	String a,b,c;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		list = new SLL();
		a = "A";
		b = "B";
		c = "C";
	}

	/**
	 * Test method for {@link utility.SLL#append(java.lang.Object)}.
	 * Appending when list is empty.
	 */
	@Test
	void testAppend1() {

		assertTrue(list.append(a));
		assertEquals(1, list.size(),"Appending to empty list failed");
		assertEquals(a, list.getLast(),"Appending to empty list failed");
	}
	
	/**
	 * Test method for {@link utility.SLL#append(java.lang.Object)}.
	 * Appending when list is not empty.
	 */
	@Test
	void testAppend2() {

		assertTrue(list.append(a));
		assertTrue(list.append(b));
		assertEquals(2, list.size(),"Appending to non-empty list failed");
		assertEquals(b, list.getLast(),"Appending to non-empty list failed");
	}

	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object)}.
	 * Adding an object in the first position when the list is empty.
	 */
	@Test
	void testAddObject1() {

		assertTrue(list.add(a));
		assertEquals(1, list.size(),"Adding to empty list failed");
		assertEquals(a, list.getLast(),"Adding to empty list failed");
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object)}.
	 * Adding an object in the first position when the list is not empty.
	 */
	@Test
	void testAddObject2() {

		assertTrue(list.add(a));
		assertTrue(list.add(b));
		assertEquals(2, list.size(),"Adding to non-empty list failed");
		assertEquals(b, list.get(),"Adding to non-empty list failed");
		assertEquals(a, list.getLast(),"Adding to non-empty list failed");
	}

	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in the specific position which is less 
	 * than 0 when the list is empty, which throws exception.
	 */
	@Test
	void testAddObjectInt1() {
		
		try
		{
			list.add(a, -1);
			assertTrue(false);
		}
		catch(IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
		
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in the specific position which 
	 * is greater than size when the list is empty,which throws exception.
	 */
	@Test
	void testAddObjectInt2() {
		
		try
		{
			list.add(b, list.size()+1);
			assertTrue(false);
		}
		catch(IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
		
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in the specific position which 
	 * is less than 0 when the list is not empty, which throws exception.
	 */
	@Test
	void testAddObjectInt3() {
		
		try
		{
			list.append(a);
			list.add(b, -1);
			assertTrue(false);
		}
		catch(IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in the specific position which 
	 * is greater than size when the list is not empty,which throws exception.
	 */
	@Test
	void testAddObjectInt4() {
		
		try
		{
			list.append(a);
			list.add(b, 2);
			assertTrue(false);
		}
		catch(IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in position 0
	 * when the list is empty.
	 */
	@Test
	void testAddObjectInt5() {

		assertTrue(list.add(a, 0));
		assertEquals(a,list.get(),"failed to add an object to the list in position 0 when the list is empty.");
		assertEquals(a,list.getLast(),"failed to add an object to the list in position 0 when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in position 0
	 * when the list is not empty.
	 */
	@Test
	void testAddObjectInt6() {
		
		assertTrue(list.append(a));
		assertTrue(list.add(b, 0));
		assertTrue(list.add(c,0));
		assertEquals(c, list.get(),"failed to add an object to the list in position 0 when the list is not empty.");
		assertEquals(a,list.getLast(),"failed to add an object to the list in position 0 when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in position 1
	 * when the list is not empty.
	 */
	@Test
	void testAddObjectInt7() {
		
		assertTrue(list.append(a));
		assertTrue(list.add(b, 0));
		assertTrue(list.add(c,1));
		assertEquals(b, list.get(),"failed to add an object to the list in position 1 when the list is not empty.");
		assertEquals(a,list.getLast(),"failed to add an object to the list in position 1 when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in (position >1 && position<size)
	 * when the list is not empty.
	 */
	@Test
	void testAddObjectInt8() {
		
		assertTrue(list.append(a));
		assertTrue(list.add(b));
		assertTrue(list.append(c));
		assertTrue(list.add(a,2));
		assertEquals(4, list.size(),"failed to add an object to the list in (position >1 && position<size) when the list is not empty.");
		assertEquals(b, list.get(),"failed to add an object to the list in (position >1 && position<size) when the list is not empty.");
		assertEquals(a,list.get(2),"failed to add an object to the list in (position >1 && position<size) when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#add(java.lang.Object, int)}.
	 * Adding an object to the list in (position = size)
	 * when the list is not empty.
	 */
	@Test
	void testAddObjectInt9() {
		
		assertTrue(list.append(a));
		assertTrue(list.append(b));
		assertTrue(list.append(c));
		assertTrue(list.add(a,3));
		assertEquals(4, list.size(),"failed to add an object to the list in (position = size) when the list is not empty.");
		assertEquals(a, list.get(),"failed to add an object to the list in (position = size) when the list is not empty.");
		assertEquals(a,list.getLast(),"failed to add an object to the list in (position = size) when the list is not empty.");
	}

	/**
	 * Test method for {@link utility.SLL#clear()}.
	 * Clearing the whole list.
	 */
	@Test
	void testClear() {

		assertTrue(list.append(a));
		assertTrue(list.append(b));
		assertTrue(list.append(c));
		assertTrue(list.add(a,3));
		list.clear();
		assertEquals(0,list.size(),"failed to clear the whole list");
		assertEquals(null,list.get(),"failed to clear the whole list");
		assertEquals(null,list.getLast(),"failed to clear the whole list");
	}

	/**
	 * Test method for {@link utility.SLL#remove()}.
	 * Removing the first element in the list when the list is empty.
	 */
	@Test
	void testRemove1() {

		list.remove();
		assertEquals(null,list.remove(),"failed to Removing the first element in the list when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#remove()}.
	 * Removing the first element in the list when the list is not empty.
	 */
	@Test
	void testRemove2() {
		
		assertTrue(list.append(a));
		assertTrue(list.append(b));
		assertTrue(list.append(c));
		assertEquals(a,list.remove(),"failed to Removing the first element in the list when the list is empty.");
		assertEquals(2,list.size(),"failed to Removing the first element in the list when the list is empty.");
		assertEquals(b,list.get(),"failed to Removing the first element in the list when the list is empty.");
	}

	/**
	 * Test method for {@link utility.SLL#removeLast()}.
	 * Remove the last element in the list when the list is empty.
	 */
	@Test
	void testRemoveLast1() {

		list.removeLast();
		assertEquals(null,list.removeLast(),"failed to Removing the last element in the list when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#removeLast()}.
	 * Remove the last element in the list when the size equals to 1 and 
	 * the list is not empty.
	 */
	@Test
	void testRemoveLast2() {
		
		assertTrue(list.append(a));
		list.removeLast();
		assertEquals(0,list.size(),"failed to Removing the last element in the list when when the size equals to 1 and the list is empty.");
		assertEquals(null,list.removeLast(),"failed to Removing the last element in the list when when the size equals to 1 and the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#removeLast()}.
	 * Remove the last element in the list when the size equals to 2 and 
	 * the list is not empty.
	 */
	@Test
	void testRemoveLast3() {
		
		assertTrue(list.append(a));
		assertTrue(list.add(b));
		list.removeLast();
		assertEquals(1,list.size(),"failed to Removing the last element in the list when when the size equals to 1 and the list is empty.");
		assertEquals(b,list.get(),"failed to Removing the last element in the list when when the size equals to 1 and the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#removeLast()}.
	 * Remove the last element in the list when the size is greater than 2 and 
	 * the list is not empty.
	 */
	@Test
	void testRemoveLast4() {
		
		assertTrue(list.append(a));
		assertTrue(list.add(b));
		assertTrue(list.append(c));
		assertEquals(c,list.removeLast(),"failed to Removing the last element in the list when when the size is greater than 2 and the list is empty.");
		assertEquals(2,list.size(),"failed to Removing the last element in the list when when the size is greater than 2 and the list is empty.");
		assertEquals(b,list.get(),"failed to Removing the last element in the list when when the size is greater than 2 and the list is empty.");
		assertEquals(a,list.getLast(),"failed to Removing the last element in the list when when the size is greater than 2 and the list is empty.");
	}

	/**
	 * Test method for {@link utility.SLL#remove(int)}.
	 * Remove the element at the position which is less than 0 
	 * when the list is not empty.
	 */
	@Test
	void testRemoveInt1() {

		try {
			
			list.remove(-1);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#remove(int)}.
	 * Remove the element at the position which is greater than 
	 * or equals to size when the list is empty.
	 */
	@Test
	void testRemoveInt2() {

		try {
			
//			list.remove(1);
			list.remove(2);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#remove(int)}.
	 * Remove the element at the first index when the list is not empty.
	 */
	@Test
	void testRemoveInt3() {

		assertTrue(list.add(a));
		assertTrue(list.append(b));
		assertTrue(list.add(c));
		list.remove(0);
		assertEquals(a,list.get(),"failed to Remove the element at the first index when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#remove(int)}.
	 * Remove the element at the index 1 when the list is not empty.
	 */
	@Test
	void testRemoveInt4() {

		assertTrue(list.add(a));
		assertTrue(list.append(b));
		assertTrue(list.add(c));
		list.remove(1);
		assertEquals(c,list.get(),"failed to Remove the element at the index 1 when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#remove(int)}.
	 * Remove the element at the (index+1 equals to the size) 
	 * when the list is not empty.
	 */
	@Test
	void testRemoveInt5() {

		assertTrue(list.add(a));
		assertTrue(list.append(b));
//		assertTrue(list.add(c));
//		list.remove(1);
		assertEquals(b,list.remove(1),"failed to Remove the element at the (index+1 equals to the size) when the list is not empty.");
		assertEquals(1,list.size(),"failed to Remove the element at the (index+1 equals to the size) when the list is not empty.");
		assertEquals(a,list.getLast(),"failed to Remove the element at the (index+1 equals to the size) when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#remove(int)}.
	 * Remove the element at the index that is greater than 1 and 
	 * less than index+1 when the list is not empty.
	 */
	@Test
	void testRemoveInt6() {

		assertTrue(list.add(a));
		assertTrue(list.append(b));//removed
		assertTrue(list.add(c));
		assertTrue(list.append(b));
		list.remove(2);
		assertEquals(3,list.size(),"failed to Remove the element at the index that is greater than 1 andless than index+1 when the list is not empty.");
		assertEquals(c,list.get(),"failed to Remove the element at the index that is greater than 1 andless than index+1 when the list is not empty.");
		assertEquals(b,list.getLast(),"failed to Remove the element at the index that is greater than 1 andless than index+1 when the list is not empty.");
	}

	/**
	 * Test method for {@link utility.SLL#get()}.
	 * Gets a reference to the first element in the list 
	 * when the list is empty.
	 */
	@Test
	void testGet1() {

		assertEquals(null,list.get(),"failed to Gets a reference to the first element in the list when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#get()}.
	 * Gets a reference to the first element in the list 
	 * when the list is not empty.
	 */
	@Test
	void testGet2() {

		list.add(a);
		list.append(b);
		list.add(c);
		assertEquals(c,list.get(),"failed to Gets a reference to the first element in the list when the list is not empty.");
	}

	/**
	 * Test method for {@link utility.SLL#getLast()}.
	 * Gets a reference to the last element in the list 
	 * when the list is empty.
	 */
	@Test
	void testGetLast1() {

		assertEquals(null,list.getLast(),"failed to Gets a reference to the last element in the list when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#getLast()}.
	 * Gets a reference to the last element in the list 
	 * when the list is not empty.
	 */
	@Test
	void testGetLast2() {

		list.add(a);
		list.add(b);
		assertEquals(a,list.getLast(),"failed to Gets a reference to the last element in the list when the list is not empty.");
	}

	/**
	 * Test method for {@link utility.SLL#get(int)}.
	 * Gets a reference to the element at the specified 
	 * position that is less than 0 when the list is empty, 
	 * which throws exception.
	 */
	@Test
	void testGetInt1() {

		try {
			
			list.get(-1);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#get(int)}.
	 * Gets a reference to the element at the specified 
	 * position that is less than 0 when the list is not empty, 
	 * which throws exception.
	 */
	@Test
	void testGetInt2() {

		try {
			
			list.add(a);
			list.get(-1);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#get(int)}.
	 * Gets a reference to the element at the specified 
	 * position that is greater than the size of the list
	 * when the list is empty, which throws exception.
	 */
	@Test
	void testGetInt3() {

		try {
			
			list.get(1);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#get(int)}.
	 * Gets a reference to the element at the specified 
	 * position that is greater than the size of the list when the list is 
	 * not empty, which throws exception.
	 */
	@Test
	void testGetInt4() {

		try {
			
			list.add(a);
			list.add(b);
			list.get(2);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#get(int)}.
	 * Gets a reference to the element at the specified 
	 * position when the list is empty.
	 */
	@Test
	void testGetInt5() {

		assertEquals(null, list.get(0),"failed to Gets a reference to the element at the specified position when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#get(int)}.
	 * Gets a reference to the element at the first index 
	 * when the list is not empty.
	 */
	@Test
	void testGetInt6() {
		
		list.add(a);
		list.append(b);
		list.append(c);
		assertEquals(a, list.get(0),"failed to Gets a reference to the element at the first index when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#get(int)}.
	 * Gets a reference to the element at the index that is greater than 0
	 * when the list is not empty.
	 */
	@Test
	void testGetInt7() {
		
		list.add(a);
		list.append(b);
		list.append(c);
		assertEquals(c, list.get(2),"failed to Gets a reference to the element at the first index when the list is not empty.");
	}

	/**
	 * Test method for {@link utility.SLL#set(java.lang.Object, int)}.
	 * Replaces the element at the position that is less than 0 in this list 
	 * with the specified element when the list is empty.
	 */
	@Test
	void testSet1() {
		
		try {
			
			list.set(a,-1);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#set(java.lang.Object, int)}.
	 * Replaces the element at the position that is less than 0 in this list 
	 * with the specified element when the list is not empty.
	 */
	@Test
	void testSet2() {
		
		try {
			
			list.add(a);
			list.set(a,-1);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#set(java.lang.Object, int)}.
	 * Replaces the element at the position that is greater than the size 
	 * in this list with the specified element when the list is empty.
	 */
	@Test
	void testSet3() {
		
		try {
			
			list.set(a,1);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#set(java.lang.Object, int)}.
	 * Replaces the element at the position that is greater than the size 
	 * in this list with the specified element when the list is not empty.
	 */
	@Test
	void testSet4() {
		
		try {
			
			list.add(a);
			list.set(a,1);
			assertTrue(false);
		}catch(IndexOutOfBoundsException e){
			
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.SLL#set(java.lang.Object, int)}.
	 * Replaces the element at the first index in this list 
	 * with the specified element when the list is not empty.
	 */
	@Test
	void testSet5() {
		
		list.add(a);
		assertEquals(a, list.set(b, 0),"failed to Replaces the element at the first index in this list with the specified element when the list is not empty.");
		assertEquals(b, list.get(),"failed to Replaces the element at the first index in this list with the specified element when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#set(java.lang.Object, int)}.
	 * Replaces the element at the index that is greater than 0 in this list 
	 * with the specified element when the list is not empty.
	 */
	@Test
	void testSet6() {
		
		list.add(a);
		list.add(b);
		list.append(c);
		assertEquals(b, list.set(a, 0),"failed to Replaces the element at the index that is greater than 0 in this list with the specified element when the list is not empty.");
		assertEquals(a, list.get(),"failed to Replaces the element at the the index that is greater than 0 in this list with the specified element when the list is not empty.");
	}

	/**
	 * Test method for {@link utility.SLL#contains(java.lang.Object)}.
	 * Checks the list and determines if the object exists in the list
	 * when the list is empty.
	 */
	@Test
	void testContains1() {
		
		assertEquals(false, list.contains(a),"failed to Checks the list and determines if the object exists in the list when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#contains(java.lang.Object)}.
	 * Checks the list and determines if the object exists in the list
	 * when the list is not empty.
	 */
	@Test
	void testContains2() {
		
		list.add(a);
		list.add(b);
		assertEquals(true, list.contains(b),"failed to Checks the list and determines if the object exists in the list when the list is empty.");
	}

	/**
	 * Test method for {@link utility.SLL#indexOf(java.lang.Object)}.
	 * Returns the index in this list of the first occurrence of the
	 * specified element when the list is not empty.
	 */
	@Test
	void testIndexOf1() {
		
		list.add(a);
		list.add(b);
		assertEquals(1, list.indexOf(a),"failed to Returns the index in this list of the first occurrence of the specified element when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#indexOf(java.lang.Object)}.
	 * Returns the index in this list of the first occurrence of the
	 * specified element when the list is empty.
	 */
	@Test
	void testIndexOf2() {

		assertEquals(-1, list.indexOf(a),"failed to Returns the index in this list of the first occurrence of the specified element when the list is empty.");
	}

	/**
	 * Test method for {@link utility.SLL#size()}.
	 * Gets the current number of elements in the list 
	 * when the list is empty.
	 */
	@Test
	void testSize1() {

		assertEquals(0, list.size(),"failed to Gets the current number of elements in the list when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#size()}.
	 * Gets the current number of elements in the list 
	 * when the list is not empty.
	 */
	@Test
	void testSize2() {

		list.add(a);
		list.add(b);
		list.add(c);
		assertEquals(3, list.size(),"failed to Gets the current number of elements in the list when the list is not empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#isEmpty()}.
	 * Returns the current status of the list when the list is empty.
	 */
	@Test
	void testIsEmpty1() {

		assertEquals(true, list.isEmpty(),"failed to Returns the current status of the list when the list is empty.");
	}
	
	/**
	 * Test method for {@link utility.SLL#isEmpty()}.
	 * Returns the current status of the list when the list is not empty.
	 */
	@Test
	void testIsEmpty2() {

		list.add(a);
		list.add(b);
		list.add(c);
		assertEquals(false, list.isEmpty(),"failed to Returns the current status of the list when the list is not empty.");
	}

}
