import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class Deadline extends Task implements Comparable<Deadline> {

  protected LocalDate by;

  public Deadline(String description, LocalDate by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), Ui.stringifyDate(this.by));
  }

  @Override
  public String toCommand(int idx) {
    return (new DeadlineCommand(Map.ofEntries(new SimpleEntry<>("description", this.description), new SimpleEntry<>("by", this.by)))).toString()
        + "\n" + super.toCommand(idx) + "\n";
  }

  @Override
  public int compareTo(Deadline other) {
    return (int) ChronoUnit.DAYS.between(other.by, this.by);
  }
  
}
