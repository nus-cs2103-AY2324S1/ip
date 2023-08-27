public class Event extends Task {
  private String from;
  private String to;

  public Event(String descriptor, String from, String to) {
    super(descriptor);
    this.symbol = "E";
    this.from = from;
    this.to = to;
  }

  @Override
  public String save() {
    return String.format("%s|%s|%s|%s", this.symbol, super.save(), this.from,
        this.to);
  }

  @Override
  public String toString() {
    return String.format("[%s]%s (%s to %s)", this.symbol, super.toString(),
        this.from, this.to);
  }
}
