import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class Event extends Task {

  protected String from;
  protected String to;

  public Event(String description, String from, String to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
  }

  @Override
  public String toCommand(int idx) {
    return (new EventCommand(Map.ofEntries(new SimpleEntry<>("description", this.description), new SimpleEntry<>("from", this.from), new SimpleEntry<>("to", this.to)))).toString()
        + "\n" + super.toCommand(idx) + "\n";
  }
  
}
