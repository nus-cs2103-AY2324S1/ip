package tasks;

import com.google.gson.annotations.SerializedName;
import utility.DateUtility;

import java.time.LocalDate;

public class Deadline extends Task {
  @SerializedName("type")
  private final static String TYPE = "deadline";
  @SerializedName("due")
  private final LocalDate deadline;

  public Deadline(String name, LocalDate deadline) {
    super(name);
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return String.format(
        "[D] %s (by: %s)",
        super.toString(),
        DateUtility.formatLocalDate(this.deadline)
    );
  }
}
