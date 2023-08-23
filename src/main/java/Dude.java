import java.util.Scanner;

public class Dude {
  // Messages
  static String logo =
    " _|    _| _    O\n" +
      "(_||_|(_|(/_  /İ\\\n";
  static String border = "-----------------------------------------------------------\n";
  static String hello = logo + "\n" +
    "Hello! I'm dude.\n" +
    "What can I do for you?";
  static String goodbye =
    "Bye. Hope to see you again soon!";
  static String taskListPrefix = "Here's your tasks list:\n";
  static String emptyTaskList = "You currently have no tasks in your list.";
  static String invalidCommand =
    "I don't know what that means. Try checking if you've typed the command correctly.";
  static String noTaskNumber = "Please specify a task number.";
  static String invalidTaskNumber =
    "I can't find the task numbered \"%s\". Try checking if you've typed the correct task number.";
  static String addedTask = "Got it! I've added this task:\n\t%s\nYou now have a total of %d task(s).";
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
   * @param description Description of task to add.
   * @return Task that was added.
   */
  public static Task addTask(String description) {
    Task newTask = new Task(description);
    tasks[numTasks] = newTask;
    numTasks += 1;
    return newTask;
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
      String taskStr = taskNumberPrefix + tasks[i].getTaskString() + "\n";
      tasksList.append(taskStr);
    }
    return tasksList.toString();
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
      String.join("\n" + prefix, lines) + "\n" +
      border;
    System.out.println(output);
  }

  /**
   * Parse input and act accordingly.
   *
   * @param input Input to parse.
   * @return `true` if a subsequent command can be taken, `false` if not (i.e. quit)
   */
  public static boolean parseInput(String input) {
    // extract command (strip leading and trailing whitespace, take first word)
    String[] splitInput = input.split(" ");
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
        // mark task as done
        if (splitInput.length < 2) {
          printMessage(noTaskNumber);
        } else {
          String specifiedTask = splitInput[1];
          try {
            int taskNumber = Integer.parseInt(specifiedTask);
            Task task = getTask(taskNumber);
            task.markAsDone();
            printMessage(markedAsDonePrefix + task.getTaskString());
          } catch (NumberFormatException | TaskOutOfBoundsException e) {
            printMessage(String.format(invalidTaskNumber, specifiedTask));
          }
        }
        break;
      case "unmark":
        // mark task as not done
        if (splitInput.length < 2) {
          printMessage(noTaskNumber);
        } else {
          String specifiedTask = splitInput[1];
          try {
            int taskNumber = Integer.parseInt(specifiedTask);
            Task task = getTask(taskNumber);
            task.markAsNotDone();
            printMessage(markedAsNotDonePrefix + task.getTaskString());
          } catch (NumberFormatException | TaskOutOfBoundsException e) {
            printMessage(String.format(invalidTaskNumber, specifiedTask));
          }
        }
        break;
      default:
        // add task to list
        Task task = addTask(input);
        printMessage(String.format(addedTask, task.getTaskString(), numTasks));
    }
    return true;
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
      shouldContinue = parseInput(input);
    }
  }
}
