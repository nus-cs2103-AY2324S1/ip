import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Task is a class that represents a task that can be added for the chatbot to keep track of.
 * It also manages the list of tasks in a local database.
 */
public abstract class Task {

  /**
   * List of all tasks added.
   */
  public static ArrayList<Task> taskList = new ArrayList<>();
  private String taskName;
  private boolean isDone;

  private static final Path DATABASE_PATH = java.nio.file.Paths.get(
      System.getProperty("user.dir"), "data", "duke.txt");

  public Task(String taskName) {
    this.taskName = taskName;
    this.isDone = false;
  }

  /**
   * Turns the task into a string that can be stored in the database.
   * @return The task information in the form of a string.
   */
  protected abstract String toDatabaseRepresentation();

  /**
   * Adds a task to the list.
   * @param task The task to be added.
   */
  public static void addTask(Task task) {
    taskList.add(task);
    Duke.printLine();
    System.out.println("Got it. I've added this task:");
    System.out.println(task.toString());
    System.out.printf("Now you have %s tasks in the list.%n", taskList.size());
    Duke.printLine();

    writeToDatabase();
  }

  /**
   * Marks a task as done.
   * @param i The index of the task in the list (1-based indexing).
   */
  public static void markTask(int i) {
    taskList.get(i - 1).isDone = true;
    Duke.printLine();
    System.out.println("Nice! I've marked this task as done:");
    System.out.println(taskList.get(i - 1).toString());

    writeToDatabase();
  }

  /**
   * Marks a task as not done.
   * @param i The index of the task in the list (1-based indexing).
   */
  public static void unmarkTask(int i) {
    taskList.get(i - 1).isDone = false;
    System.out.println("OK, I've marked this task as not done yet:");
    System.out.println(taskList.get(i - 1).toString());

    writeToDatabase();
  }

  protected boolean isDone() {
    return isDone;
  }

  /**
   * Deletes a task from the list.
   * @param i The index of the task in the list (1-based indexing).
   */
  public static void deleteTask(int i) {
    Task removedTask = taskList.remove(i - 1);
    Duke.printLine();
    System.out.println("Noted. I've removed this task:");
    System.out.println(removedTask.toString());
    System.out.printf("Now you have %s tasks in the list.%n", taskList.size());

    writeToDatabase();
  }

  /**
   * Lists out all the tasks that are in the list.
   */
  public static void listTasks() {
    Duke.printLine();
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < taskList.size(); i++) {
      System.out.printf("%s.%s%n", i + 1, taskList.get(i).toString());
    }
    Duke.printLine();
  }

  protected String getTaskName() {
    return this.taskName;
  }

  private String getStatusIcon() {
    return this.isDone ? "[X]" : "[ ]";
  }

  public String toString() {
    return String.format("%s %s", this.getStatusIcon(), this.getTaskName());
  }

  /**
   * Creates a local text file for saving the lists of tasks if it does not exist.
   */
  public static void createDatabase() {
    File database = new File(DATABASE_PATH.toString());

    try {
      database.getParentFile().mkdirs();
      database.createNewFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Checks if the database exists.
   * @return True if the text file exists.
   */
  public static boolean databaseExist() {
    File database = new File(DATABASE_PATH.toString());
    return database.exists();
  }

  /**
   * Parses the string of tasks from the database into Task objects and stores them in taskList.
   */
  public static void readFromDatabase() {
    taskList.clear();

    try {
      FileReader reader = new FileReader(DATABASE_PATH.toString());
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        taskList.add(stringToTask(line));
      }
      reader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void writeToDatabase() {
    try {
      FileWriter writer = new FileWriter(DATABASE_PATH.toString(), false);
      BufferedWriter bufferedWriter = new BufferedWriter(writer);

      for (Task task : taskList) {
        bufferedWriter.write(task.toDatabaseRepresentation());
        bufferedWriter.newLine();
      }

      bufferedWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static Task stringToTask(String line) {
    try {
      Task newTask;
      String[] taskInfo = line.split(" \\| ");

      switch (taskInfo[0]) {
      case "T":
        newTask = new Todo(taskInfo[2]);
        break;
      case "D":
        newTask = new Deadline(taskInfo[2], taskInfo[3]);
        break;
      case "E":
        newTask = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
        break;
      default:
        throw new DukeException("Database is corrupted.");
      }

      if (taskInfo[1].equals("1")) {
        newTask.isDone = true;
      }

      return newTask;
    } catch (DukeException e) {
      throw new RuntimeException(e);
    }
  }

  protected LocalDateTime stringToDate(String input) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    return LocalDateTime.parse(input, formatter);
  }

  protected String dateToDatabaseRepresentation(LocalDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    return date.format(formatter);
  }

  protected String dateToString(LocalDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
    return date.format(formatter);
  }
}
