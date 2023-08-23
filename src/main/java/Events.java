public class Events extends ListItem {

  String start;
  String end;

  /**
   * Constructor for Events.
   */
  public Events(String text, String start, String end) {
    super(text);
    this.start = start;
    this.end = end;
  }

  @Override
  /**
   * Returns string representation of Events.
   */
  public String toString() {
    return (
      "[E] " +
      super.toString() +
      String.format(" (from: %s to: %s)", this.start, this.end)
    );
  }
}
