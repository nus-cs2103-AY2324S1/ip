public class Todos extends ListItem {

  /**
   * Constructor for Todos.
   */
  public Todos(String text) {
    super(text);
  }

  @Override
  /**
   * Returns string representation of Todos.
   */
  public String toString() {
    return "[T] " + super.toString();
  }
}
