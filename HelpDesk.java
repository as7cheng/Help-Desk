//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: HelpDesk
// Files: HelpDeskInterface.java, SupportTicket.java, HelpDesk.java,
// HelpDeskTestSuite.java
// Course: Comp Sci 300, LEC-005, Spring 2019
//
// Author: Shihan Cheng
// Email: scheng93@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NA
// Partner Email: NA
// Partner Lecturer's Name: NA
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NA
// Online Sources: NA
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class be implemented as a max-heap: where the SupportTicket at the root of this
 * heap is always the support ticket containing the longest message, ie the ticket that is
 * latest in the natural ordering of messages. The root of this heap must always be at
 * index zero
 * 
 * @author shihan
 *
 */
public class HelpDesk implements HelpDeskInterface {
  protected SupportTicket[] array; // zero-indexed max-heap
  protected int size; // Size of current tickets in heap

  /**
   * Constructor that takes the internal array capacity as an int parameter. This capacity
   * is fixed, and does not need to grow when filled.
   * 
   * @param capacity Maximum number of tickets can be stored for this heap
   */
  public HelpDesk(int capacity) {
    this.array = new SupportTicket[capacity]; // Define heap's size
    this.size = 0; // Initially heap has zero tickets
  }

  /**
   * Creates and adds a new SupportTicket to this HelpDesk.
   * 
   * @param message names the client and describes their need for support.
   * @throws NullPointerException when the String message argument is null.
   * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
   */
  @Override
  public void createNewTicket(String message) {
    // Create a new ticket first, check if message is valid by calling SupportTicket()
    SupportTicket ticket = new SupportTicket(message);
    // When the heap is full, throw exception
    if (this.size >= this.array.length) {
      throw new IndexOutOfBoundsException("array is full.");
    }
    // When the heap is empty, only set the root with the ticket
    if (this.size == 0) {
      this.array[this.size] = ticket;
      size++; // Size increases
    } else { // Otherwise, set the ticket as the last one
      this.array[this.size] = ticket;
      propagateUp(this.size); // Re-order the heap
      this.size++; // Size increases
    }
  }

  /**
   * Returns the message within this HelpDesk that has the highest priority. This method
   * does not change the state of this HelpDesk.
   * 
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String checkNextTicket() {
    if (size == 0) { // When the heap is empty, throw exception
      throw new IllegalStateException("HelperDesk is empty.");
    }
    if (this.array[0] == null) {
      throw new NullPointerException("ticket at 0 is null");
    }
    // Otherwise, return the root's message
    return this.array[0].toString();
  }

  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   * 
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  @Override
  public String closeNextTicket() {
    if (this.size == 0) { // When the heap is empty
      throw new IllegalStateException("HelperDesk is empty.");
    }
    if (this.array[0] == null) {
      throw new NullPointerException("ticket at 0 is null");
    }
    SupportTicket temp; // Create a temporary ticket
    if (this.size == 1) { // If heap only contains one element
      temp = array[0]; // Store it
      this.array[0] = null; // Remove
      this.size--; // Size decreases
      return temp.toString(); // Return its message
    }
    // Otherwise, heap contains at lease two tickets
    temp = array[0]; // Store the root
    swap(0, this.size - 1); // Swap it with the last one
    this.array[this.size - 1] = null; // Remove the last one
    this.size--; // Size decreases
    propagateDown(0); // Re-order the heap with the root
    return temp.toString(); // Return its message
  }

  /**
   * Given an index into the heap array, this method returns that index's parent index
   * 
   * @param index Index that we want to get its parent's index
   * @return index's parent index
   */
  protected static int parentOf(int index) {
    if (index == 0) { // If parameter is the root, return 0
      return 0;
    }
    if (index < 0) { // If parameter is less than 0
      throw new IndexOutOfBoundsException("Index is invalid");
    }
    // Otherwise, parent's index is (index - 1)/2
    return (index - 1) / 2;
  }

