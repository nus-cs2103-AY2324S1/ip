package tasks;

public class Deadline extends Task {
  private final String deadline;

  public Deadline(String name, String deadline) {
    super(name);
    this.deadline = deadline;
  }

  public String getDeadline() {
    return this.deadline;
  }

  @Override
  public String toString() {
    return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
  }
}
