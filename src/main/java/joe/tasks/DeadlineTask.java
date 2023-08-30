package joe.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
  private final LocalDateTime deadline;

  public DeadlineTask(String description, LocalDateTime deadline) {
    super(description);
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    String deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    return String.format("[D]%s (by: %s)", super.toString(), deadlineString);
  }
}
