public class EventTask extends Task {

  private String startDate;
  private String endDate;

  public EventTask(String taskName, String startDate, String endDate) {
    super(taskName);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String toString() {
    return "[E]" + super.toString() + " (from:" + startDate + " to:" + endDate + ")";
  }
}
