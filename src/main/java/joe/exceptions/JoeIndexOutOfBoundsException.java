package joe.exceptions;

/**
 * A custom exception class for handling index out-of-bounds errors in the Joe application's task
 * list.
 */
public class JoeIndexOutOfBoundsException extends JoeException {
  /**
   * Constructs a JoeIndexOutOfBoundsException with an error message indicating that the specified
   * task index does not exist.
   *
   * @param idx The index of the task that is out of bounds.
   */
  public JoeIndexOutOfBoundsException(int idx) {
    super("Task " + idx + " does not exist");
  }
}
