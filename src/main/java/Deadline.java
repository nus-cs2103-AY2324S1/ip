public class Deadline extends Task {
  private final String by;

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.by + ")";
  }

  Deadline(String taskName, String by) {
    super(taskName);
    this.by = by;
  }
}
