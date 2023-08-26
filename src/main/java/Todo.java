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
  protected String toDatabaseRepresentation() {
    return String.format("T | %s | %s", isDone() ? 1 : 0, getTaskName());
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
