public class Deadline extends Task{
  private final String deadline;

  public Deadline(String description, String deadline) {
    super(description);
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return "[D][" + getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
  }
}
