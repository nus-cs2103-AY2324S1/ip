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
    System.out.println(FORMAT_LINE);
    System.out.println("Thank you for using " + APP_NAME + ". Goodbye.");
    System.out.println(FORMAT_LINE);
  }

  public static void main(String[] args) {
    greet();
    exit();
  }
}
