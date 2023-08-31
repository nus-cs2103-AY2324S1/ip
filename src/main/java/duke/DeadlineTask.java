package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * A subclass of Task that has an end date.
 */
public class DeadlineTask extends Task {

  private LocalDate endDate;
  private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

  /**
   * Creates a new Deadline task.
   *
   * @param taskName the name of the task
   * @param endDate the end date of the task
   */
  public DeadlineTask(String taskName, LocalDate endDate) {
    super(taskName);
    this.endDate = endDate;
  }

  /**
   * Creates a new Deadline task.
   *
   * @param taskName the name of the task
   * @param endDate the end date of the task
   * @param isCompleted the status of completion
   */
  public DeadlineTask(String taskName, LocalDate endDate, boolean isCompleted) {
    super(taskName, isCompleted);
    this.endDate = endDate;
  }

  /**
   * Returns the current state of the task in String for saving purposes.
   *
   * @return the current state of the task in String for saving purposes
   */
  @Override
  public String saveData() {
    char delimiter = 31;
    String isCompleted = isCompleted() ? "1" : "0";
    return "deadline" + delimiter + isCompleted + delimiter + taskName()
            + delimiter + this.endDate.format(this.saveFormatter);
  }

  /**
   * Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
  public String toString() {
    return "[D]" + super.toString() + " (by: " + endDate.format(displayFormatter) + ")";
  }
}
