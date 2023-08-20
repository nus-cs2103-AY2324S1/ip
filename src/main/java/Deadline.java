public class Deadline extends Task {
  private String by;

  public Deadline(String name, String by) {
    super(name, 'D');
    this.by = by;
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), by);
  }
}
