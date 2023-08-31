package duke;

/**
 * Responsible for the miscellaneous console outputs.
 */
public class UI {

  /**
   * Creates a UI object that prints out miscellaneous statements.
   */
  public UI() {}

  /**
   * Prints a divider line.
   */
  public void printLine() {
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Greets the user.
   */
  public void greet() {
    printLine();
    System.out.println("    Hello! I'm Duke");
    System.out.println("    What can I do for you?");
    printLine();
  }

  /**
   * Says bye to the user.
   */
  public void bye() {
    System.out.println("    Bye. Hope to see you again soon!");
    printLine();
  }

}
