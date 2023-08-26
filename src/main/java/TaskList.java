import java.util.ArrayList;
import java.util.List;

public class TaskList {
  private final List<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public Task getTask(int i) throws DukeException { // 1-indexed
    try {
      return tasks.get(i - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException(String.format(DukeException.OUT_OF_BOUNDS_TASK_LIST, i));
    }
  }

  public void addTask(Task t) {
    tasks.add(t);
  }

  public String getNumberOfTasks() {
    return String.format(
        "Now you have %d task%s in the list.", tasks.size(), tasks.size() == 1 ? "" : "s");
  }

  public void deleteTask(int i) throws DukeException {
    try {
      tasks.remove(i - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException(String.format(DukeException.OUT_OF_BOUNDS_TASK_LIST, i));
    }
  }

	public int size() {
		return tasks.size();
	}
}
