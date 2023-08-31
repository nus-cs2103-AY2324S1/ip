package TaskPackages;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task{

  protected LocalDate fromDate;
  protected LocalTime fromTime;
  protected LocalDate toDate;
  protected LocalTime toTime;

  protected Event(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
    super(description);
    this.fromDate = fromDate;
    this.fromTime = fromTime;
    this.toDate = toDate;
    this.toTime = toTime;
  }
  
  @Override
  public String toString() {
    return String.format("[E][%s] %s (from: %s %s to: %s %s)", this.getStatusIcon(), this.description, this.fromDate, this.fromTime, this.toDate, this.toTime);
  }

  @Override
  public String toFileString() {
    return String.format("E # %d # %s # /from %s # /to %s", (isDone ? 1 : 0), this.description, this.from, this.to);
  }
}
