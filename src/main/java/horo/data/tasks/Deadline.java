package horo.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import horo.HoroException;

public class Deadline extends Task {

  private LocalDateTime deadline;

  public Deadline(String description, LocalDateTime deadline) throws HoroException {
    super(description);
    this.deadline = deadline;
  }

  public Deadline(String description, String deadline) throws HoroException {
    super(description);
    try {
      this.deadline = LocalDateTime.parse(deadline, DATE_TIME_FORMAT);
    } catch (DateTimeParseException e) {
      throw new HoroException("Wrong Date Time Format");
    }
  }

  @Override
  public String toString() {
    return "[D]"
        + super.toString()
        + " (by: "
        + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy H:mm"))
        + ")";
  }

  @Override
  public String getDataString() {
    return "D,"
        + (super.isDone() ? "1" : "0") + ","
        + super.getDescription() + ","
        + this.deadline.format(DATE_TIME_FORMAT);
  }
}
