package mypackage;

public class Event extends Task {
  private final String to;
  private final String from;

  public Event(String m) {
    super(m.substring(0, m.indexOf("/") - 1));
    int indexOfFirstSlash = m.indexOf("/");
    int indexOfSecondSlash = m.indexOf("/", m.indexOf("/") + 1);
    this.from = m.substring(indexOfFirstSlash + 6, indexOfSecondSlash - 1);
    this.to = m.substring(indexOfSecondSlash + 4);
  }

  public Event (String m, String from, String to) {
    super(m);
    this.from = from;
    this.to = to;
  }

  
  @Override
  public String toString() {
    return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
  }
}