  /**
   * Given an index into the heap array, this method returns that index's left child index
   * 
   * @param index Index that we want to get its leftChild's index
   * @return index's leftChild's index
   */
  protected static int leftChildOf(int index) {
    if (index < 0) { // If parameter is less than 0
      throw new IndexOutOfBoundsException("Index is invalid");
    }
    // Otherwise, return the left child index is index * 2 + 1
    return index * 2 + 1;
  }

  /**
   * Given an index into the heap array, this method returns that index's right child
   * index
   * 
   * @param index Index that we want to get its rightChild's index
   * @return index's rightChild's index
   */
  protected static int rightChildOf(int index) {
    if (index < 0) { // If parameter is less than 0
      throw new IndexOutOfBoundsException("Index is invalid");
    }
    // Otherwise, return the left child index is index * 2 + 2
    return index * 2 + 2;
  }

  /**
   * Given two indexes into the heap array, this method swaps the SupportTickets at those
   * indexes.
   * 
   * @param indexA first index to be swapped
   * @param indexB second index to be swapped
   */
  protected void swap(int indexA, int indexB) {
    // Check if the first index is valid
    if (indexA > this.size || indexA < 0) {
      throw new IndexOutOfBoundsException("indexA is invalid");
    }
    // Check if the second index is valid
    if (indexB > this.size || indexB < 0) {
      throw new IndexOutOfBoundsException("indexB is invalid");
    }
    // Check if the ticket at indexA is null
    if (this.array[indexA] == null) {
      throw new NullPointerException("ticket at indexA is null");
    }
    // Check if the ticket at indexB is null
    if (this.array[indexB] == null) {
      throw new NullPointerException("ticket at indexB is null");
    }
    // Then create a temporary ticket to store message, swap the two tickets
    SupportTicket temp = this.array[indexA];
    this.array[indexA] = this.array[indexB];
    this.array[indexB] = temp;
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets
   * necessary to enforce the heap's order property between this index and the heap's
   * root.
   * 
   * @param index Index of the element that need to go up
   */
  protected void propagateUp(int index) {
    // Check if the index is valid
    if (index > this.size || index < 0) {
      throw new IndexOutOfBoundsException("index is invalid");
    }
    if (this.array[0] == null) {
      throw new NullPointerException("ticket at 0 is null");
    }
    // When index is the root, nothing need to do
    if (index == 0) {
      return;
    }
    // Otherwise, start the recursion
    if (this.array[index].compareTo(this.array[parentOf(index)]) > 0) {
      swap(index, parentOf(index));
      propagateUp(parentOf(index));
    }
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets
   * necessary to enforce the heap's order property between this index and it's children.
   * 
   * @param index Index of the element that need to go down
   */
  protected void propagateDown(int index) {
    if (this.size == 0) { // When the heap is empty
      throw new IllegalStateException("HelperDesk is empty.");
    }
    if (index > this.size || index < 0) { // When the index is invalid
      throw new IndexOutOfBoundsException("index is invalid");
    }
    if (this.array[0] == null) {
      throw new NullPointerException("ticket at 0 is null");
    }
    // Recursion. When left child is non-null and greater than its parent and it is
    // invalid index
    if (leftChildOf(index) <= size && this.array[leftChildOf(index)] != null
        && this.array[index].compareTo(this.array[leftChildOf(index)]) < 0) {
      // Now, compare left and right. Swap root with the biggest child
      if (this.array[rightChildOf(index)] == null
          || rightChildOf(index) > size || this.array[leftChildOf(index)]
              .compareTo(this.array[rightChildOf(index)]) > 0) {
        swap(index, leftChildOf(index));
        propagateDown(leftChildOf(index));
      } else {
        swap(index, rightChildOf(index));
        propagateDown(rightChildOf(index));
      }
    }
    // Recursion. When left child is non-null and greater than its parent it is invalid
    // index
    else if (rightChildOf(index) <= size && this.array[rightChildOf(index)] != null
        && this.array[index].compareTo(this.array[rightChildOf(index)]) < 0) {
      swap(index, rightChildOf(index));
      propagateDown(rightChildOf(index));
    } else { // Base case. When parent is greater the its children
      return;
    }
  }

}
