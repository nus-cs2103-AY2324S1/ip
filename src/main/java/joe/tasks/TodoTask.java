package joe.tasks;

/** Represents a to-do task with a description and completion status. */
public class TodoTask extends Task {
  /**
   * Constructs a TodoTask object with the given description.
   *
   * @param description The description of the to-do task.
   */
  public TodoTask(String description) {
    super(description);
  }

  /**
   * Converts the TodoTask object to a formatted string.
   *
   * @return A string representation of the to-do task, including its completion status and
   *     description.
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
