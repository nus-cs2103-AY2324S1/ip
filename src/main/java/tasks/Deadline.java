package tasks;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import tasks.Task;

/**
 * The deadline class represents a deadline. It has a name and a deadline
 */
@JsonTypeName("tasks.Deadline")
public class Deadline extends Task {

  public static final String taskType = "D";

  private LocalDate deadDate;

  public Deadline(String desc, LocalDate deaddate) {
    super(desc);
    this.deadDate = deaddate;
  }

  public Deadline() {
    super("");
  }

  public String getDeadDate() {
    return this.deadDate.toString();
  }

}
