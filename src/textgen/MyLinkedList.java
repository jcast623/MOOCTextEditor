package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.size = 0;
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		try{
			this.add(this.size, element);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		// TODO: Implement this method.
		if ((index > (this.size - 1)) || (index < 0)) {
			throw new IndexOutOfBoundsException();
		} else {
			LLNode<E> cur = this.head.next;
			int count = 0;

			while (count < index) {
				cur = cur.next;
				count++;
			}
			return cur.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		LLNode<E> elemToAdd = new LLNode<E>(element);
		if ((index > this.size) || (index < 0)) {
			throw new IndexOutOfBoundsException();
		}else{
			
			int count = 0;
			LLNode<E> cur = this.head;
			
			while(count <= index){
				cur = cur.next;
				count++;
			}
			
			LLNode<E> prevNode = cur.prev;
			prevNode.next = elemToAdd;
			cur.prev = elemToAdd;
			elemToAdd.next = cur;
			elemToAdd.prev = prevNode;
			this.size++;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if ((index >= this.size) || (index < 0)) {
			throw new IndexOutOfBoundsException();
		}else{
			int count = 0;
			LLNode<E> cur = this.head;
			
			while(count <= index){
				cur = cur.next;
				count++;
			}
			
			LLNode<E> prevNode = cur.prev;
			LLNode<E> nextNode = cur.next;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			this.size--;
			return cur.data;
			
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if ((index >= this.size) || (index < 0)) {
			throw new IndexOutOfBoundsException();
		}else{
			E toRemove = this.remove(index);
			this.add(index, element);
			return toRemove;
		}
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> prev){
		this(e,prev,null);
	}
	
	public LLNode(E e, LLNode<E> prev, LLNode<E> next){
		this.data = e;
		this.prev = prev;
		this.next = next;
	}

}
