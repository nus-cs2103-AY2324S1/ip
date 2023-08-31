package duke;

/**
 * Occurs when index is out of bounds or too big for a task operation.
 */
public class WrongIndexException extends DukeException {

  /**
   * Creates a WrongIndexException.
   */
  WrongIndexException() {
    super("    Enter a valid index");
  }
}
