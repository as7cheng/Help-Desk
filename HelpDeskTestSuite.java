//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: HelpDeskTestSuite
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
 * This class contains nine tests. The first one is to test if SupporTicket() works well.
 * Rest of them are to test the specific methods' behavior in HelpDesk().
 * 
 * @author shihan
 *
 */
public class HelpDeskTestSuite extends HelpDesk {

  // This constructor will never be called
  private HelpDeskTestSuite(int capacity) {
    super(capacity);
  }

  /**
   * This method tests if SupporTicket() works well
   * 
   * @return true if the behavior of a SupportTicket object works well. False otherwise
   */
  public static boolean testSuppotTicket() {
    // Create 4 SuppotTicket objects
    SupportTicket ticketA = new SupportTicket("aB");
    SupportTicket ticketB = new SupportTicket("ab");
    SupportTicket ticketC = new SupportTicket("Abc");
    SupportTicket ticketD = new SupportTicket("aB");

    // Compare ticketA with B, here B has higher priority
    if (ticketA.compareTo(ticketB) != -1) {
      System.out.println(ticketA.compareTo(ticketB));
      System.out.println(
          ticketA.toString() + " should have higher priority than " + ticketB.toString());
      return false;
    }
    // Compare ticketA with C, here C has higher priority
    if (ticketA.compareTo(ticketC) != -1) {
      System.out.println(
          ticketA.toString() + " should have lower priority than " + ticketC.toString());
      return false;
    }
    // Compare ticketA with D, here they have same priority
    if (ticketA.compareTo(ticketD) != 0) {
      System.out.println(
          ticketA.toString() + " should have same priority as " + ticketD.toString());
      return false;
    }
    try { // Try to create an empty ticket, then IllegalStateException should be thrown
      SupportTicket ticketE = new SupportTicket("");
      System.out.println("ticketE is empty, an IllegalStateException should be thorown");
      return false;
    } catch (IllegalStateException e) {
    }
    try { // Try to create an null ticket, then NullPointerException should be thrown
      SupportTicket ticketF = new SupportTicket(null);
      System.out.println("ticketF is null, an NullPointerException should be thorown");
      return false;
    } catch (NullPointerException e) {
    }
    return true; // Test passed
  }


  /**
   * This method tests if createNewTicket() works well
   * 
   * @return true if when adding a new ticket the size increases and heap re-orders from
   *         high to low priority correctly. False otherwise
   */
  public static boolean testHelpDeskCreateNewTicket() {
    HelpDesk deskA = new HelpDesk(10); // Create a new HelpDesk
    // Create seven SupportTicket objects with priority form high to low
    SupportTicket ticketA = new SupportTicket("aaa");
    SupportTicket ticketB = new SupportTicket("c");
    SupportTicket ticketC = new SupportTicket("f");
    SupportTicket ticketD = new SupportTicket("d");
    SupportTicket ticketE = new SupportTicket("e");
    SupportTicket ticketF = new SupportTicket("a");
    SupportTicket ticketG = new SupportTicket("b");
    // For the max heap, if we add the tickets by the order of A,B..G
    // Then the array should be: "aaa", "e", "f", "c", "d", "a", "b"
    deskA.array[0] = ticketA;
    deskA.array[1] = ticketE;
    deskA.array[2] = ticketC;
    deskA.array[3] = ticketB;
    deskA.array[4] = ticketD;
    deskA.array[5] = ticketF;
    deskA.array[6] = ticketG;
    deskA.size = 7; // Size should be 7.

    HelpDesk deskB = new HelpDesk(10); // Create a second HelpDesk
    // Now check use the createTicket() to add above tickets in
    deskB.createNewTicket(ticketA.toString());
    deskB.createNewTicket(ticketB.toString());
    deskB.createNewTicket(ticketC.toString());
    deskB.createNewTicket(ticketD.toString());
    deskB.createNewTicket(ticketE.toString());
    deskB.createNewTicket(ticketF.toString());
    deskB.createNewTicket(ticketG.toString());
    // First check if the deskB's size is 7
    if (deskB.size != 7) {
      System.out.println("deskB's size should be 7, but it acctually is " + deskB.size);
      return false;
    }
    // Then iterate two desks compare if the array accessed by createTicket() has the
    // correct order as we just accessed manually
    for (int i = 0; i < deskB.size; i++) {
      if (deskB.array[i].compareTo(deskA.array[i]) != 0) {
        System.out.println("At index " + i + " should be " + deskA.array[i].toString()
            + ", but it is " + deskB.array[i].toString());
      }
    }
    // Otherwise, createTicket() works well
    return true;
  }

