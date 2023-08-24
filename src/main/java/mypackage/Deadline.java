package mypackage;

public class Deadline extends Task {
  private final String by;

  public Deadline(String description) {
    super(description.substring(0, description.indexOf("/") - 1));
    this.by = description.substring(description.indexOf("/") + 4);
  }
  
  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), by);
  }
}
