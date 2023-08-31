package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task that has a start and end date.
 */
public class EventTask extends Task {

  private LocalDate startDate;
  private LocalDate  endDate;
  private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

  /**
   * Creates a new Event Task.
   *
   * @param taskName the name of the task
   * @param startDate the start date of the task
   * @param endDate the end date of the task
   */
  public EventTask(String taskName, LocalDate startDate, LocalDate endDate) {
    super(taskName);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * Creates a new Event Task.
   *
   * @param taskName the name of the task
   * @param startDate the start date of the task
   * @param endDate the end date of the task
   * @param isCompleted the completion status of the task
   */
  public EventTask(String taskName, LocalDate startDate, LocalDate endDate, boolean isCompleted) {
    super(taskName, isCompleted);
    this.startDate = startDate;
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
    return "event" + delimiter + isCompleted + delimiter + taskName() + delimiter
            + this.startDate.format(this.saveFormatter) + delimiter + this.endDate.format(this.saveFormatter);
  }

  /**
   *  Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
  public String toString() {
    return "[E]" + super.toString() + " (from: " + startDate.format(this.displayFormatter) + " to: "
            + endDate.format(this.displayFormatter) + ")";
  }
}
