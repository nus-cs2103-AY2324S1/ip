package oscar.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that contains description of task and deadline.
 */
public class Deadline extends Task{
  private final LocalDateTime deadline;

  /**
   * Public constructor for deadline.
   * @param description Details of deadline.
   * @param deadline Deadline of task.
   */
  public Deadline(String description, LocalDateTime deadline) {
    super(description, "D");
    this.deadline = deadline;
  }

  /**
   * Formats string representation of deadline.
   *
   * @return Information of deadline.
   */
  @Override
  public String toString() {
    return super.toString() + " (by: "
            + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
  }
}
