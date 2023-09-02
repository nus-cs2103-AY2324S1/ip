package joe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import joe.tasks.Task;

public class TaskList {
  private ArrayList<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<Task>();
  }

  public void add(Task task) {
    tasks.add(task);
  }

  public Task get(int idx) {
    return tasks.get(idx);
  }

  public void remove(int idx) {
    tasks.remove(idx);
  }

  public int size() {
    return tasks.size();
  }

  public List<String> getStringList() {
    return tasks.stream().map(Task::toString).collect(Collectors.toList());
  }
  /**
   * Returns a TaskList of tasks containing the search word.
   *
   * @param searchString The search word.
   * @return A TaskList of tasks.
   */
  public TaskList find(String searchString) {
    TaskList res = new TaskList();
    for (Task task : tasks) {
      if (task.getDescription().toLowerCase().contains(searchString)) {
        res.add(task);
      }
    }
    return res;
  }
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
