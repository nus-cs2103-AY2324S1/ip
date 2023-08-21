import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

  private final String[] tasks;
  private int totalTasks;

  public TaskList() {
    tasks = new String[100];
  }

  public void addTask(String task) {
    tasks[totalTasks++] = task;
  }

  public List<String> getTasks() {
    return new ArrayList<>(Arrays.asList(tasks).subList(0, totalTasks));
  }
}
