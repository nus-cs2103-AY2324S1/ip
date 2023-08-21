import java.util.Scanner;

/**
 * Main Program for the application. <br>
 * As of Level-0, this has been renamed from Duke to TrackerBot
 * as part of the requirements for the iP.
 *
 * @author WZWren
 * @version Level-0
 */
public class TrackerBot {
  /** Name of the app. **/
  private static final String APP_NAME = "TrackerBot";

  /** Line separators for the console between paragraphs. **/
  private static final String FORMAT_LINE =
      "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

  /**
   * Greet function of the app. <br>
   * Prints a welcome message to the user on login.
   */
  private static void greet() {
    System.out.println(FORMAT_LINE);
    System.out.println("Greetings from " + APP_NAME + "!");
    System.out.println("How may I assist?");
    System.out.println(FORMAT_LINE);
  }

  /**
   * Exit function of the app. <br>
   * Prints an exit message to the user on logout.
   */
  private static void exit() {
    System.out.println("Thank you for using " + APP_NAME + ". Goodbye.");
  }

  /**
   * Input handler function of the app. <br>
   * Takes in a user input, and acts upon the input based on what input it gets. <br>
   * <ul>
   *   <li>If the input is "bye", exits the program.</li>
   *   <li>Otherwise, echoes the input back to the console.</li>
   * </ul>
   * @param str The input string that is given to the method.
   */
  private static void handleInput(String str) {
    System.out.println(FORMAT_LINE);
    // switch used for now: to handle future input cases.
    switch(str) {
      case "bye":
        exit();
        break;
      default:
        System.out.println(str);
    }
    System.out.println(FORMAT_LINE);
  }

  public static void main(String[] args) {
    greet();
    Scanner scanner = new Scanner(System.in);
    String input;
    do {
      // scanner.nextLine() blocks the main thread.
      input = scanner.nextLine();
      handleInput(input);
    } while (!input.equals("bye"));
  }
}
