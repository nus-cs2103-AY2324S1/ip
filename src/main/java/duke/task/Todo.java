package duke.task;

/** 
 * Represents a Todo task 
 */
public class Todo extends Task {
  /**
   * Returns a Todo
   *
   * @param description the description of task
   * @param mark if the task is marked
   * @return the created Todo
   */
  public Todo(String description, boolean mark) {
    super(description, mark, 'T');
  }
}
