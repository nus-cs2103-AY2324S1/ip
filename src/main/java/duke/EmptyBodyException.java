package duke;

/**
 * Exception when the user does not give a tasks description.
 */
public class EmptyBodyException extends DukeException {

  EmptyBodyException() {
    super("    OOPS!!! The description of a task cannot be empty.");
  }

}
