package duke.exception;

/**
 * This class implements all checked exceptions that can occur that is specific to Duke.
 * The error message contains the string to be printed for the user to view.
 */
public class DukeException extends Exception {
  public DukeException(String errorMessage) {
    super(errorMessage);
  }
  public DukeException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
