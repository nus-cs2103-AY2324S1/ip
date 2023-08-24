import java.util.ArrayList;
import java.util.Scanner;

/**
 * Dude (Duke, but renamed)
 */
public class Dude {
  // Messages
  static String logo =
    " _|    _| _    O\n" +
      "(_||_|(_|(/_  /İ\\\n" +
      "------------  ```\n";
  static String border = "-----------------------------------------------------------\n";
  static String hello = logo +
    "Hello! I'm dude.\n" +
    "What can I do for you?";
  static String goodbye =
    "Bye. Hope to see you again soon!";
  static String taskListPrefix = "Here's your tasks list:\n";
  static String emptyTaskList = "You currently have no tasks in your list.";
  static String addedTask = "Got it! I've added this task:\n\t%s\nYou now have a total of %d task(s).";
  static String deletedTask = "Got it! I've removed this task:\n\t%s\nYou now have a total of %d task(s).";
  static String noTaskDescription = "Please specify a task description.";
  static String noDeadline = "Please specify a task deadline after the task\ndescription, prefixed by `\\by`.";

  static String noEventStart = "Please specify the event start date after the task\ndescription, prefixed by `\\from`.";
  static String noEventEnd = "Please specify the event end date after the task\n description and start date, prefixed by `\\to`.";
  static String markedAsDonePrefix = "Nice! I've marked this task as done:\n\t";
  static String markedAsNotDonePrefix = "Got it. I've marked this task as not done:\n\t";

  /**
   * Tasks stored by user.
   */
  public static ArrayList<Task> tasks = new ArrayList<>();

  /**
   * Add task to tasks list.
   *
   * @param task Task to add.
   */
  public static void addTask(Task task) {
    tasks.add(task);
  }

