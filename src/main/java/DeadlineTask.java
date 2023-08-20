public class DeadlineTask extends Task {
  protected String deadline;

  public DeadlineTask(String description, String deadline) {
    super(description);
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
  }
}
