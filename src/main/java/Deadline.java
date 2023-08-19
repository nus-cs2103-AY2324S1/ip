public class Deadline extends Task {

  private String deadline;

  public Deadline(String taskString, String deadline) throws HoroException {
    super(taskString);
    if (deadline == null || deadline.isBlank()) {
      throw new HoroException("Deadline cannot be empty");
    }
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.deadline + ")";
  }
}
