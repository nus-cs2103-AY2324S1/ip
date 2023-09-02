package tasks;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import tasks.Task;

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
