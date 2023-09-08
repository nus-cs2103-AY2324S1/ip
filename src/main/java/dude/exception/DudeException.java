package dude.exception;

/**
 * Exception specific to Dude.
 */
public class DudeException extends Exception {
  /**
   * Constructor for Dude exception with message
   *
   * @param message Human-readable error message.
   */
  public DudeException(String message) {
    super(message);
  }

  /**
   * Constructor for Dude exception with default message
   */
  public DudeException() {
    super("An error occurred. :(");
  }

}
