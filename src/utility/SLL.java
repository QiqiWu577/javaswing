package utility;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SLL implements List,Serializable{

	private SLLNode head;
	private int size;
	

	/**
	 *Method to append an element to the end of a List.  If the list is
	 *empty the element will be added to the first position.
	 *Performence: O(1)?
	 *
	 *@param element - element to be added to the list
	 */
	@Override
	public boolean append(Object element) {

		SLLNode newNode = new SLLNode(element);
		
		//check if list is empty
		if(head == null) {
			
			head = newNode;
		}else {
			
			SLLNode lastNode = head;
			
			while(lastNode.next != null) {
				
				lastNode = lastNode.next;
			}
			
			lastNode.next = newNode;
		}
		
		size++;
		return true;
	}

	/**
	 *Method to add an element to the FIRST position in the
	 *list.  If the list is empty, the element will be added to the
	 *first position.
	 *Performence: O(1)
	 *
	 *@param element - element to be added to the list
	 */
	@Override
	public boolean add(Object element) {

		SLLNode newNode = new SLLNode(element);
		
		if(head == null) {
			
			head = newNode;
		}else {
			//grab the element and the next from the original head
			newNode.next = head;
			//reset the head as newNode since the position of head will not change(head is the reference data type)
			head = newNode;
		}
		
		size++;
		return true;
	}

	 /**
	  *Method to add an element to a specific position in the
	  *list.  If the position is an index outside of the bounds
	  *of the list an exception is raised.
	  *this method has 6 conditions.
	  *Performence: O(n)
	  *@param element - element to be added to the list
	  *@param position - index that the element should be placed at
	  *@throws IndexOutOfBoundsException when the index is (position<0 || position > size())
	  *
	  */
	@Override
	public boolean add(Object element, int position) throws IndexOutOfBoundsException {

		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException("failed");
		}
		
		SLLNode newNode = new SLLNode(element);
		
		if(head == null) {
			
			head = newNode;
		}else {
			
			if(position==0) {
				
				newNode.next = head;
				head = newNode;
			}else if(position==1){
				
				SLLNode afterNode = head.next;
				head.next = newNode;
				newNode.next = afterNode;
			}else if(position>1 && position<size){
				
				SLLNode beforeNode = head;
				
				for(int i=0;i<position-1;i++) {
					
					beforeNode = beforeNode.next;
				}
				
				SLLNode afterNode = beforeNode.next;
				
				beforeNode.next = newNode;
				newNode.next = afterNode;
			}else if(position==size) {
				
				SLLNode lastNode = head;
				
				while(lastNode.next != null) {
					
					lastNode = lastNode.next;
				}
				
				lastNode.next = newNode;
			}
			
		}
		
		size++;
		return true;
	}

	/**
	 *Clears the list, all elements are lost
	 *Performence: O(1)
	 */
	@Override
	public void clear() {
	
		size = 0;
		head = null;
	}

	/**
	  *Removes the <b>FIRST</b> element in the list and changes the index
	  *for all the remaining elements by (-1).<br>
	  *Performence: O(1)
	  *
	  *@return Object element at the first position of the list.  If the list
	  *is empty, returns a <code>null</code> reference.
	  */
	@Override
	public Object remove() {

		if(head == null) {
			
			return null;
		}else {
			
			Object deletedNode = null;
			SLLNode remove = head.next;
			
			deletedNode = head.element;
			head = remove;
			
			size--;
			return deletedNode;
		}
	}

	/**
	  *Removes the <b>LAST</b> element in the list.
	  *Performence: O(n)
	  *
	  *@return Object element at the last position of the list.  If list
	  *is empty, returns a <code>null</code> reference.
	  */
	@Override
	public Object removeLast() {

		if(head==null) {
			
			return null;
		}else {
			
			Object deletedNode = null;
			
			if(size==1) {
				
				deletedNode = head.element;
				head = null;
			}else if(size==2) {
				
				deletedNode = head.next.element;
				head.next = null;
			}else if(size>2) {
				
				SLLNode beforeNode = head;
				
				for(int i=1;i<size-1;i++) {
					
					beforeNode = beforeNode.next;
				}
				deletedNode = beforeNode.next.element;
				beforeNode.next = null;
			}
			
			size--;
			return deletedNode;
		}
	}

	/**
	  *Removes the element at the provided index, if the index
	  *is outside the bounds of the the list an exception is thrown
	  *Performence: O(n)
	  *
	  *@param position the position in the list of the element to be removed
	  *@return Object element being removed from the list
	  *@throws IndexOutOfBoundsException when the index is (position<0 || position >= size())
	  */
	@Override
	public Object remove(int position) throws IndexOutOfBoundsException {
		
		Object deletedNode = null;
		
		if(position < 0 || position >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(head == null) {
			
			return null;
		}else {
			
			if(position==0) {
				
				SLLNode afterNode = head.next;
				
				deletedNode = head.element;
				head = afterNode;
			}else if(position==1){
				
				SLLNode afterNode = head.next.next;
				
				deletedNode = head.next.element;
				head.next = afterNode;
			}else if(position+1==size){
				
				removeLast();
				size++;//since the method removeLast() has minus 1 so need to add 1 to make up.
			}else {
				
				SLLNode beforeNode = head;
				
				for(int i=0;i<position-1;i++) {
					
					beforeNode = beforeNode.next;
				}
				
				SLLNode afterNode = beforeNode.next.next;
				
				deletedNode = beforeNode.next.element;
				beforeNode.next = afterNode;
			}
			
			size--;
			return deletedNode;
		}
	}

	/**
	  *Gets a reference to the first element in the list without
	  *disturbing the list structure.
	  *
	  *@return Object reference to the first element in the list, if
	  *the list is empty, returns a <code>null</code> reference.
	  */
	@Override
	public Object get() {

		if(head == null) {
			return null;    
		}
		
		return head.element;
	}

	 /**
	  *Gets a reference to the last element in the list, without
	  *disturbing the list structure.
	  *
	  *@return Object reference to the last element in the list, if
	  *the list is empty, returns a <code>null</code> reference.
	  */
	@Override
	public Object getLast() {
		
		if(head == null){
			return null;
		}
		
		SLLNode lastNode = head;
		while(lastNode.next != null) {
			
			lastNode = lastNode.next;
		}
		
		return lastNode.element;
	}

	/**
	  *Gets a reference to the element at the specified position. If the List
	  *is empty returns a <code>null</code> reference.
	  *
	  *@param position the location within the list of the desired element
	  *@return Object reference to the specified element
	  *@throws IndexOutOfBoundsException when the index is (position< 0 || position >= size())
	  */
	@Override
	public Object get(int position) throws IndexOutOfBoundsException {
		
		if(head == null) {
			
			if(position < 0 || position > size) {
				throw new IndexOutOfBoundsException();
			}
			
			return null;
		}else {
			
			if(position < 0 || position >= size) {
				throw new IndexOutOfBoundsException();
			}
			
			if(position==0) {
				
				return head.element;
			}else {
				
				SLLNode lastNode = head;
				
				for(int i=0;i<position;i++) {
					
					lastNode =lastNode.next;
				}
				
				return lastNode.element;
			}
		}
	}

	/**
	  * Replaces the element at the specified position in this list
	  * with the specified element.
	  *  
	  * @param element - element to be stored at the specified position
	  * @param position - index of the element to replace
	  * @return Object reference to the element previously stored at the specified position
	  * @throws IndexOutOfBoundsException when the index is (position< 0 || position >= size())
	  */
	@Override
	public Object set(Object element, int position) throws IndexOutOfBoundsException {
		
		Object prev = null;
		
		if(head==null) {
			
			if(position < 0 || position > size) {
				throw new IndexOutOfBoundsException();
			}
			
			head.element = element;
			
			return null;
		}else {
			
			if(position < 0 || position >= size) {
				throw new IndexOutOfBoundsException();
			}
			
			if(position==0) {
				
				prev = head.element;
				head.element = element;
				
				return prev;
			}else {
				
				SLLNode lastNode = head;
				
				for(int i=0;i<position;i++) {
					
					lastNode =lastNode.next;
				}
				
				prev = lastNode.element;
				lastNode.element = element;
				
				return prev;
			}
		}
	}

	/**
	  *Checks the list and determines if the object exists in the list, if 
	  *the object is present in the list, the method returns true.
	  *
	  *@param element - element being located in list
	  *@return <code>boolean</code> true if object is in list,
	  *otherwise false.
	  */
	@Override
	public boolean contains(Object element) {
		
		boolean check = false;
		
		if(head==null) {
			
			check = false;
		}
		
		SLLNode node = head;
			
		for(int i=0;i<size;i++) {
			
			if(node.element.equals(element)) {
				
				check = true;
			}
			node = node.next;
		}
		return check;
	}

	/**
	  *Returns the index in this list of the first occurrence of the
	  *specified element, or -1 if the List does not contain this element.
	  *
	  *@param element - element being located in list
	  *@return <code>int</code>, the index in this list of the first
	  *occurrence of the specified element, or -1 if the list does not
	  *contain this element.
	  */
	@Override
	public int indexOf(Object element) {

		int index = 0;
		
		if(head==null) {
			
			index = -1;
		}
		
		SLLNode node = head;
		
		for(int i=0;i<size;i++) {
			
			if(node.element.equals(element)) {
				
				index = i;
				return index;
			}
			node = node.next;
		}
		return index;
	}

	/**
	  *Gets the current number of elements in the list, if list is empty
	  *returns a 0 (zero).
	  *
	  *@return <code>int</code>, an integer greater or equal to zero
	  */
	@Override
	public int size() {
		
		int sizeR = 0;
		
		if(head==null) {
			
			return sizeR;
		}else {
			
			sizeR = size;
			
			return sizeR;
		}
	}
	
	/**
	  *Returns the current status of the list.
	  *
	  *@return <code>boolean</code> false if the list contains any elements.
	  */
	@Override
	public boolean isEmpty() {
		
		return (head == null);
	}
}
