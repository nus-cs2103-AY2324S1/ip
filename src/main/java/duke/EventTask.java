package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

  private LocalDate startDate;
  private LocalDate  endDate;
  private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

  public EventTask(String taskName, LocalDate startDate, LocalDate endDate) {
    super(taskName);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public EventTask(String taskName, LocalDate startDate, LocalDate endDate, boolean isCompleted) {
    super(taskName, isCompleted);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public String saveData() {
    char delimiter = 31;
    String isCompleted = isCompleted() ? "1" : "0";
    return "event" + delimiter + isCompleted + delimiter + taskName() + delimiter
            + this.startDate.format(this.saveFormatter) + delimiter + this.endDate.format(this.saveFormatter);
  }

  public String toString() {
    return "[E]" + super.toString() + " (from: " + startDate.format(this.displayFormatter) + " to: "
            + endDate.format(this.displayFormatter) + ")";
  }
}
