package duke;

/**
 * Exception when user inputs an invalid date format.
 */
public class InvalidDateException extends DukeException {
  /**
   * Creates an InvalidDate Exception
   */
  public InvalidDateException() {
    super("Invalid date format, enter the dates using: /from dd/MM/yyyy /to dd/MM/yyyy or /by dd/MM/yyyy");
  }
}
