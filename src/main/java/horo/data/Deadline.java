package horo.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import horo.HoroException;

public class Deadline extends Task {

  private LocalDateTime deadline;
  private static DateTimeFormatter DATE_TIME_FORMAT = new DateTimeFormatterBuilder()
      .appendPattern("yyyy/MM/dd[ [HH][:mm]]")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .toFormatter();

  public Deadline(String description, LocalDateTime deadline) throws HoroException {
    super(description);
    this.deadline = deadline;
  }

  public Deadline(String description, String deadline) throws HoroException {
    super(description);
    try {
      this.deadline = LocalDateTime.parse(deadline, DATE_TIME_FORMAT);
    } catch (DateTimeParseException e) {
      e.printStackTrace();
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
