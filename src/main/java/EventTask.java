public class EventTask extends Task {

  private String startDate;
  private String endDate;

  public EventTask(String taskName, String startDate, String endDate) {
    super(taskName);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public EventTask(String taskName, String startDate, String endDate, boolean isCompleted) {
    super(taskName, isCompleted);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public String saveData() {
    char delimiter = 31;
    String isCompleted = isCompleted() ? "1" : "0";
    return "event" + delimiter + isCompleted + delimiter + taskName() + delimiter + startDate + delimiter + endDate;
  }

  public String toString() {
    return "[E]" + super.toString() + " (from:" + startDate + " to:" + endDate + ")";
  }
}
