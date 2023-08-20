/**
 * Event task that contains description of event,
 * as well as start and end date/time.
 */
public class Event extends Task{
  private final String start;
  private final String end;

  /**
   * Public constructor for event.
   * @param description Details of event.
   * @param start Start date/time of event.
   * @param end End date/time of event.
   */
  public Event(String description, String start, String end) {
    super(description, "E");
    this.start = start;
    this.end = end;
  }

  /**
   * Detailed string representation of event.
   * @return Information of event.
   */
  @Override
  public String toString() {
    return super.toString() +
            " (from: " + this.start + " to: " + this.end + ")";
  }
}
