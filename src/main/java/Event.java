public class Event extends Task{
  private final String start;
  private final String end;

  public Event(String description, String start, String end) {
    super(description);
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "[E][" + getStatusIcon() + "] " + this.description + " (from: " + this.start + " to: " + this.end + ")";
  }
}
