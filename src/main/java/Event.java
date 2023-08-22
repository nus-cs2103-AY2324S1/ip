public class Event extends Task {
  private String start;
  private String end;

  public Event(String taskString, String start, String end) throws HoroException {
    super(taskString);
    if (start == null || start.isBlank()) {
      throw new HoroException("Start time cannot be empty");
    }
    if (end == null || end.isBlank()) {
      throw new HoroException("End time cannot be empty");
    }
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
  }
}
