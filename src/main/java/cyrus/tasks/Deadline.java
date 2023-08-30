package cyrus.tasks;

import com.google.gson.annotations.SerializedName;
import cyrus.utility.DateUtility;

import java.time.LocalDate;

/**
 * Deadline task that contains the name of the deadline and the due date (i.e. {@code deadline}.
 */
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
