public class Deadlines extends ListItem {

  String end;

  /**
   * Constructor for Deadlines.
   */
  public Deadlines(String text, String end) {
    super(text);
    this.end = end;
  }

  @Override
  /**
   * Returns string representation of Deadlines.
   */
  public String toString() {
    return "[D] " + super.toString() + String.format(" (by: %s)", this.end);
  }
}