  /**
   * This method tests if checkNextTicket() works well
   * 
   * @return true if checkNextTicket() return the root of the array. False otherwise
   */
  public static boolean testHelpDeskCheckNextTicket() {
    HelpDesk deskA = new HelpDesk(10); // Create a new HelpDesk
    HelpDesk deskB = new HelpDesk(10); // Create a new HelpDesk

    // Create new tickets
    SupportTicket ticketA = new SupportTicket("a");
    SupportTicket ticketB = new SupportTicket("b");
    // Manually add them in deskA with high to low priority
    deskA.array[0] = ticketB;
    deskA.array[1] = ticketA;
    deskA.size = 2;
    // Then call createNewTicket() to add them into deskB
    deskB.createNewTicket(ticketA.toString());
    deskB.createNewTicket(ticketB.toString());
    // Since checkNextTicket is return the root who has the highest priority
    // Then this method should return same ticket as deskA[0]
    if (!deskB.checkNextTicket().equals(deskA.array[0].toString())) {
      System.out.println("checkNextTicket() should return " + deskA.array[0].toString()
          + ", but it returns " + deskB.checkNextTicket());
      return false;
    }
    // If checkNextTicket() for deskA does not return deskA[0], method failed
    if (!deskA.checkNextTicket().equals(deskA.array[0].toString())) {
      System.out.println("checkNextTicket() should return " + deskA.array[0].toString()
          + ", but it returns " + deskB.checkNextTicket());
      return false;
    }
    return true; // Otherwise, checkNextTicket() works well
  }

  /**
   * This method tests if testHelpDeskCloseNextTicket() works well
   * 
   * @return true if the heap remove the root and return the root's message successfully.
   *         False otherwise
   */
  public static boolean testHelpDeskCloseNextTicket() {
    HelpDesk desk = new HelpDesk(10); // Create a new HelpDesk
    // Create seven SupportTicket objects
    SupportTicket ticketA = new SupportTicket("2");
    SupportTicket ticketB = new SupportTicket("2");
    SupportTicket ticketC = new SupportTicket("3");
    SupportTicket ticketD = new SupportTicket("4");
    SupportTicket ticketE = new SupportTicket("5");

    // Then the array should be: "2", "2", "3', "4", "5"
    desk.array[0] = ticketA;
    desk.array[1] = ticketB;
    desk.array[2] = ticketC;
    desk.array[3] = ticketD;
    desk.array[4] = ticketE;
    desk.size = 5; // Size is 5.
    // Call closeNextTicket()
    desk.closeNextTicket();
    // Now, desk should be "5", "2", "3", "4", size is 4
    if (desk.size != 4) { // Size should be 4
      System.out.println("size should be 4, but it actually is " + desk.size);
      return false;
    }
    if (!desk.array[0].toString().equals("5")) { // [0] should be 5
      System.out
          .println("Inde 0 should be 5, but it actually is " + desk.array[0].toString());
      return false;
    }
    if (!desk.array[1].toString().equals("2")) { // [1] should be 2
      System.out
          .println("Inde 1 should be 2, but it actually is " + desk.array[1].toString());
      return false;
    }
    if (!desk.array[2].toString().equals("3")) { // [2] should be 3
      System.out
          .println("Inde 2 should be 3, but it actually is " + desk.array[2].toString());
      return false;
    }
    if (!desk.array[3].toString().equals("4")) { // [3] should be 4
      System.out
          .println("Inde 3 should be 4, but it actually is " + desk.array[3].toString());
      return false;
    }
    return true;
  }

