import java.util.ArrayList;

/**
 * Task is a class that represents a task that can be added for the chatbot to keep track of.
 */
public class Task {

  /**
   * List of all tasks added.
   */
  public static ArrayList<Task> taskList = new ArrayList<>();
  private String taskName;
  private boolean isDone;

  public Task(String taskName) {
    this.taskName = taskName;
    this.isDone = false;
  }

  /**
   * Add a task to the list.
   * @param task The task to be added.
   */
  public static void addTask(Task task) {
    taskList.add(task);
    Duke.printLine();
    System.out.println("Got it. I've added this task:");
    System.out.println(task.toString());
    System.out.printf("Now you have %s tasks in the list.%n", taskList.size());
    Duke.printLine();
  }

  /**
   * Mark a task as done.
   * @param i The index of the task in the list (1-based indexing).
   */
  public static void markTask(int i) {
    taskList.get(i - 1).isDone = true;
    Duke.printLine();
    System.out.println("Nice! I've marked this task as done:");
    System.out.println(taskList.get(i - 1).toString());
  }

  /**
   * Mark a task as not done.
   * @param i The index of the task in the list (1-based indexing).
   */
  public static void unmarkTask(int i) {
    taskList.get(i - 1).isDone = false;
    System.out.println("OK, I've marked this task as not done yet:");
    System.out.println(taskList.get(i - 1).toString());
  }

  /**
   * Delete a task from the list.
   * @param i The index of the task in the list (1-based indexing).
   */
  public static void deleteTask(int i) {
    Task removedTask = taskList.remove(i - 1);
    Duke.printLine();
    System.out.println("Noted. I've removed this task:");
    System.out.println(removedTask.toString());
    System.out.printf("Now you have %s tasks in the list.%n", taskList.size());
  }

  /**
   * List out all the tasks that are in the list.
   */
  public static void listTasks() {
    Duke.printLine();
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < taskList.size(); i++) {
      System.out.printf("%s.%s%n", i + 1, taskList.get(i).toString());
    }
    Duke.printLine();
  }

  private String getTaskName() {
    return this.taskName;
  }

  private String getStatusIcon() {
    return this.isDone ? "[X]" : "[ ]";
  }

  public String toString() {
    return String.format("%s %s", this.getStatusIcon(), this.getTaskName());
  }
}
