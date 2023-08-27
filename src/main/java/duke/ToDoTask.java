package duke;

/**
 * Represents a task that only has a task name.
 */
public class ToDoTask  extends Task {
  public ToDoTask(String taskName) {
    super(taskName);
  }

  public ToDoTask(String taskName, boolean isCompleted) {
    super(taskName, isCompleted);
  }


  /**
   * Returns the current state of the task in String for saving purposes.
   *
   * @return the current state of the task in String for saving purposes
   */
  @Override
  public String saveData() {
    char delimiter = 31;
    String isCompleted = isCompleted() ? "1" : "0";
    return "todo" + delimiter + isCompleted + delimiter + taskName() + delimiter;
  }


  /**
   * Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
  public String toString() {
    return "[T]" + super.toString();
  }
}