  /**
   * This method tests if parentOf() works well
   * 
   * @return true if parentOf() returns the correct correct index. False otherwise
   */
  public static boolean testHelpDeskParentof() {
    // For index within the valid range, parent of index should be (i - 1) / 2
    // First check if the input index is invalid, the exception will be thrown
    try {
      parentOf(-1);
      System.out.println("Index is invalid, IndexOutOfBoundsException should be thrown");
      return false;
    } catch (IndexOutOfBoundsException e) {
    }
    // Then check if the parentOf() return correct index with odd 5
    if (parentOf(5) != 2) {
      System.out
          .println("5's parent index should be 2, but it actually is " + parentOf(5));
      return false;
    }
    // Then check if the parentOf() return correct index with even 4
    if (parentOf(4) != 1) {
      System.out
          .println("4's parent index should be 1, but it actually is " + parentOf(4));
      return false;
    }
    return true; // Otherwise, parentOf() works well
  }

  /**
   * This method tests if leftChildOf() and rightChildOf() works well
   * 
   * @return true if leftChildOf() and rightChildOf() return the correct index. False
   *         otherwise
   */
  public static boolean testHelpDeskChildof() {
    // For index within the valid range, left child index should be i * 2 + 1, right
    // child index shoud be i * 2 + 2
    // First check if the input index is invalid, the exception will be thrown
    try {
      leftChildOf(-1);
      System.out.println("Index is invalid, IndexOutOfBoundsException should be thrown");
      return false;
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      rightChildOf(-1);
      System.out.println("Index is invalid, IndexOutOfBoundsException should be thrown");
      return false;
    } catch (IndexOutOfBoundsException e) {
    }
    // Then check if the leftChildOf() return correct index with odd 5
    if (leftChildOf(5) != 11) {
      System.out.println(
          "5's leftChild index should be 11, but it actually is " + leftChildOf(5));
      return false;
    }
    // Then check if the leftChildOf() return correct index with even 4
    if (leftChildOf(4) != 9) {
      System.out.println(
          "4's leftChild index should be 9, but it actually is " + leftChildOf(4));
      return false;
    }
    // Then check if the rightChildOf() return correct index with odd 3
    if (rightChildOf(3) != 8) {
      System.out.println(
          "3's rightChild index should be 8, but it actually is " + rightChildOf(3));
      return false;
    }
    // Then check if the rightChildOf() return correct index with even 4
    if (rightChildOf(4) != 10) {
      System.out.println(
          "4's rightChild index should be 10, but it actually is " + rightChildOf(4));
      return false;
    }
    return true; // Otherwise, parentOf() works well
  }

  /**
   * This method tests if testHelpDeskSwap() works well
   * 
   * @return true if swap
   */
  public static boolean testHelpDeskSwap() {
    HelpDesk desk = new HelpDesk(10); // Create a new HelpDesk
    // Create seven SupportTicket objects
    SupportTicket ticketA = new SupportTicket("2");
    SupportTicket ticketB = new SupportTicket("2");
    SupportTicket ticketC = new SupportTicket("3");
    SupportTicket ticketD = new SupportTicket("4");
    SupportTicket ticketE = new SupportTicket("5");

    // Then the array should be: "2", "2", "3', "4", "5"
    desk.array[0] = ticketA;
    desk.array[1] = ticketB;
    desk.array[2] = ticketC;
    desk.array[3] = ticketD;
    desk.array[4] = ticketE;
    desk.size = 5; // Size is 5.

    // When we swap [0] and [4], the array should be "5", "2", "3", "4", "2"
    // Call swap() with 0 and 4
    desk.swap(0, 4);
    if (!desk.array[0].toString().equals("5")) {
      System.out.println("Index 0 should be 5, but it actually is " + desk.array[0]);
      return false;
    }
    if (!desk.array[4].toString().equals("2")) {
      System.out.println("Index 4 should be 2, but it actually is " + desk.array[4]);
      return false;
    }
    // When we swap [1] and [2], the array should be "5", "3", "2", "4", "2"
    // Call swap() with 0 and 4
    desk.swap(1, 2);
    if (!desk.array[1].toString().equals("3")) {
      System.out.println("Index 1 should be 3, but it actually is " + desk.array[1]);
      return false;
    }
    if (!desk.array[2].toString().equals("2")) {
      System.out.println("Index 2 should be 2, but it actually is " + desk.array[2]);
      return false;
    }
    return true; // Otherwise, swap() works well
  }

