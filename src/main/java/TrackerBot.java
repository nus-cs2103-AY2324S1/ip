import java.util.Scanner;

/**
 * Main Program for the application. <br>
 * As of Level-0, this has been renamed from Duke to TrackerBot
 * as part of the requirements for the iP.
 *
 * @author WZWren
 * @version Level-3
 */
public class TrackerBot {
  /** Name of the app. **/
  private static final String APP_NAME = "TrackerBot";

  /** Line separators for the console between paragraphs. **/
  private static final String FORMAT_LINE =
      "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

  /**
   * Task Array - as TrackerBot is not instantiated, this must be static.
   * The Task List array itself should be immutable, in case we override it
   * during runtime.
   */
  private static final Task[] TASK_LIST = new Task[100];

  /** Tracks the number of items in the task list. */
  private static int taskCounter = 0;

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
   * List function of the app. <br>
   * Prints the list of tasks in the bot.
   */
  private static void list() {
    // happy path: prints an appropriate message and exit the method.
    if (taskCounter == 0) {
      System.out.println("No tasks have been added to the list yet.");
      return;
    }

    System.out.println("I am tracking these tasks:");
    for (int i = 1; i < taskCounter + 1; i++) {
      System.out.println(i + ". " + TASK_LIST[i - 1].toString());
    }
  }

  /**
   * Add function of the app. <br>
   * Adds the item to the task list. We do not handle the OutOfBounds case as
   * the tasks is assumed to be less than 100.
   * @param str The String of the task to add to the list.
   */
  private static void add(String str) {
    Task newTask = new Task(str);
    TASK_LIST[taskCounter] = newTask;
    taskCounter++;

    System.out.println("Now tracking: \n  " + newTask);
  }

  /**
   * Helper function for mark - checks if the index is valid.
   * @param index The index of the list to check.
   * @return The Task object at the index, if it exists, and null otherwise.
   */
  private static Task getTask(int index) {
    // happy path: return null if out of bounds.
    if (index <= 0 || index > taskCounter + 1) {
      return null;
    }
    return TASK_LIST[index - 1];
  }

  /**
   * Mark function for the app. <br>
   * Attempts to mark the item in the task list as completed. If the Task is already
   * completed, or the Task does not exist, prints an appropriate error message.
   * @param index The index of the list to mark.
   */
  private static void mark(int index) {
    Task task = getTask(index);
    // happy path: the task does not exist.
    if (task == null) {
      System.out.println("That task is not on the list!");
      System.out.println("Use \"list\" to display what I am currently tracking.");
      return;
    }

    if (task.markTask()) {
      System.out.println("This task has been marked as completed.\n  " + task);
    } else {
      System.out.println("This task has already been completed!\n  " + task);
    }
  }

  /**
   * Unmark function for the app. <br>
   * Attempts to unmark the item in the task list as incomplete. If the Task is already
   * incomplete, or the Task does not exist, prints an appropriate error message.
   * @param index The index of the list to unmark.
   */
  private static void unmark(int index) {
    Task task = getTask(index);
    // happy path: the task does not exist.
    if (task == null) {
      System.out.println("That task is not on the list!");
      System.out.println("Use \"list\" to display what I am currently tracking.");
      return;
    }

    if (task.unmarkTask()) {
      System.out.println("The task has been marked as incomplete.\n  " + task);
    } else {
      System.out.println("This task is already in progress.\n  " + task);
    }
  }

  /**
   * Input handler function of the app. <br>
   * Takes in a user input, and acts upon the input based on what input it gets. <br>
   * <ul>
   *   <li>If the input is "bye", exits the program by returning true.</li>
   *   <li>If the input is "list", prints the list.</li>
   *   <li>Otherwise, adds the item to the list.</li>
   * </ul>
   * @param str The input string that is given to the method.
   * @return true if the handler detects the bye keyword,
   *         false otherwise.
   */
  private static boolean handleInput(String str) {
    System.out.println(FORMAT_LINE);
    // switch used for now: to handle future input cases.
    switch(str) {
      case "bye":
        exit();
        return true;
      case "list":
        list();
        break;
      default:
        add(str);
    }
    System.out.println(FORMAT_LINE);
    return false;
  }

  public static void main(String[] args) {
    greet();
    Scanner scanner = new Scanner(System.in);
    String input;
    boolean isBye;
    do {
      // scanner.nextLine() blocks the main thread.
      input = scanner.nextLine();
      isBye = handleInput(input);
    } while (!isBye);
  }
}
