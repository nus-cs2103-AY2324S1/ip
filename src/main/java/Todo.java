/**
 * This class represents a basic task that can be marked.
 */
public class Todo extends Task {
  public Todo(String taskName) throws DukeException {
    super(taskName);
    if (taskName.isEmpty()) {
      throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
    }
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