  /**
   * This method tests if testHelDeskPropagateUp() works well
   * 
   * @return true if the index goes up and the array re-orders correctly. False otherwise
   */
  public static boolean testHelDeskPropagateUp() {
    { // Case 1
      HelpDesk desk = new HelpDesk(10); // Create a new HelpDesk
      // Create seven SupportTicket objects
      SupportTicket ticketA = new SupportTicket("2");
      SupportTicket ticketB = new SupportTicket("2");
      SupportTicket ticketC = new SupportTicket("3");
      SupportTicket ticketD = new SupportTicket("4");
      SupportTicket ticketE = new SupportTicket("5");

      // Then the array should be: "2", "2", "3', "4", "5"
      desk.array[0] = ticketA;
      desk.array[1] = ticketB;
      desk.array[2] = ticketC;
      desk.array[3] = ticketD;
      desk.array[4] = ticketE;
      desk.size = 5; // Size is 5.
      // Call propagateUp() with index 4
      desk.propagateUp(4);
      // After calling propagateUp() with index 4, the new order of array should be
      // "5", "2", "3", "4", "2"
      if (desk.array[0].toString() != "5") { // If [0] is not 5, return false
        System.out.println(
            "Index 0 should be 5, but it acctually is " + desk.array[0].toString());
        return false;
      }
      if (desk.array[1].toString() != "2") { // If [1] is not 2, return false
        System.out.println(
            "Index 1 should be 2, but it acctually is " + desk.array[1].toString());
        return false;
      }
      if (desk.array[2].toString() != "3") { // If [2] is not 3, return false
        System.out.println(
            "Index 2 should be 3, but it acctually is " + desk.array[2].toString());
        return false;
      }
      if (desk.array[3].toString() != "4") { // If [3] is not 4, return false
        System.out.println(
            "Index 3 should be 4, but it acctually is " + desk.array[3].toString());
        return false;
      }
      if (desk.array[4].toString() != "2") { // If [0] is not 2, return false
        System.out.println(
            "Index 4 should be 2, but it acctually is " + desk.array[4].toString());
        return false;
      }
      {
        // Case 2
        HelpDesk deskB = new HelpDesk(10); // Create a new HelpDesk
        // Create seven SupportTicket objects
        SupportTicket ticketAa = new SupportTicket("3");
        SupportTicket ticketBb = new SupportTicket("1");
        SupportTicket ticketCc = new SupportTicket("6");
        SupportTicket ticketDd = new SupportTicket("2");
        SupportTicket ticketEe = new SupportTicket("4");

        // Then the array should be: "3", "1", "6', "2", "4"
        deskB.array[0] = ticketAa;
        deskB.array[1] = ticketBb;
        deskB.array[2] = ticketCc;
        deskB.array[3] = ticketDd;
        deskB.array[4] = ticketEe;
        deskB.size = 5; // Size is 5.
        // Call propagateUp() with index 4
        deskB.propagateUp(4);
        // After calling propagateUp() with index 4, the new order of array should be
        // "4", "3", "6", "2", "1"
        if (deskB.array[0].toString() != "4") { // If [0] is not 4, return false
          System.out.println(
              "Index 0 should be 4, but it acctually is " + deskB.array[0].toString());
          return false;
        }
        if (deskB.array[1].toString() != "3") { // If [1] is not 3, return false
          System.out.println(
              "Index 1 should be 3, but it acctually is " + deskB.array[1].toString());
          return false;
        }
        if (deskB.array[2].toString() != "6") { // If [2] is not 6, return false
          System.out.println(
              "Index 2 should be 6, but it acctually is " + deskB.array[2].toString());
          return false;
        }
        if (deskB.array[3].toString() != "2") { // If [3] is not 2, return false
          System.out.println(
              "Index 3 should be 2, but it acctually is " + deskB.array[3].toString());
          return false;
        }
        if (deskB.array[4].toString() != "1") { // If [0] is not 1, return false
          System.out.println(
              "Index 4 should be 1, but it acctually is " + deskB.array[4].toString());
          return false;
        }
      }
      return true; // Otherwise, propagateUp() works well
    }
  }

