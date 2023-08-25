public class Deadline extends Task {

  private String deadline;

  public Deadline(String description, String deadline) throws HoroException {
    super(description);
    if (deadline == null || deadline.isBlank()) {
      throw new HoroException("Deadline cannot be empty");
    }
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.deadline + ")";
  }

  @Override
  public String getDataString() {
    return "D," + (super.isDone() ? "1" : "0") + "," + super.getDescription() + "," + deadline;
  }
}
