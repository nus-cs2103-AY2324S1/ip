public class DeadlineTask extends Task {
  /**
   * Deadline
   */
  protected String deadline;

  /**
   * Constructor for Deadline task.
   *
   * @param description Description of task.
   */
  public DeadlineTask(String description, String deadline) {
    super(description);
    this.deadline = deadline;
  }

  /**
   * Gets Deadline task formatted with type and status icon
   *
   * @return Task formatted as a string.
   */
  @Override
  public String toString() {
    return String.format("<D>%s (by: %s)", super.toString(), this.deadline);
  }
}
