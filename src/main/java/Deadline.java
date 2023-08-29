import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Deadline")
public class Deadline extends Task {

  public static final String taskType = "D";

  private String deadDate;

  Deadline(String desc, String deaddate) {
    super(desc);
    this.deadDate = deaddate;
  }

  Deadline() {
    super("");
  }

  public String getDeadDate() {
    return this.deadDate;
  }

}
