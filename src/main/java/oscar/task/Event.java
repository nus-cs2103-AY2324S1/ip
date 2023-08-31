package oscar.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task that contains description of event,
 * as well as start and end date/time.
 */
public class Event extends Task{
  private final LocalDateTime start;
  private final LocalDateTime end;

  /**
   * Public constructor for event.
   * @param description Details of event.
   * @param start Start date/time of event.
   * @param end End date/time of event.
   */
  public Event(String description, LocalDateTime start, LocalDateTime end) {
    super(description, "E");
    this.start = start;
    this.end = end;
  }

  /**
   * Formats string representation of event.
   *
   * @return Information of event.
   */
  @Override
  public String toString() {
    return super.toString() +
            " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) +
            " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
  }
}
