import ip.utils.Pair;

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
      "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

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
   *   <li>If the input is "mark X", marks the item at index X.</li>
   *   <li>If the input is "unmark X", unmarks the item at index X.</li>
   *   <li>Otherwise, adds the item to the list.</li>
   * </ul>
   * @param str The input string that is given to the method.
   * @return true if the handler detects the bye keyword,
   *         false otherwise.
   */
  private static boolean handleInput(String str) {
    Pair<Command, String> input = Command.parse(str);
    Scanner scanner = new Scanner(input.getSecond());

    System.out.println(FORMAT_LINE);
    // switch used for now: to handle future input cases.
    switch(input.getFirst()) {
      case BYE:
        exit();
        return true;
      case LIST:
        list();
        break;
      case MARK:
        if (scanner.hasNextInt()) {
          mark(scanner.nextInt());
        } else {
          System.out.println("Compulsory parameter for mark should be a number.");
        }
        break;
      case UNMARK:
        if (scanner.hasNextInt()) {
          unmark(scanner.nextInt());
        } else {
          System.out.println("Compulsory parameter for unmark should be a number.");
        }
        break;
      default:
        add(str);
    }
    System.out.println(FORMAT_LINE);
    scanner.close();
    return false;
  }

  public static void main(String[] args) {
    greet();
    Scanner scanner = new Scanner(System.in);
    String input;
    boolean isBye;
    do {
      // scanner.nextLine() blocks the main thread.
      System.out.print("Format :: [keyword] [parse string] | ");
      input = scanner.nextLine();
      isBye = handleInput(input);
    } while (!isBye);
  }

  /**
   * Enum for the commands that a user can use.
   */
  public enum Command {
    /** Command to exit the program. **/
    BYE ("bye"),
    /** Command to list all tasks in the task list. **/
    LIST ("list"),
    /** Command to add a new task to the task list. **/
    ADD ("add"),
    /** Command to mark a task to be complete. **/
    MARK ("mark"),
    /** Command to mark a task as incomplete. **/
    UNMARK ("unmark"),
    /** Command to denote an unknown keyword call. **/
    UNKNOWN ("");

    /** The String representation of the enum. Used to parse the command into enum. **/
    private final String keyword;

    /**
     * Constructor for the enum Command. <br>
     * Enum constructors are implicitly private, so the tag is not included.
     * @param keyword The keyword of the task.
     */
    Command(String keyword) {
      this.keyword = keyword;
    }

    /**
     * Helper function. Splits the console input string into the invoking keyword
     * and its description after the invoking keyword. <br>
     * If no description exists after the keyword, an empty string is returned in the
     * second half of the Pair structure.
     *
     * @param input The unmodified console string that the user inputs.
     * @return A Pair&lt;Command, String&gt; object containing the enum and description.
     */
    public static Pair<Command, String> parse(String input) {
      Scanner scanner = new Scanner(input);
      // if the input is empty, return the unknown keyword with an empty description.
      if (!scanner.hasNext()) {
        scanner.close();
        return new Pair<>(Command.UNKNOWN, "");
      }

      String keyword = scanner.next();
      Command first = Command.UNKNOWN;

      for (Command command: Command.values()) {
        if (keyword.equals(command.keyword)) {
          first = command;
          break;
        }
      }

      if (first.equals(Command.UNKNOWN) || !scanner.hasNextLine()) {
        scanner.close();
        return new Pair<>(first, "");
      }

      String second = scanner.nextLine().trim();
      scanner.close();
      return new Pair<>(first, second);
    }
  }
}
