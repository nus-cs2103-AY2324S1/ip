package duke;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a list of tasks, and supports methods like add, delete, mark, unmarked.
 */
public class TaskList {

  private LinkedList<Task> tasks = new LinkedList<>();

  /**
   * Creates a new TaskList with the given list.
   * @param tasks the tasks to be created
   */
  public TaskList(LinkedList<Task> tasks) {
    this.tasks = tasks;
  }

  public LinkedList<Task> getTaskList() {
    return this.tasks;
  }

  /**
   * List the current state of the tasks list
   */
  public void listTasks() {
    int i = 1;
    System.out.println("     Here are the tasks in your list:");
    for (Task task : tasks) {
      System.out.println("     " + i + "." + task);
      i += 1;
    }
  }

  /**
   * Adds the task to the list
   *
   * @param task the task to be added
   */
  public void addTask(Task task) {
    tasks.add(task);
    System.out.println("     " + "Got it. I've added this task:");
    System.out.println("       " + task.toString());
    System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
  }

  /**
   * Deletes the task at the specified position
   *
   * @param index position of task to be deleted
   * @throws WrongIndexException when index is out of bounds, or too big to parse
   */
  public void deleteTask(String index) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }
      int i = Integer.parseInt(index, 10) - 1;
      Task task = tasks.remove(i);
      System.out.println("     Noted. I've removed this task:");
      System.out.println("       " + task.toString());
      System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }

  /**
   * Marks the task as completed at the specified position
   *
   * @param index the position of the tasks to be mark completed
   * @throws WrongIndexException when index is out of bounds, or too big to parse
   */
  public void markTask(String index) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }
      int i = Integer.parseInt(index, 10) - 1;
      Task task = tasks.get(i);
      task.markCompleted();
      System.out.println("     Nice! I've marked this task as done:");
      System.out.println("       " + task.toString());

    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }

  /**
   * Unmarked the task as completed at the specified position.
   *
   * @param index the position of the tasks to be unmarked
   * @throws WrongIndexException when index is out of bounds, or too big to parse
   */
  public void unmarkedTask(String index) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }

      int i = Integer.parseInt(index, 10) - 1;
      Task task = tasks.get(i);
      task.markNotCompleted();
      System.out.println("     OK, I've marked this task as not done yet:");
      System.out.println("       " + task.toString());
    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }

  /**
   * Finds all the tasking matching the expression
   *
   * @param expr the expression to match
   * @return true if there is a match, false otherwise
   */
  public boolean findTasks(String expr) {
    System.out.println("     Here are the matching tasks in your list:");
    if (expr.equals("")) {
      return false;
    }
    boolean match = false;
    int i = 1;
    Pattern p = Pattern.compile(expr);
    for (Task task : tasks) {
      Matcher m = p.matcher(task.toString());
      if (m.find()) {
        System.out.println("     " + i + "." + task);
        match = true;
      }
      i += 1;
    }
    return match;
  }
}