  /**
   * Remove task from tasks list.
   *
   * @param index 1-based index of task to remove.
   * @return Removed task.
   */
  public static Task removeTask(int index) throws TaskOutOfBoundsException {
    try {
      return tasks.remove(index - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new TaskOutOfBoundsException();
    }
  }

  /**
   * Get number of tasks.
   *
   * @return Number of tasks.
   */
  public static int getNumTasks() {
    return tasks.size();
  }

  /**
   * Get task from list.
   *
   * @param index 1-based index of task to get.
   * @throws TaskOutOfBoundsException if task number does not exist
   */
  public static Task getTask(int index) throws TaskOutOfBoundsException {
    try {
      return tasks.get(index - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new TaskOutOfBoundsException();
    }
  }

  /**
   * Gets list of all tasks as a string.
   *
   * @return Tasks list formatted as a string.
   */
  public static String getTasksList() {
    if (tasks.isEmpty()) {
      return emptyTaskList;
    }
    StringBuilder tasksList = new StringBuilder(taskListPrefix);
    for (int i = 0; i < getNumTasks(); i++) {
      String taskNumberPrefix = String.format("%3s-", i + 1);
      String taskStr = taskNumberPrefix + tasks.get(i).toString() + "\n";
      tasksList.append(taskStr);
    }
    return tasksList.toString();
  }

  /**
   * Parses command with task number with format `{cmd} {index}`, getting corresponding task.
   *
   * @param input command.
   * @return index of task with index specified in commands
   * @throws InvalidTaskIndexException if index is invalid
   * @throws TaskIndexMissingException if index is not specified
   */
  public static int parseTaskIndexCommand(String input) throws InvalidTaskIndexException, TaskIndexMissingException {
    String[] splitInput = input.split(" ", 3);
    if (splitInput.length < 2) {
      // task number not specified
      throw new TaskIndexMissingException();
    } else {
      String specifiedTask = splitInput[1];
      try {
        int index = Integer.parseInt(specifiedTask);
        getTask(index);
        return index;
      } catch (NumberFormatException | TaskOutOfBoundsException e) {
        // cannot parse number from input
        throw new InvalidTaskIndexException(specifiedTask);
      }
    }
  }

  /**
   * Parses delete commands.
   *
   * @param input delete command.
   * @throws DudeException if parsing task from command fails
   */
  public static void parseDelete(String input) throws DudeException {
    int taskToDelete = parseTaskIndexCommand(input);
    Task task = removeTask(taskToDelete);
    printMessage(String.format(deletedTask, task, getNumTasks()));
  }

  /**
   * Parses mark commands.
   *
   * @param input mark command.
   * @throws DudeException if parsing task from command fails
   */
  public static void parseMark(String input) throws DudeException {
    int taskToMark = parseTaskIndexCommand(input);
    Task task = getTask(taskToMark);
    task.markAsDone();
    printMessage(markedAsDonePrefix + task);
  }

  /**
   * Parses unmark commands.
   *
   * @param input unmark command.
   * @throws DudeException if parsing task from command fails
   */
  public static void parseUnmark(String input) throws DudeException {
    int taskToUnmark = parseTaskIndexCommand(input);
    Task task = getTask(taskToUnmark);
    task.markAsNotDone();
    printMessage(markedAsNotDonePrefix + task);
  }

  /**
   * Parses todo task command.
   *
   * @param input command.
   */
  public static void parseTodo(String input) {
    String[] splitInput = input.split(" ", 2);

    if (splitInput.length < 2) {
      // task description not specified
      printMessage(noTaskDescription);
    } else {
      String description = splitInput[1].trim();
      Task task = new ToDoTask(description);
      addTask(task);
      printMessage(String.format(addedTask, task, getNumTasks()));
    }
  }

  /**
   * Parses deadline task command.
   *
   * @param input command.
   */
  public static void parseDeadline(String input) {
    String[] splitInput = input.split(" ", 2);

    if (splitInput.length < 2) {
      // task description not specified
      printMessage(noTaskDescription);
    } else {
      String[] splitDeadline = splitInput[1].split("/by", 2);
      if (splitDeadline.length < 2) {
        // deadline not specified
        printMessage(noDeadline);
      } else {
        String description = splitDeadline[0].trim();
        String deadline = splitDeadline[1].trim();
        Task task = new DeadlineTask(description, deadline);
        addTask(task);
        printMessage(String.format(addedTask, task, getNumTasks()));
      }
    }
  }

  /**
   * Parses event task command.
   *
   * @param input command.
   */
  public static void parseEvent(String input) {
    String[] splitInput = input.split(" ", 2);

    if (splitInput.length < 2) {
      // task description not specified
      printMessage(noTaskDescription);
    } else {
      String[] splitStart = splitInput[1].split("/from", 2);
      if (splitStart.length < 2) {
        // start date not specified
        printMessage(noEventStart);
      } else {
        String[] splitEnd = splitStart[1].split("/to", 2);
        if (splitEnd.length < 2) {
          // end date not specified
          printMessage(noEventEnd);
        } else {
          String description = splitStart[0].trim();
          String start = splitEnd[0].trim();
          String end = splitEnd[1].trim();
          Task task = new EventTask(description, start, end);
          addTask(task);
          printMessage(String.format(addedTask, task, getNumTasks()));
        }
      }
    }
  }

  /**
   * Parse input and act accordingly.
   *
   * @param input Input to parse.
   * @return `true` if a subsequent command can be taken, `false` if not (i.e. quit)
   */
  public static boolean parseInput(String input) throws DudeException {
    // extract command (strip leading and trailing whitespace, take first word)
    String[] splitInput = input.split(" ", 2);
    String cmd = splitInput[0].toLowerCase();

    switch (cmd) {
      case "bye":
        // quit
        // Print shutdown greeting
        printMessage(goodbye);
        return false;
      case "list":
        // list tasks
        printMessage(getTasksList());
        break;
      case "mark":
        // mark as done
        parseMark(input);
        break;
      case "unmark":
        // mark as not done
        parseUnmark(input);
        break;
      case "delete":
      case "remove": // alias because I keep typing remove lol
        parseDelete(input);
        break;
      case "todo":
        // add todo task to list
        parseTodo(input);
        break;
      case "deadline":
        // add deadline task to list
        parseDeadline(input);
        break;
      case "event":
        // add event task to list
        parseEvent(input);
        break;
      default:
        throw new InvalidCommandException();
    }
    return true;
  }

  /**
   * Format and print given message/prompt to console.
   *
   * @param message Message to print. Lines separated by \n.
   */
  public static void printMessage(String message) {
    String[] lines = message.split("\\n");
    String prefix = "  ";
    String output = border + prefix +
      String.join("\n" + prefix, lines) +
      "\n" + border;
    System.out.println(output);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // Print startup greeting
    printMessage(hello);

    // Input loop -- wait for input, respond, repeat
    boolean shouldContinue = true;
    while (shouldContinue) {
      // read user input
      String input = sc.nextLine();
      try {
        shouldContinue = parseInput(input);
      } catch (DudeException e) {
        printMessage(e.getMessage());
      }
    }
  }
}
