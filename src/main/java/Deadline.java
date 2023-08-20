/**
 * Deadline task that contains description of task and deadline.
 */
public class Deadline extends Task{
  private final String deadline;

  /**
   * Public constructor for deadline.
   * @param description Details of deadline.
   * @param deadline Deadline of task.
   */
  public Deadline(String description, String deadline) {
    super(description, "D");
    this.deadline = deadline;
  }

  /**
   * Detailed string representation of deadline.
   * @return Information of deadline.
   */
  @Override
  public String toString() {
    return  super.toString() + " (by: " + this.deadline + ")";
  }
}
