import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
  private LocalDateTime from;
  private LocalDateTime to;

  public EventTask(String description, String from, String to) throws JoeException {
    super(description);
    try {
      this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
      this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    } catch (DateTimeParseException e) {
      throw new JoeException("Failed to parse datetime.");
    }
  }

  @Override
  public String toString() {
    String fromString = this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    String toString = this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    return String.format("[E]%s (from: %s to: %s)", super.toString(), fromString, toString);
  }
}
