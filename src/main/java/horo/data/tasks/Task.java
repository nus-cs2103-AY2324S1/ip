package horo.data.tasks;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import horo.HoroException;
import horo.data.Data;

public abstract class Task extends Data {

  protected static final DateTimeFormatter DATE_TIME_FORMAT = new DateTimeFormatterBuilder()
      .appendPattern("yyyy/MM/dd[ [HH][:mm]]")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .toFormatter();

  private String description = "";
  private boolean isDone = false;

  public Task(String description) throws HoroException {
    if (description == null || description.isBlank()) {
      throw new HoroException("Task description cannot be empty");
    }

    this.description = description;
  }

  public void markDone() {
    this.isDone = true;
  }

  public void markNotDone() {
    this.isDone = false;
  }

  public Boolean isDone() {
    return isDone;
  }

  public String getDescription() {
    return this.description;
  }

  @Override
  public String toString() {
    return (isDone ? "[X] " : "[ ] ") + getDescription();
  }
}
