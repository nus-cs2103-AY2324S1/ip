package dude.exception;

/**
 * Exception for error with saving to or reading from disk save file.
 */
public class SaveFileException extends DudeException {
  public SaveFileException(String message) {
    super(
      message
    );
  }
}