  /**
   * This method tests if testJelpDeskPropagateDown() works well
   * 
   * @return true if the index goes down and the array re-orders correctly. False
   *         otherwise
   */
  public static boolean testJelpDeskPropagateDown() {
    { // Case 1
      HelpDesk desk = new HelpDesk(10); // Create a new HelpDesk
      // Create seven SupportTicket objects
      SupportTicket ticketA = new SupportTicket("2");
      SupportTicket ticketB = new SupportTicket("2");
      SupportTicket ticketC = new SupportTicket("3");
      SupportTicket ticketD = new SupportTicket("4");
      SupportTicket ticketE = new SupportTicket("5");

      // Then the array should be: "2", "2", "3', "4", "5"
      desk.array[0] = ticketA;
      desk.array[1] = ticketB;
      desk.array[2] = ticketC;
      desk.array[3] = ticketD;
      desk.array[4] = ticketE;
      desk.size = 5; // Size is 5.
      // Call propagateDown() with index 0
      desk.propagateDown(0);
      // After calling propagateDown() with index 0, the new order of array should be
      // "3", "2", "2", "4", "5"
      if (desk.array[0].toString() != "3") { // If [0] is not 3, return false
        System.out.println(
            "Index 0 should be 3, but it acctually is " + desk.array[0].toString());
        return false;
      }
      if (desk.array[1].toString() != "2") { // If [1] is not 2, return false
        System.out.println(
            "Index 1 should be 2, but it acctually is " + desk.array[1].toString());
        return false;
      }
      if (desk.array[2].toString() != "2") { // If [2] is not 2, return false
        System.out.println(
            "Index 2 should be 2, but it acctually is " + desk.array[2].toString());
        return false;
      }
      if (desk.array[3].toString() != "4") { // If [3] is not 4, return false
        System.out.println(
            "Index 3 should be 4, but it acctually is " + desk.array[3].toString());
        return false;
      }
      if (desk.array[4].toString() != "5") { // If [0] is not 5, return false
        System.out.println(
            "Index 4 should be 5, but it acctually is " + desk.array[4].toString());
        return false;
      }
    }
    {
      // Case 2
      HelpDesk deskB = new HelpDesk(10); // Create a new HelpDesk
      // Create seven SupportTicket objects
      SupportTicket ticketAa = new SupportTicket("3");
      SupportTicket ticketBb = new SupportTicket("1");
      SupportTicket ticketCc = new SupportTicket("6");
      SupportTicket ticketDd = new SupportTicket("2");
      SupportTicket ticketEe = new SupportTicket("4");

      // Then the array should be: "3", "1", "6', "2", "4"
      deskB.array[0] = ticketAa;
      deskB.array[1] = ticketBb;
      deskB.array[2] = ticketCc;
      deskB.array[3] = ticketDd;
      deskB.array[4] = ticketEe;
      deskB.size = 5; // Size is 5.
      // Call propagateUp() with index 0
      deskB.propagateDown(0);
      // After calling propagateUp() with index 4, the new order of array should be
      // "6", "1", "3", "2", "4"
      if (deskB.array[0].toString() != "6") { // If [0] is not 6, return false
        System.out.println(
            "Index 0 should be 6, but it acctually is " + deskB.array[0].toString());
        return false;
      }
      if (deskB.array[1].toString() != "1") { // If [1] is not 1, return false
        System.out.println(
            "Index 1 should be 1, but it acctually is " + deskB.array[1].toString());
        return false;
      }
      if (deskB.array[2].toString() != "3") { // If [2] is not 3, return false
        System.out.println(
            "Index 2 should be 3, but it acctually is " + deskB.array[2].toString());
        return false;
      }
      if (deskB.array[3].toString() != "2") { // If [3] is not 2, return false
        System.out.println(
            "Index 3 should be 2, but it acctually is " + deskB.array[3].toString());
        return false;
      }
      if (deskB.array[4].toString() != "4") { // If [0] is not 4, return false
        System.out.println(
            "Index 4 should be 4, but it acctually is " + deskB.array[4].toString());
        return false;
      }
    }
    return true; // Otherwise, propagateDown() works well
  }
  
