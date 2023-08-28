package tasks;

import com.google.gson.annotations.SerializedName;
import utility.DateUtility;

import java.time.LocalDate;

public class Event extends Task {
  @SerializedName("type")
  private final static String TYPE = "event";
  private final LocalDate from;
  private final LocalDate to;

  public Event(String name, LocalDate from, LocalDate to) {
    super(name);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return String.format(
        "[E] %s (from: %s to: %s)",
        super.toString(),
        DateUtility.formatLocalDate(this.from),
        DateUtility.formatLocalDate(this.to)
    );
  }
}

