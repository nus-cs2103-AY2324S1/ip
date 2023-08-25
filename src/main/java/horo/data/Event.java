package horo.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import horo.HoroException;

public class Event extends Task {
  private LocalDateTime start;
  private LocalDateTime end;

  private static DateTimeFormatter DATE_TIME_FORMAT = new DateTimeFormatterBuilder()
      .appendPattern("yyyy/MM/dd[ [HH][:mm]]")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .toFormatter();

  public Event(String description, LocalDateTime start, LocalDateTime end) throws HoroException {
    super(description);
    this.start = start;
    this.end = end;
  }

  public Event(String description, String start, String end) throws HoroException {
    super(description);
    try {
      this.start = LocalDateTime.parse(start, DATE_TIME_FORMAT);
      this.end = LocalDateTime.parse(end, DATE_TIME_FORMAT);
    } catch (DateTimeParseException e) {
      throw new HoroException("Wrong Date Time Format");
    }
  }

  @Override
  public String toString() {
    return "[E]"
        + super.toString()
        + " (from: "
        + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy H:mm"))
        + " to: "
        + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy H:mm"))
        + ")";
  }

  @Override
  public String getDataString() {
    return "D,"
        + (super.isDone() ? "1" : "0") + ","
        + super.getDescription() + ","
        + start.format(DATE_TIME_FORMAT)
        + ","
        + end.format(DATE_TIME_FORMAT);
  }
}
