public class ToDoTask extends Task {
  /**
   * Constructor for ToDo task.
   *
   * @param description Description of task.
   */
  public ToDoTask(String description) {
    super(description);
  }

  /**
   * Gets Todo task formatted with type and status icon
   *
   * @return Task formatted as a string.
   */
  @Override
  public String toString() {
    return "<T>" + super.toString();
  }
}
