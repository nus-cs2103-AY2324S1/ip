package duke;

/**
 * Exception when the user does not give a tasks description.
 */
public class EmptyBodyException extends DukeException {

  /**
   * Creates an EmptyBodyException error.
   */
  EmptyBodyException() {
    super("OOPS!!! The description of a task cannot be empty.");
  }

}
