package joe.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
  private final LocalDateTime from;
  private final LocalDateTime to;

  public EventTask(String description, LocalDateTime from, LocalDateTime to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    String fromString = this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    String toString = this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    return String.format("[E]%s (from: %s to: %s)", super.toString(), fromString, toString);
  }
}
