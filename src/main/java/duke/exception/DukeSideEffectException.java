package duke.exception;

/** 
 * Represents an exception that a command can recover from. Message will be printed by Printer. 
 */
public class DukeSideEffectException extends RuntimeException {
  /**
   * Returns a DukeSideEffectException
   *
   * @param message The error message to print.
   */
  public DukeSideEffectException(String message) {
    super(message);
  }
}
