package duke.task;

import duke.exception.TaskListOutOfBoundsException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/** Represents the TaskList */
public class TaskList {
  private List<Task> tasks = new ArrayList<>();

  /**
   * Returns the task at index i (1-indexed)
   *
   * @param i the index of the task to return
   * @return the task at index i
   * @throws TaskListOutOfBoundsException if i is out of range
   */
  public Task getTask(int i) { // 1-indexed
    try {
      return tasks.get(i - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new TaskListOutOfBoundsException(i);
    }
  }

  /**
   * Adds Task t to TaskList
   *
   * @param t the task to add
   */
  public void addTask(Task t) {
    tasks.add(t);
  }

  /**
   * Returns a string describing how many tasks there are.
   *
   * @return a string describing how many tasks there are.
   */
  public String getNumberOfTasks() {
    return String.format(
        "Now you have %d task%s in the list.", tasks.size(), tasks.size() == 1 ? "" : "s");
  }

  /**
   * Deletes the task at i (1-indexed)
   *
   * @param i the index of the task to delete
   * @throws TaskListOutOfBoundsException if i is out of range
   */
  public void deleteTask(int i) {
    try {
      tasks.remove(i - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new TaskListOutOfBoundsException(i);
    }
  }

  /**
   * Returns the size of the tasklist
   *
   * @return the size of tasklist
   */
  public int size() {
    return tasks.size();
  }

  /**
   * Returns a string representation of tasklist, the commands used to populate tasklist.
   *
   * @return a string representation of the commands used to create tasklist.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    tasks.forEach(
        (task) -> {
          sb.append(task.toCommand());
          sb.append('\n');
        });
    return sb.toString().trim();
  }

  /**
   * Populates tasklist based on tasks
   *
   * @param tasks the tasks to fill tasklist
   */
  public void populate(List<Task> tasks) {
    this.tasks = tasks;
  }

  public Stream<Task> filter(Optional<LocalDateTime> before) {
    return tasks.stream().filter((task) -> task.filter(before));
  }

  public Stream<Task> filter(String search) {
    return tasks.stream().filter((task) -> task.filter(search));
  }
}
