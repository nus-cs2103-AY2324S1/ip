import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class Deadline extends Task {

  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.by);
  }

  @Override
  public String toCommand(int idx) {
    return (new DeadlineCommand(Map.ofEntries(new SimpleEntry<>("description", this.description), new SimpleEntry<>("by", this.by)))).toString()
        + "\n" + super.toCommand(idx) + "\n";
  }
  
}
