package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

  protected LocalDate byDate;
  protected LocalTime byTime;

  public Deadline(String description, boolean doneness, LocalDate byDate, LocalTime byTime) {
    super(description, doneness);
    this.byDate = byDate;
    this.byTime = byTime;
  }

  /**
  * Returns a String representation of this Deadline. The format is "[D][StatusIcon] Description (by: Date/Time)"
  * 
  * @return a String representation of this Deadline.
  */
  @Override
  public String toString() {
    return String.format("[D][%s] %s (by: %s %s)", this.getStatusIcon(), this.description,
        (this.byDate != null ? this.byDate : ""), (this.byTime != null ? this.byTime : ""));
  }

  /**
  * Returns a string representation of this Deadline. The format is D # Doneness # Description # Date/Time.
  * Note that this is different from toString() as it is used for encoding data in the file.
  * 
  * 
  * @return a string representation of this Deadline for storage in the file.
  */
  @Override
  public String toFileString() {
    return String.format("D # %d # %s # %s %s", (doneness ? 1 : 0), this.description,
        (this.byDate != null ? this.byDate : ""), (this.byTime != null ? this.byTime : ""));
  }
}