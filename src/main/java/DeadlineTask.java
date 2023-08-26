public class DeadlineTask extends Task {

  private String endDate;

  public DeadlineTask(String taskName, String endDate) {
    super(taskName);
    this.endDate = endDate;
  }

  public DeadlineTask(String taskName, String endDate, boolean isCompleted) {
    super(taskName, isCompleted);
    this.endDate = endDate;
  }

  @Override
  public String saveData() {
    char delimiter = 31;
    String isCompleted = isCompleted() ? "1" : "0";
    return "deadline" + delimiter + isCompleted + delimiter + taskName() + delimiter + endDate;
  }

  public String toString() {
    return "[D]" + super.toString() + " (by:" + endDate + ")";
  }
}
