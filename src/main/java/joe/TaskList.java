package joe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import joe.tasks.Task;

/** Represents a list of tasks. */
public class TaskList {
  private ArrayList<Task> tasks;

  /** Constructs a new TaskList. */
  public TaskList() {
    this.tasks = new ArrayList<Task>();
  }

  /**
   * Adds a task to the list.
   *
   * @param task The task to add.
   */
  public void add(Task task) {
    tasks.add(task);
  }

  /**
   * Retrieves a task at the specified index.
   *
   * @param idx The index of the task to retrieve.
   * @return The task at the specified index.
   */
  public Task get(int idx) {
    return tasks.get(idx);
  }

  /**
   * Removes a task at the specified index.
   *
   * @param idx The index of the task to remove.
   */
  public void remove(int idx) {
    tasks.remove(idx);
  }

  /**
   * Returns the number of tasks in the list.
   *
   * @return The number of tasks.
   */
  public int size() {
    return tasks.size();
  }

  /**
   * Returns a list of task as strings.
   *
   * @return A list of task as strings.
   */
  public List<String> getStringList() {
    return tasks.stream().map(Task::toString).collect(Collectors.toList());
  }

  /**
   * Returns a formatted string representation of the task list.
   *
   * @return A string representation of the task list.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Here are your tasks:\n");
    for (int i = 0; i < tasks.size(); i++) {
      sb.append(i + 1);
      sb.append(".");
      sb.append(tasks.get(i).toString());
      sb.append("\n");
    }
    sb.setLength(sb.length() - 1);
    return sb.toString();
  }
}
