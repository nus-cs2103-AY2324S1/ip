package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

  protected LocalDate fromDate;
  protected LocalTime fromTime;
  protected LocalDate toDate;
  protected LocalTime toTime;

  public Event(String description, boolean doneness, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
    super(description, doneness);
    this.fromDate = fromDate;
    this.fromTime = fromTime;
    this.toDate = toDate;
    this.toTime = toTime;
  }

  @Override
  public String toString() {
    return String.format("[E][%s] %s (from: %s %s to: %s %s)", this.getStatusIcon(), this.description,
        (this.fromDate != null ? this.fromDate : ""), (this.fromTime != null ? this.fromTime : ""),
        (this.toDate != null ? this.toDate : ""), (this.toTime != null ? this.toTime : ""));
  }

  @Override
  public String toFileString() {
    return String.format("E # %d # %s # %s %s # %s %s", (doneness ? 1 : 0), this.description,
        (this.fromDate != null ? this.fromDate : ""), (this.fromTime != null ? this.fromTime : ""),
        (this.toDate != null ? this.toDate : ""), (this.toTime != null ? this.toTime : ""));
  }
}
