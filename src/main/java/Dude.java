import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Dude (Duke, but renamed)
 */
public class Dude {
  // Save-file related constants
  /**
   * Path to save file
   */
  static final String SAVE_FILE_PATH = "./data/dude.txt";

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
  static String markedAsDonePrefix = "Nice! I've marked this task as done:\n\t";
  static String markedAsNotDonePrefix = "Got it. I've marked this task as not done:\n\t";

  /**
   * Tasks stored by user.
   */
  public static ArrayList<Task> tasks = new ArrayList<>();

  /**
   * Add task to tasks list.
   *
   * @param task Task to add
   * @throws SaveFileException if error saving
   */
  public static void addTask(Task task) throws SaveFileException {
    tasks.add(task);
    saveToSaveFile();
  }

  /**
   * Remove task from tasks list.
   *
   * @param index 1-based index of task to remove
   * @return Removed task
   * @throws SaveFileException if error saving
   */
  public static Task removeTask(int index) throws TaskOutOfBoundsException, SaveFileException {
    try {
      Task removedTask = tasks.remove(index - 1);
      saveToSaveFile();
      return removedTask;
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
   * @throws TaskDescriptionMissingException if task description is missing
   */
  public static void parseTodo(String input) throws TaskDescriptionMissingException, SaveFileException {
    String[] splitInput = input.split(" ", 2);
    if (splitInput.length < 2) {
      // task description not specified
      throw new TaskDescriptionMissingException();
    }
    String description = splitInput[1].trim();
    Task task = new ToDoTask(description);
    addTask(task);
    printMessage(String.format(addedTask, task, getNumTasks()));

  }

  /**
   * Parses deadline task command.
   *
   * @param input command.
   * @throws TaskDescriptionMissingException if task description is missing
   * @throws TaskDeadlineMissingException    if task deadline is missing
   */
  public static void parseDeadline(String input)
    throws TaskDescriptionMissingException, TaskDeadlineMissingException, SaveFileException {
    String[] splitInput = input.split(" ", 2);
    if (splitInput.length < 2) {
      // task description not specified
      throw new TaskDescriptionMissingException();
    }
    String[] splitDeadline = splitInput[1].split("/by", 2);
    if (splitDeadline.length < 2) {
      // deadline not specified
      throw new TaskDeadlineMissingException();
    }
    String description = splitDeadline[0].trim();
    String deadline = splitDeadline[1].trim();
    Task task = new DeadlineTask(description, deadline);
    addTask(task);
    printMessage(String.format(addedTask, task, getNumTasks()));
  }

  /**
   * Parses event task command.
   *
   * @param input command.
   * @throws TaskDescriptionMissingException if task description is missing
   * @throws EventStartMissingException      if event start is missing
   * @throws EventEndMissingException        if event end is missing
   */
  public static void parseEvent(String input)
    throws TaskDescriptionMissingException, EventStartMissingException, EventEndMissingException, SaveFileException {
    String[] splitInput = input.split(" ", 2);
    if (splitInput.length < 2) {
      // task description not specified
      throw new TaskDescriptionMissingException();
    }
    String[] splitStart = splitInput[1].split("/from", 2);
    if (splitStart.length < 2) {
      // start date not specified
      throw new EventStartMissingException();
    }
    String[] splitEnd = splitStart[1].split("/to", 2);
    if (splitEnd.length < 2) {
      // end date not specified
      throw new EventEndMissingException();
    }
    String description = splitStart[0].trim();
    String start = splitEnd[0].trim();
    String end = splitEnd[1].trim();
    Task task = new EventTask(description, start, end);
    addTask(task);
    printMessage(String.format(addedTask, task, getNumTasks()));
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
    String cmdString = splitInput[0].toLowerCase();
    try {
      DudeCommand cmd = DudeCommand.valueOf(cmdString);
      switch (cmd) {
        case bye:
          // quit
          // Print shutdown greeting
          printMessage(goodbye);
          return false;
        case list:
          // list tasks
          printMessage(getTasksList());
          break;
        case mark:
          // mark as done
          parseMark(input);
          break;
        case unmark:
          // mark as not done
          parseUnmark(input);
          break;
        case delete:
        case remove: // alias because I keep typing remove lol
          parseDelete(input);
          break;
        case todo:
          // add todo task to list
          parseTodo(input);
          break;
        case deadline:
          // add deadline task to list
          parseDeadline(input);
          break;
        case event:
          // add event task to list
          parseEvent(input);
          break;
        default:
      }
    } catch (IllegalArgumentException e) {
      // invalid command entered
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

  /**
   * Create new empty save file at SAVE_FILE_PATH if it does not already exist.
   *
   * @throws SaveFileException if file cannot be created
   */
  public static void createSaveFile() throws SaveFileException {
    File saveFile = new File(SAVE_FILE_PATH);
    File parent = saveFile.getParentFile();
    // check & create parent dir(s)
    if (parent != null && !parent.exists()) {
      parent.mkdirs();
    }
    try {
      // create save file
      saveFile.createNewFile();
    } catch (IOException e) {
      throw new SaveFileException("Error creating save file: " + e.getMessage());
    }
  }

  /**
   * Reads save file contents and sets list of Task instances.
   *
   * @throws SaveFileException        if there is an error reading the file
   * @throws InvalidTaskDataException if the text data format is invalid
   */
  public static void readSaveFile() throws SaveFileException, InvalidTaskDataException {
    try {
      File f = new File(SAVE_FILE_PATH);
      Scanner s = new Scanner(f);
      ArrayList<Task> readTasks = new ArrayList<>();
      while (s.hasNext()) {
        String data = s.nextLine();
        if (data.isBlank()) {
          continue;
        }
        String taskType = data.split(Task.DELIMITER_REGEX)[0];
        switch (taskType) {
          case "T":
            readTasks.add(ToDoTask.fromData(data));
            break;
          case "D":
            readTasks.add(DeadlineTask.fromData(data));
            break;
          case "E":
            readTasks.add(EventTask.fromData(data));
            break;
          default:
            throw new InvalidTaskDataException();
        }
      }
      tasks = readTasks;
    } catch (IOException e) {
      throw new SaveFileException("Error reading save file: " + e.getMessage());
    }
  }

  /**
   * Saves current list of tasks to save file.
   *
   * @throws SaveFileException if there is an error saving the file
   */
  public static void saveToSaveFile() throws SaveFileException {
    StringBuilder s = new StringBuilder();
    for (int i = 1; i <= getNumTasks(); i++) {
      s.append(getTask(i).toData());
    }
    try {
      FileWriter fw = new FileWriter(SAVE_FILE_PATH);
      fw.write(s.toString());
      fw.close();
    } catch (IOException e) {
      throw new SaveFileException("Error writing save file: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    try {
      // Startup
      createSaveFile();
      printMessage(hello); // Print startup greeting
      readSaveFile();

      // Input loop -- wait for input, respond, repeat
      boolean shouldContinue = true;
      while (shouldContinue) {
        try {
          // read user input
          String input = sc.nextLine();
          shouldContinue = parseInput(input);
        } catch (DudeException e) {
          printMessage(e.getMessage());
        }
      }
    } catch (DudeException e) {
      printMessage(e.getMessage());
    }
  }
}
