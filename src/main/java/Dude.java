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
  static String noTaskNumber = "Please specify a task number.";
  static String invalidTaskNumber =
    "I can't find the task numbered \"%s\".\nTry checking if you've typed the correct task number.";
  static String addedTask = "Got it! I've added this task:\n\t%s\nYou now have a total of %d task(s).";
  static String noTaskDescription = "Please specify a task description.";
  static String noDeadline = "Please specify a task deadline after the task\ndescription, prefixed by `\\by`.";

  static String noEventStart = "Please specify the event start date after the task\ndescription, prefixed by `\\from`.";
  static String noEventEnd = "Please specify the event end date after the task\n description and start date, prefixed by `\\to`.";
  static String markedAsDonePrefix = "Nice! I've marked this task as done:\n\t";
  static String markedAsNotDonePrefix = "Got it. I've marked this task as not done:\n\t";

  /**
   * Tasks stored by user.
   */
  public static Task[] tasks = new Task[100];
  /**
   * Number of tasks
   */
  public static int numTasks = 0;

  /**
   * Add task to tasks list.
   *
   * @param task Task to add.
   */
  public static void addTask(Task task) {
    tasks[numTasks] = task;
    numTasks += 1;
  }

  /**
   * Get task from list.
   *
   * @param task 1-based index of task to get.
   * @throws TaskOutOfBoundsException if task number does not exist
   */
  public static Task getTask(int task) throws TaskOutOfBoundsException {
    if (task > numTasks || task <= 0) {
      throw new TaskOutOfBoundsException();
    }
    return tasks[task - 1];
  }

  /**
   * Gets list of all tasks as a string.
   *
   * @return Tasks list formatted as a string.
   */
  public static String getTasksList() {
    if (numTasks == 0) {
      return emptyTaskList;
    }
    StringBuilder tasksList = new StringBuilder(taskListPrefix);
    for (int i = 0; i < numTasks; i++) {
      String taskNumberPrefix = String.format("%3s-", i + 1);
      String taskStr = taskNumberPrefix + tasks[i].toString() + "\n";
      tasksList.append(taskStr);
    }
    return tasksList.toString();
  }

  /**
   * Parses mark/unmark commands.
   *
   * @param input mark/unmark command.
   */
  public static void parseMarkUnmark(String input) {
    String[] splitInput = input.split(" ", 3);
    String cmd = splitInput[0];

    if (splitInput.length < 2) {
      // task number not specified
      printMessage(noTaskNumber);
    } else {
      String specifiedTask = splitInput[1];
      try {
        int taskNumber = Integer.parseInt(specifiedTask);
        Task task = getTask(taskNumber);
        if (cmd.equals("mark")) {
          task.markAsDone();
          printMessage(markedAsDonePrefix + task);
        } else if (cmd.equals("unmark")) {
          task.markAsNotDone();
          printMessage(markedAsNotDonePrefix + task);
        }
      } catch (NumberFormatException | TaskOutOfBoundsException e) {
        printMessage(String.format(invalidTaskNumber, specifiedTask));
      }
    }
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
      printMessage(String.format(addedTask, task, numTasks));
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
        printMessage(String.format(addedTask, task, numTasks));
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
          printMessage(String.format(addedTask, task, numTasks));
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
    String cmd = splitInput[0];

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
      case "unmark":
        parseMarkUnmark(input);
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
