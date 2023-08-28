package tasks;

public class Event extends Task {
  private final String from;
  private final String to;

  public Event(String name, String from, String to) {
    super(name);
    this.from = from;
    this.to = to;
  }

  public String getFrom() {
    return this.from;
  }

  public String getTo() {
    return this.to;
  }

  @Override
  public String toString() {
    return String.format("[E] %s (from: %s to: %s)", super.toString(), this.from, this.to);
  }
}

