package duke.utils;

import java.util.Scanner;

public class Ui {

  private Scanner scanner;

  public static String line = 
  "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n";

  public static String logo = 
  "     ______     _                     ______          __     ____        __ \n"+
  "    / ____/____(_)___  ____ ____     /_  __/__  _  __/ /_   / __ )____  / /_\n"+
  "   / /   / ___/ / __ \\/ __ `/ _ \\     / / / _ \\| |/_/ __/  / __  / __ \\/ __/\n"+
  "  / /___/ /  / / / / / /_/ /  __/    / / /  __/>  </ /_   / /_/ / /_/ / /_  \n"+
  "  \\____/_/  /_/_/ /_/\\__, /\\___/    /_/  \\___/_/|_|\\__/  /_____/\\____/\\__/  \n"+
  "                    /____/                                                  \n";

  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Displays the welcome message to the user.
   */
  public void showWelcome() {
    System.out.println(String.format("%sHi, I am a\n%s\nHow can I help you hehe.. (° ͜ʖ °)\n%s", line, logo, line));
  }

  /**
   * Reads a command from the input stream, prints, then returns it. This method is called by the command line interface to allow the user to select a command to be executed.
   * 
   * @return String command entered by the user.
   */
  public String readCommand() {
    String input = scanner.nextLine();
    System.out.println(String.format("You said: %s\n%s", input, line));
    return input;
  }

  /**
   * Prints a string to the console. This is a convenience method for System.out.println(str).
   * 
   * @param str string to print
   */
  public void print(String str) {
    System.out.println(str);
  }

  /**
   * Prints a divider line to the console.
   */
  public void showLine() {
    System.out.println(line);
  }

  /**
   * Displays an error message to the user when the file cannot be loaded.
   */
  public void showLoadingError() {
    System.out.println("File failed to load");
  }

  /**
   * Prints an error message to the console. This method is called by the index file when an error occurs.
   * 
   * @param message for display to the user.
   */
  public void showError(String message) {
    System.out.println(message);
  }

  /**
   * Close the scanner and show goodbye. This is called when the user inputs the closing command.
   */
  public void showGoodbye() {
    scanner.close();
  }
  
}
