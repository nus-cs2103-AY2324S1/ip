package dude.task;

import dude.exception.InvalidTaskIndexException;

import java.util.ArrayList;

/**
 * Handles task list operations.
 */
public class TaskList {
  /**
   * Tasks stored by user.
   */
  private final ArrayList<Task> tasks;

  public static final String emptyTaskList = "You currently have no tasks in your list.";

  public static final String taskListPrefix = "Here's your tasks list:\n";

  /**
   * Constructor for empty TaskList.
   */
  public TaskList() {
    tasks = new ArrayList<>();
  }

  /**
   * Constructor for TaskList from existing list of tasks.
   *
   * @param tasks ArrayList of existing tasks.
   */
  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * Add task to tasks list.
   *
   * @param task Task to add
   */
  public void add(Task task) {
    tasks.add(task);
  }

  /**
   * Remove task from tasks list.
   *
   * @param index 1-based index of task to remove.
   * @return Removed task.
   * @throws InvalidTaskIndexException If task number does not exist.
   */
  public Task remove(int index) throws InvalidTaskIndexException {
    try {
      return tasks.remove(index - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskIndexException(String.format("%d", index));
    }
  }

  /**
   * Get number of tasks.
   *
   * @return Number of tasks.
   */
  public int getNumTasks() {
    return tasks.size();
  }

  /**
   * Get task from list.
   *
   * @param index 1-based index of task to get.
   * @throws InvalidTaskIndexException If task number does not exist.
   */
  public Task getTask(int index) throws InvalidTaskIndexException {
    try {
      return tasks.get(index - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskIndexException(String.format("%d", index));
    }
  }

  /**
   * Finds list of tasks with descriptions containing given substring.
   *
   * @param substring Substring to search (case-insensitive).
   * @return ArrayList of tasks with given keyword.
   */
  public ArrayList<Task> searchDescriptions(String substring) {
    ArrayList<Task> results = new ArrayList<>();
    for (Task task : tasks) {
      if (task.description.toLowerCase().contains(substring.strip().toLowerCase())) {
        results.add(task);
      }
    }
    return results;
  }

  /**
   * Returns list of given tasks as a string.
   *
   * @param tasks ArrayList of tasks to list.
   * @return Tasks list formatted as a string.
   */
  public static String displayList(ArrayList<Task> tasks) {
    if (tasks.isEmpty()) {
      return emptyTaskList;
    }
    StringBuilder tasksList = new StringBuilder();
    for (int i = 0; i < tasks.size(); i++) {
      String taskNumberPrefix = String.format("%3s-", i + 1);
      String taskStr = taskNumberPrefix + tasks.get(i).toString() + "\n";
      tasksList.append(taskStr);
    }
    return tasksList.toString();
  }

  /**
   * Returns list of all tasks as a string.
   *
   * @return Tasks list formatted as a string.
   */
  @Override
  public String toString() {
    return taskListPrefix + displayList(tasks);
  }

  /**
   * Gets list of all tasks as an ArrayList.
   *
   * @return ArrayList of task instances.
   */
  public ArrayList<Task> toArrayList() {
    return tasks;
  }
}
