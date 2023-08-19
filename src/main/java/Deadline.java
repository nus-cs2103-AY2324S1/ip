public class Deadline extends Task {

  private String deadline;

  public Deadline(String taskString, String deadline) {
    super(taskString);
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.deadline + ")";
  }
}
