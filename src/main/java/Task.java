import java.util.ArrayList;
import java.util.List;

public abstract class Task {
  private boolean mark = false;
  private final String description;
  private final char type;

  static final List<Task> tasks = new ArrayList<>();

  public Task(String description, char type) throws DukeException {
    this.description = description;
    this.type = type;

    if (description.isEmpty())
      throw new DukeException(
          String.format(
              "The description of a %s cannot be empty.",
              this.getClass().getSimpleName().toLowerCase()));
  }

  public void mark() {
    mark = true;
  }

  public void unmark() {
    mark = false;
  }

  @Override
  public String toString() {
    return String.format("  [%c][%c] %s", type, mark ? 'X' : ' ', description);
  }

  static Task getTask(int i) { // 1-indexed
    try {
      return tasks.get(i - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException(String.format(DukeException.OUT_OF_BOUNDS_TASK_LIST, i));
    }
  }

  static void addTask(Task t) {
    tasks.add(t);
  }

  static String getNumberOfTasks() {
    return String.format(
        "Now you have %d task%s in the list.", tasks.size(), tasks.size() == 1 ? "" : "s");
  }

  static void deleteTask(int i) { // 1-index
    tasks.remove(i - 1);
  }
}