  public static boolean test1() {
    HelpDesk desk = new HelpDesk(100);
    desk.createNewTicket("12");
    for (int i = 0; i < desk.size; i++) {
      System.out.print(desk.array[i] + " ");
      }
    System.out.println();
    
    desk.createNewTicket("1234");
    for (int i = 0; i < desk.size; i++) {
      System.out.print(desk.array[i] + " ");
      }
    System.out.println();
    
    desk.createNewTicket("1");
    for (int i = 0; i < desk.size; i++) {
      System.out.print(desk.array[i] + " ");
      }
    System.out.println();
    
    desk.createNewTicket("123456");
    for (int i = 0; i < desk.size; i++) {
      System.out.print(desk.array[i] + " ");
      }
    System.out.println();
   
    desk.closeNextTicket();
    for (int i = 0; i < desk.size; i++) {
      System.out.print(desk.array[i] + " ");
      }
    System.out.println();
   
    desk.createNewTicket("123");
    for (int i = 0; i < desk.size; i++) {
      System.out.print(desk.array[i] + " ");
      }
    System.out.println();
    
    desk.closeNextTicket();
    for (int i = 0; i < desk.size; i++) {
      System.out.print(desk.array[i] + " ");
      }
    System.out.println();
    
     desk.closeNextTicket();
    for (int i = 0; i < desk.size; i++) {
    System.out.print(desk.array[i] + " ");
    }
    
    return true;
  }

  /**
   * The main method calls all the test methods above and print the messages for
   * displaying the test outcomes
   * 
   * @param arg (input arguments if any)
   */
  public static void main(String arg[]) {
    /**if (testSuppotTicket()) {
      System.out.println("testSuppotTicket(): passed");
    }
    if (testHelpDeskCreateNewTicket()) {
      System.out.println("testHelpDeskCreateNewTicket(): passed");
    }
    if (testHelpDeskCheckNextTicket()) {
      System.out.println("testHelpDeskCheckNextTicket(): passed");
    }
    if (testJelpDeskPropagateDown()) {
      System.out.println("testJelpDeskPropagateDown(): passed");
    }
    if (testHelDeskPropagateUp()) {
      System.out.println("testHelDeskPropagateUp(): passed");
    }
    if (testHelpDeskCloseNextTicket()) {
      System.out.println("testHelpDeskCloseNextTicket(): passed");
    }
    if (testHelpDeskSwap()) {
      System.out.println("testHelpDeskSwap(): passed");
    }
    if (testHelpDeskParentof()) {
      System.out.println("testHelpDeskParentof(): passed");
    }
    if (testHelpDeskChildof()) {
      System.out.println("testHelpDeskChildof(): passed");
    }*/
    test1();
  }

}
