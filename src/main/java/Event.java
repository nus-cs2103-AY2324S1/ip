import java.util.regex.*;

public class Event extends Task {
  private final String from, to;

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
  }

  Event(String taskName, String from, String to) {
    super(taskName);
    this.from = from;
    this.to = to;
  }
}
