import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
  private LocalDateTime deadline;

  public DeadlineTask(String description, String deadline) throws DukeException {
    super(description);
    try {
      this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    } catch (DateTimeParseException e) {
      throw new DukeException("Failed to parse datetime.");
    }
  }

  @Override
  public String toString() {
    String deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    return String.format("[D]%s (by: %s)", super.toString(), deadlineString);
  }
}
