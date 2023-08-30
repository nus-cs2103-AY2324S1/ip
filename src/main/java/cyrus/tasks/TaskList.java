package cyrus.tasks;

import cyrus.storage.IStorage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class TaskList {
  private final List<Task> tasks;
  private final IStorage storage;

  public TaskList(IStorage storage) {
    this.storage = storage;
    this.tasks = storage.load();
  }

  public void addTask(Task task) {
    this.tasks.add(task);
    this.saveTasks();
  }

  public void removeTask(int index) {
    this.tasks.remove(index);
    this.saveTasks();
  }

  public void setTaskStatus(int index, boolean status) {
    this.tasks.get(index).setDone(status);
    this.saveTasks();
  }

  public int size() {
    return this.tasks.size();
  }

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

  private void saveTasks() {
    this.storage.save(this.tasks);
  }
}
