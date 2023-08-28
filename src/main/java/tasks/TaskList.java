package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
  private static TaskList INSTANCE;
  private final List<Task> tasks = new ArrayList<>();

  public static TaskList getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new TaskList();
    }

    return INSTANCE;
  }

  public void addTask(Task task) {
    this.tasks.add(task);
  }

  public void removeTask(int index) {
    this.checkIndex(index);
    this.tasks.remove(index);
  }

  public int size() {
    return this.tasks.size();
  }

  public void setTaskStatus(int index, boolean status) {
    this.checkIndex(index);
    this.tasks.get(index).setDone(status);
  }

  public Task getTask(int index) {
    this.checkIndex(index);
    return this.tasks.get(index);
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= this.tasks.size()) {
      throw new IllegalArgumentException("Invalid index provided");
    }
  }
}
