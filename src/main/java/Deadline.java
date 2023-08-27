public class Deadline extends Task {
  private String by;

  public Deadline(String description, String by) {
    super(description);
    this.symbol = "D";
    this.by = by;
  }

  @Override
  public String save() {
    return String.format("%s|%s|%s", this.symbol, super.save(), this.by);
  }

  @Override
  public String toString() {
    return String.format("[%s]%s (by %s)", this.symbol, super.toString(),
        this.by);
  }
}
