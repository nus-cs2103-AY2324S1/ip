package dude.task;

import dude.exception.InvalidTaskIndexException;

import java.util.ArrayList;

/**
 * List of tasks.
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
   * @param task Task to add.
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
   * Returns list of all tasks as a string.
   *
   * @return Tasks list formatted as a string.
   */
  @Override
  public String toString() {
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
   * Gets list of all tasks as an ArrayList.
   *
   * @return ArrayList of task instances.
   */
  public ArrayList<Task> toArrayList() {
    return tasks;
  }
}
