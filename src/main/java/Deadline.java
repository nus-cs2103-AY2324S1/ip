import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;

@JsonTypeName("Deadline")
public class Deadline extends Task {

  public static final String taskType = "D";

  private LocalDate deadDate;

  Deadline(String desc, LocalDate deaddate) {
    super(desc);
    this.deadDate = deaddate;
  }

  Deadline() {
    super("");
  }

  public String getDeadDate() {
    return this.deadDate.toString();
  }

}
