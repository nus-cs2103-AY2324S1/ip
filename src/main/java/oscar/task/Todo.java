package oscar.task;

/**
 * Todo task that contains description of task.
 */
public class Todo extends Task {
  /**
   * Public constructor of todo.
   *
   * @param description Details of todo.
   */
  public Todo(String description) {
    super(description, "T");
  }
}
