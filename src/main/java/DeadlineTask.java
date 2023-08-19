public class DeadlineTask extends Task {

  private String endDate;

  public DeadlineTask(String taskName, String endDate) {
    super(taskName);
    this.endDate = endDate;
  }

  public String toString() {
    return "[D]" + super.toString() + " (by:" + endDate + ")";
  }
}
