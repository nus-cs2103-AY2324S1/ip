package cyrus.tasks;

import cyrus.storage.IStorage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Wrapper around the list of {@code Task}.
 */
public class TaskList {
  private final List<Task> tasks;
  private final IStorage storage;

  public TaskList(IStorage storage) {
    this.storage = storage;
    this.tasks = storage.load();
  }

  /**
   * Adds task and saves to file.
   *
   * @param task {@code Task} to add
   */
  public void addTask(Task task) {
    this.tasks.add(task);
    this.saveTasks();
  }

  /**
   * Removes task by index and saves tto file.
   *
   * @param index index position of task to remove
   */
  public void removeTask(int index) {
    this.tasks.remove(index);
    this.saveTasks();
  }

  /**
   * Updates {@code Task} at {@code index} to given {@code status}.
   *
   * @param index  index position of task to update
   * @param status status to update to
   */
  public void setTaskStatus(int index, boolean status) {
    this.tasks.get(index).setDone(status);
    this.saveTasks();
  }

  /**
   * Get size of current {@code TaskList}.
   *
   * @return size of {@code TaskList}
   */
  public int size() {
    return this.tasks.size();
  }

  /**
   * Get {@code Task} given {@code index}.
   *
   * @param index index position of task to update
   * @return {@code Task} corresponding to the {@code index}
   */
  public Task getTask(int index) {
    return this.tasks.get(index);
  }

  @Override
  public String toString() {
    if (this.tasks.size() == 0) {
      return "You do not have any cyrus.tasks, use todo, deadline, or event to add new ones!";
    }
    List<String> formatted = IntStream
        .range(0, tasks.size())
        .mapToObj((j) -> String.format("%d. %s", j + 1, this.tasks.get(j)))
        .collect(Collectors.toList());
    formatted.add(0, "Here are the cyrus.tasks in your list:");
    return String.join("\n", formatted);
  }

  /**
   * Helper function to save the current list of tasks to the storage.
   */
  private void saveTasks() {
    this.storage.save(this.tasks);
  }
}
