package TaskPackages;

public class Event extends Task{

  protected String from;
  protected String to;

  protected Event(String description, String from, String to) {
    super(description);
    this.from = from;
    this.to = to;
  }
  
  @Override
  public String toString() {
    return String.format("[E][%s] %s (from: %s to: %s)", this.getStatusIcon(), this.description, this.from, this.to);
  }

  @Override
  public String toFileString() {
    return String.format("  # %d # %s # %s # %s", (isDone ? 1 : 0), this.description, this.from, this.to);
  }
}
