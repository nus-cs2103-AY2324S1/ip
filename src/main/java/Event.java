public class Event extends Task {
  private String from;
  private String to;

  public Event(String descriptor, String from, String to) {
    super(descriptor);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return String.format("[%s]%s (%s to %s)", "E", super.toString(), this.from,
        this.to);
  }
}
