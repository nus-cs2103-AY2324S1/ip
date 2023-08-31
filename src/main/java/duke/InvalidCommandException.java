package duke;

/**
 * Exception to handle invalid user input.
 */
public class InvalidCommandException extends DukeException {

  /**
   * Creates an InvalidCommandException.
   */
  InvalidCommandException() {
    super("    OOPS!!! I'm sorry, but I don't know what that means :-(");
  }

}
