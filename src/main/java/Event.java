public class Event extends Task {

  private final String start;

  private final String end;

  public Event(String name, String start, String end) {
    super(name);
    this.setTag("[E]");
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return super.toString() + "(from: " + start + " to: " + end + ")";
  }
}
