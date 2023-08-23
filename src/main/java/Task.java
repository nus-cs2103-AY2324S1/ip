public class Task {
  protected boolean isDone;
  protected String description;

  /**
   * Constructor for task.
   *
   * @param description Description of task.
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }


  /**
   * Gets text status icon of task's completion status.
   *
   * @return space if not done; X if done.
   */
  public String getStatusIcon() {
    return (this.isDone ? "X" : " ");
  }

  /**
   * Gets task formatted with status icon
   *
   * @return Task formatted as a string. e.g. [X] completed task
   */
  public String getTaskString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.description);
  }

  /**
   * Checks if task is done.
   *
   * @return true if task is completed; false otherwise
   */
  public boolean isDone() {
    return this.isDone;
  }

  /**
   * Marks task as done.
   */
  public void markAsDone() {
    this.isDone = true;
  }

  /**
   * Marks task as not done.
   */
  public void markAsNotDone() {
    this.isDone = false;
  }

}
