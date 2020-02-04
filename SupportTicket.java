//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: SupportTicket
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
 * This class should contain a constructor that takes a String message in as input, and
 * stores that string within an instance field. This String message should then be
 * returned whenever toString() is called on that SupportTicket object.
 * 
 * @author shihan
 *
 */
public class SupportTicket implements Comparable<SupportTicket> {

  private String message; // Instance field to store the message of the ticket

  /**
   * Constructor that takes a String message in as input, and stores that string within an
   * instance field.
   * 
   * @param message The message of the ticket
   */
  public SupportTicket(String message) {
    // Check if the message is null
    if (message == null) {
      throw new NullPointerException("message is null");
    }
    // Check if the message is an empty string
    if (message.equals("")) {
      throw new IllegalStateException("ticket's message is empty");
    }
    // Assign the instance message with the argument
    this.message = message;
  }

  /**
   * Compare this ticket with a specific message
   * 
   * @return a negative integer, zero, or a positive integer as this ticket's message is
   *         less than, equal to, or greater than the specified ticket.
   * 
   */
  @Override
  public int compareTo(SupportTicket ticket) {
    // Compare their length first. the ticket with longer massage has higher priority
    if (this.message.trim().length() < ticket.toString().trim().length()) {
      return -1;
    }
    if (this.message.trim().length() > ticket.toString().trim().length()) {
      return 1;
    }
    // When they have same length, compare them with characters lexicographically
    if (this.message.trim().compareTo(ticket.toString().trim()) < 0) {
      return -1;
    }
    if (this.message.trim().compareTo(ticket.toString().trim()) > 0) {
      return 1;
    }
    // Otherwise, the two ticket are totally same
    return 0;
  }

  /**
   * Get the string of this message
   * 
   * @return the string of this message
   */
  public String toString() {
    return this.message;
  }

}
