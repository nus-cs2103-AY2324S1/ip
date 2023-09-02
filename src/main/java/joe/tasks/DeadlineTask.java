package joe.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents a task with a specific deadline. */
public class DeadlineTask extends Task {
  private final LocalDateTime deadline;

  /**
   * Constructs a DeadlineTask with the given description and deadline.
   *
   * @param description The description of the task.
   * @param deadline The deadline for the task.
   */
  public DeadlineTask(String description, LocalDateTime deadline) {
    super(description);
    this.deadline = deadline;
  }

  /**
   * Converts the DeadlineTask object to a formatted string.
   *
   * @return A string representation of the DeadlineTask, including its deadline.
   */
  @Override
  public String toString() {
    String deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    return String.format("[D]%s (by: %s)", super.toString(), deadlineString);
  }
}
