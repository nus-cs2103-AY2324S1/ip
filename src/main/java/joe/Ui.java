package joe;

import java.util.Scanner;

/** Represents the user interface for interacting with the program. */
public class Ui {
  private final Scanner scanner;

  /** Constructs a new Ui object. */
  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  /** Displays a greeting message to the user. */
  public void greet() {
    System.out.println("Hello! I'm Joe\nWhat can I do for you?");
  }

  /**
   * Reads a command input from the user.
   *
   * @return The user's input command.
   */
  public String readCommand() {
    return scanner.nextLine();
  }

  /** Prints a newline to the console. */
  public void newLine() {
    System.out.println();
  }

  /** Performs the necessary clean up when exiting the program. */
  public void exit() {
    scanner.close();
  }

  /**
   * Shows a message to the user.
   *
   * @param msg The message to be printed.
   */
  public void print(String msg) {
    System.out.println(msg);
  }
}
