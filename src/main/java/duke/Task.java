package duke;

/**
 * Represents a task that only contains a start date.
 */
public abstract class Task {
  private String taskName;

  private boolean isCompleted;

  /**
   * Creates a new Task with the given task name.
   *
   * @param taskName the name of the task
   */
  public Task(String taskName) {
    this.taskName = taskName;
  }

  /**
   * Creates a new Task with the given task name.
   *
   * @param taskName the name of the task
   * @param isCompleted the completion status of the task
   */
  public Task(String taskName, boolean isCompleted) {
    this.taskName = taskName;
    this.isCompleted = isCompleted;
  }

  public abstract String saveData();

  /**
   * Marks the task as completed
   */
  public void markCompleted() {
    this.isCompleted = true;
  }

  /**
   * Marks the task as not completed
   */
  public void markNotCompleted() {
    this.isCompleted = false;
  }

  /**
   * Returns the current completion status
   *
   * @return the current completion status
   */
  protected boolean isCompleted() {
    return this.isCompleted;
  }

  protected String taskName() {
    return this.taskName;
  }

  /**
   * Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
  public String toString() {
    if (isCompleted) {
      return "[X] " + taskName;
    } else {
      return "[ ] " + taskName;
    }
  }
}
