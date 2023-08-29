
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Event")
public class Event extends Task {

  public static final String taskType = "E";

  private String startDate;
  private String endDate;

  Event(String desc, String startDate, String endDate) {
    super(desc);
    this.startDate = startDate;
    this.endDate = endDate;

  }

  Event() {
    super("");
  }

  public String getStartDate() {
    return this.startDate;
  }

  public String getEndDate() {
    return this.endDate;
  }

}
